package com.example.iniciao_cientfica.AI;

import android.util.Log;

public class NeuralNetwork {
    private int inputNodes, hiddenNodes, outputNodes;
    private Matrix weights_ih, weights_ho, bias_h, bias_o;
    private double learningRate = 0.1;

    public NeuralNetwork(int inputNodes, int hiddenNodes, int outputNodes) {
        this.inputNodes = inputNodes;
        this.hiddenNodes = hiddenNodes;
        this.outputNodes = outputNodes;
        weights_ih = new Matrix(hiddenNodes, inputNodes);
        weights_ho = new Matrix(outputNodes, hiddenNodes);
        weights_ih.randomize();
        weights_ho.randomize();
        bias_h = new Matrix(hiddenNodes, 1);
        bias_o = new Matrix(outputNodes, 1);
        bias_h.randomize();
        bias_o.randomize();
    }


    public double[] predict(double[] inputArray) {

        Matrix inputs = Matrix.fromArray(inputArray);
        Matrix hidden = Matrix.multiply(weights_ih, inputs);
        hidden.add(bias_h);
        hidden = Matrix.map(hidden, this::relu);
        Matrix outputs = Matrix.multiply(weights_ho, hidden);
        outputs.add(bias_o);
        outputs = Matrix.map(outputs, this::sigmoid);
        return outputs.toArray();
    }

    public double train(double[] inputArray, double[] targetArray) {
        double error = 0;
        Matrix inputs = Matrix.fromArray(inputArray);
        Matrix hidden = Matrix.multiply(weights_ih, inputs);
        hidden.add(bias_h);
        hidden = Matrix.map(hidden, this::relu);

        Matrix outputs = Matrix.multiply(weights_ho, hidden);
        outputs.add(bias_o);
        outputs = Matrix.map(outputs, this::sigmoid);
        Matrix targets = Matrix.fromArray(targetArray);
        Matrix outputErrors = Matrix.subtract(targets, outputs);
        for(int i = 0; i < outputErrors.getRows(); i++){
            for(int j = 0; j < outputErrors.getCols(); j++){
                error+=outputErrors.getData()[i][j];
            }
        }
        Matrix gradients = Matrix.map(outputs, this::dsigmoid);
        gradients.hadamard(outputErrors);
        gradients.multiply(learningRate);
        Matrix hiddenT = Matrix.transpose(hidden);
        Matrix weight_ho_deltas = Matrix.multiply(gradients, hiddenT);
        weights_ho.add(weight_ho_deltas);
        bias_o.add(gradients);
        Matrix whoT = Matrix.transpose(weights_ho);
        Matrix hiddenErrors = Matrix.multiply(whoT, outputErrors);
        Matrix hiddenGradient = Matrix.map(hidden, this::drelu);
        hiddenGradient.hadamard(hiddenErrors);
        hiddenGradient.multiply(learningRate);
        Matrix inputsT = Matrix.transpose(inputs);
        Matrix weight_ih_deltas = Matrix.multiply(hiddenGradient, inputsT);
        weights_ih.add(weight_ih_deltas);
        bias_h.add(hiddenGradient);
        return error;
    }
    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
    private double dsigmoid(double y) {
        return y * (1 - y);
    }
    private double relu(double x) {
        return Math.max(0, x);
    }
    private double drelu(double y) {
        return y > 0 ? 1 : 0;
    }
    public Matrix[] getWeights() {
        return new Matrix[]{weights_ih, weights_ho};
    }
    public void setWeights(Matrix[] newWeights) {
        if (newWeights.length != 2) {
            throw new IllegalArgumentException("São necessárias duas matrizes de pesos: input-hidden e hidden-output.");
        }
        if (newWeights[0].getRows() != hiddenNodes || newWeights[0].getCols() != inputNodes) {
            throw new IllegalArgumentException("Dimensões da matriz input-hidden incorretas.");
        }
        if (newWeights[1].getRows() != outputNodes || newWeights[1].getCols() != hiddenNodes) {
            throw new IllegalArgumentException("Dimensões da matriz hidden-output incorretas.");
        }
        weights_ih = newWeights[0];
        weights_ho = newWeights[1];
    }
}