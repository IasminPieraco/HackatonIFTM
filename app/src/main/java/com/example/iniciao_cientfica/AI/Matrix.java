package com.example.iniciao_cientfica.AI;

import android.util.Log;

import java.util.function.Function;

public class Matrix {
    private double[][] data;
    private int rows, cols;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new double[rows][cols];
    }
    public void randomize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = Math.random() * 2 - 1;
            }
        }
    }
    public static Matrix fromArray(double[] arr) {
        Matrix m = new Matrix(arr.length, 1);
        for (int i = 0; i < arr.length; i++) {
            m.data[i][0] = arr[i];
        }
        return m;
    }
    public double[] toArray() {
        double[] arr = new double[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                arr[i * cols + j] = data[i][j];
            }
        }
        return arr;
    }
    public static Matrix multiply(Matrix a, Matrix b) {
        Matrix result = new Matrix(a.rows, b.cols);

        for (int i = 0; i < result.rows; i++) {
            for (int j = 0; j < result.cols; j++) {
                double sum = 0;
                for (int k = 0; k < a.cols; k++) {
                    sum += a.data[i][k] * b.data[k][j];
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }
    public void add(Matrix m) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] += m.data[i][j];
            }
        }
    }
    public void add(double n) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] += n;
            }
        }
    }
    public static Matrix subtract(Matrix a, Matrix b) {
        Matrix result = new Matrix(a.rows, a.cols);
        for (int i = 0; i < a.rows; i++) {
            for (int j = 0; j < a.cols; j++) {
                result.data[i][j] = a.data[i][j] - b.data[i][j];
            }
        }
        return result;
    }
    public static Matrix transpose(Matrix m) {
        Matrix result = new Matrix(m.cols, m.rows);
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                result.data[j][i] = m.data[i][j];
            }
        }
        return result;
    }
    public static Matrix map(Matrix m, Function<Double, Double> func) {
        Matrix result = new Matrix(m.rows, m.cols);
        for (int i = 0; i < m.rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                result.data[i][j] = func.apply(m.data[i][j]);
            }
        }
        return result;
    }
    public void multiply(double n) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] *= n;
            }
        }
    }
    public void hadamard(Matrix m) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] *= m.data[i][j];
            }
        }
    }
    int getRows() {
        return this.rows;
    }
    int getCols() {
        return this.cols;
    }
    double[][] getData(){
        return this.data;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append("[ ");
            for (int j = 0; j < cols; j++) {
                sb.append(String.format("%.4f", data[i][j]));
                if (j < cols - 1) sb.append(", ");
            }
            sb.append(" ]\n");
        }
        return sb.toString();
    }
}

