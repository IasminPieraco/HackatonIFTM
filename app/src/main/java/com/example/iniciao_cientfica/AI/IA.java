package com.example.iniciao_cientfica.AI;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IA {

    public static NeuralNetwork neural;

    public static void initIA(int inputNodes, int hiddenNodes, int outputNodes){
        neural = new NeuralNetwork(inputNodes,hiddenNodes,outputNodes);
    }

    public static void train(){
        double error = 1;
        while(error > 0.0000001){
            error = neural.train(new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 2.5, 3.0, 5.0, 0.0, 0.0, 0.4, 1.2, 0.1, 0.7, -1.2, -0.3, 0.1, -0.3, 0.15, 0.6, 1.0, -1.0, -1.0, 1.0, 1.0, 1.0, -1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0}, new double[]{0.2, 0.4, 0.6, 0.1});
            error += neural.train(new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0 ,0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.5, 9.0,  5.0,   7.0, 8.0,   5.4,  1.2, 0.1, 0.7, -1.2, -0.3, 0.1, -0.3, 0.15, 0.6, 1.0, -1.0, -1.0, 1.0, 1.0, 1.0, -1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0}, new double[]{0.8,0.2,0.2,0.4});
            error += neural.train(new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 3.5, -3.0, 0.0,   0.0, 1.0,   -0.8, 1.2, 0.1 ,0.7, -1.2, -0.3 ,0.1, -0.3, 0.15, 0.6, 1.0, -1.0, -1.0, 1.0 ,1.0, 1.0 ,-1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0}, new double[]{0.1,0.7,0.3,0.3});
            error += neural.train(new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 2.5, 3.0,  5.0,   0.0, 0.0,   0.4,  1.2, 0.1, 0.7, -1.2, -0.3, 0.1, -0.3, 0.15, 0.6, 1.0, -1.0, -1.0, 1.0, 1.0, 1.0, -1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0}, new double[]{0.2,0.4,0.6,0.1});
        }
    }

    public static void predict(){
        double[] saida = neural.predict(new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 2.5, 3.0, 5.0, 0.0, 0.0, 0.4, 1.2, 0.1, 0.7, -1.2, -0.3, 0.1, -0.3, 0.15, 0.6, 1.0, -1.0, -1.0, 1.0, 1.0, 1.0, -1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0});
        Log.i("Claudio", Arrays.toString(saida));
        saida = neural.predict(new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0 ,0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.5, 9.0,  5.0,   7.0, 8.0,   5.4,  1.2, 0.1, 0.7, -1.2, -0.3, 0.1, -0.3, 0.15, 0.6, 1.0, -1.0, -1.0, 1.0, 1.0, 1.0, -1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0});
        Log.i("Claudio", Arrays.toString(saida));
        saida = neural.predict(new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 3.5, -3.0, 0.0,   0.0, 1.0,   -0.8, 1.2, 0.1 ,0.7, -1.2, -0.3 ,0.1, -0.3, 0.15, 0.6, 1.0, -1.0, -1.0, 1.0 ,1.0, 1.0 ,-1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0});
        Log.i("Claudio", Arrays.toString(saida));
        saida = neural.predict(new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 2.5, 3.0,  5.0,   0.0, 0.0,   0.4,  1.2, 0.1, 0.7, -1.2, -0.3, 0.1, -0.3, 0.15, 0.6, 1.0, -1.0, -1.0, 1.0, 1.0, 1.0, -1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, -1.0, -1.0, 1.0, -1.0, 1.0, 1.0, 1.0});
        Log.i("Claudio", Arrays.toString(saida));
    }

    /*private static ArrayList<ArrayList<Double>> pesos = new ArrayList<>();
    private static ArrayList<ArrayList<Double>> dpesos = new ArrayList<>();
    private static ArrayList<ArrayList<Double>> arange = new ArrayList<>();
    private static ArrayList<ArrayList<Double>> saida = new ArrayList<>();
    private static ArrayList<ArrayList <Double>> bias = new ArrayList<>();
    private static Double Alfa = 0.01;
    private static Random random = new Random();
    private static ArrayList<Integer> Numcamadas = new ArrayList<>();

    public IA(){
        Numcamadas.add(48);
        Numcamadas.add(100);
        Numcamadas.add(100);
        Numcamadas.add(100);
        Numcamadas.add(10);
        for(int j = 0; j < 4; j++) {
            ArrayList<Double> aux = new ArrayList<>();
            for (int i = 0; i < Numcamadas.get(j) * Numcamadas.get(j+1); i++) {
                aux.add(random.nextDouble() * 0.5);
            }
            pesos.add(aux);
            dpesos.add(aux);
        }

        for(int j = 1; j < 4; j++) {
            ArrayList<Double> aux = new ArrayList<>();
            ArrayList<Double> aux2 = new ArrayList<>();
            for (int i = 0; i < Numcamadas.get(j); i++) {
                aux.add(0.0);
                aux2.add(0.0);
            }
            saida.add(aux);
            arange.add(aux);
            bias.add(aux2);
        }
        ArrayList<Double> aux = new ArrayList<>();
        ArrayList<Double> aux2 = new ArrayList<>();
        for (int i = 0; i < Numcamadas.get(4); i++) {
            aux.add(0.0);
            aux2.add(0.0);
        }
        saida.add(aux);
        arange.add(aux);
        bias.add(aux2);
    }

    public static void init(){
        Numcamadas.clear();
        pesos.clear();
        dpesos.clear();
        saida.clear();
        arange.clear();
        bias.clear();
        Numcamadas.add(48);
        Numcamadas.add(10);
        Numcamadas.add(10);
        Numcamadas.add(10);
        Numcamadas.add(4);
        for(int j = 0; j < 4; j++) {
            ArrayList<Double> aux = new ArrayList<>();
            for (int i = 0; i < Numcamadas.get(j) * Numcamadas.get(j+1); i++) {
                aux.add(random.nextDouble() * 0.5);
            }
            pesos.add(aux);
            dpesos.add(aux);
        }

        for(int j = 1; j < 4; j++) {
            ArrayList<Double> aux = new ArrayList<>();
            ArrayList<Double> aux2 = new ArrayList<>();
            for (int i = 0; i < Numcamadas.get(j); i++) {
                aux.add(0.0);
                aux2.add(0.0);
            }
            saida.add(aux);
            arange.add(aux);
            bias.add(aux2);
        }
        ArrayList<Double> aux = new ArrayList<>();
        ArrayList<Double> aux2 = new ArrayList<>();
        for (int i = 0; i < Numcamadas.get(4); i++) {
            aux.add(0.0);
            aux2.add(0.0);
        }
        saida.add(aux);
        arange.add(aux);
        bias.add(aux2);
    }

    public static int treino_nao_supervisionado(Double erro_desejado,ArrayList<ArrayList<Double>> E,ArrayList<ArrayList<Double>> s){
        int c = 0;
        int max = 0;
        //este erro esta errado.
        double erro = erro_desejado+1;
        while (erro > erro_desejado)
        {
            Log.println(Log.ASSERT,"Claudio","Meta");
            Log.println(Log.ASSERT,"Claudio", String.valueOf(max));
            erro = 0;
            for(int i = 0; i < E.size(); i++){
                erro+=calc_erro(E.get(i),s.get(i));

                for(int j = 0; j < s.size(); j++){
                    arange.get(3).set(j, saida.get(3).get(j)*(1-Math.pow(saida.get(3).get(j),2)));
                }

                for(int j = 2; j >= 0; j--){
                    for(int k = 0; k < Numcamadas.get(j+1); k++){
                        for(int l = 0; l < Numcamadas.get(j+2); l++) {
                            arange.get(j).set(k, arange.get(j).get(k)+(pesos.get(j).get(k*Numcamadas.get(j+1)+l)*arange.get(j+1).get(l)));
                        }
                        arange.get(j).set(k, saida.get(j).get(k) * arange.get(j).get(k));
                    }
                }

                for(int j = 0; j < 4; j++){
                    for(int k = 0; k < Numcamadas.get(j); k++){
                        for(int l = 0; l < Numcamadas.get(j+1); l++) {
                            if (j == 0) {
                                dpesos.get(j).set((k * Numcamadas.get(j+1) + l), Alfa * arange.get(j).get(l) * E.get(i).get(k));
                            }else {
                                dpesos.get(j).set((k * Numcamadas.get(j+1) + l), Alfa * arange.get(j).get(l) * saida.get(j-1).get(k));
                            }
                            pesos.get(j).set((k * Numcamadas.get(j+1) + l), pesos.get(j).get((k * Numcamadas.get(j+1) + l))+dpesos.get(j).get((k * Numcamadas.get(j+1) + l)));
                        }
                    }
                }

            }
            erro = 0.5*Math.pow(erro,2);

            c++;
            //Log.println(Log.ASSERT,"Claudio",String.valueOf(erro));
            max++;
        }
        return c;
    }
    public static double calc_erro(ArrayList<Double> Entrada,ArrayList<Double> saida){
        double erro = 0.0;
        ArrayList<Double> target = Leitura(Entrada);
        int c  = 0;
        while (c<Numcamadas.get(4))
        {
            erro = erro + (saida.get(c) - target.get(c));
            c++;
        }

        return erro;
    }

    public static ArrayList<Double> Leitura(ArrayList<Double> entrada){
        int c1, c2;
        c2=0;
        for(int i = 0; i < Numcamadas.size()-1; i++) {
            while (c2 < Numcamadas.get(i + 1)) {
                saida.get(i).set(c2, 0.0);
                c2++;
            }
            c1 = c2= 0;
            while (c1 < Numcamadas.get(i)) {
                while (c2 < Numcamadas.get(i + 1)) {
                    if (i == 0) {
                        saida.get(i).set(c2, saida.get(i).get(c2) + pesos.get(i).get((c1*Numcamadas.get(i+1))+c2) * entrada.get(c1));
                    }else{
                        saida.get(i).set(c2, saida.get(i).get(c2) + pesos.get(i).get((c1*Numcamadas.get(i+1))+c2) * saida.get(i-1).get(c1));
                    }
                    c2++;
                }
                c2 = 0;
                Log.println(Log.ASSERT,"Claudio", String.valueOf(saida.get(i)));
                while (c2 < Numcamadas.get(i + 1)) {
                    saida.get(i).set(c2, saida.get(i).get(c2) + bias.get(i).get(c2));
                    saida.get(i).set(c2, Math.tanh(saida.get(i).get(c2)));
                    c2++;
                }
                c2 = 0;
                c1++;

            }
        }
        return saida.get(3);
    }*/

}
