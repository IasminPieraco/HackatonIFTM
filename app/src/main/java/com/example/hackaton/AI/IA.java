package com.example.hackaton.AI;

import android.webkit.WebHistoryItem;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Random;

public class IA {
    private ArrayList<ArrayList<Double>> pesos = new ArrayList<>();
    private ArrayList<ArrayList<Double>> dpesos = new ArrayList<>();
    private ArrayList<ArrayList<Double>> arange = new ArrayList<>();
    private ArrayList<ArrayList<Double>> saida = new ArrayList<>();
    private ArrayList<ArrayList <Double>> bias = new ArrayList<>();
    private Double Alfa = 0.01;
    private Random random = new Random();
    private ArrayList<Integer> Numcamadas = new ArrayList<>();

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

    public int treino_nao_supervisionado(Double erro_desejado,ArrayList<ArrayList<Double>> E,ArrayList<ArrayList<Double>> s){
        int c = 0;
        //este erro esta errado.
        double erro = erro_desejado;
        while (erro > erro_desejado);
        {
            erro = 0;
            for(int i = 0; i < E.size(); i++){
                erro+=calc_erro(E.get(i),s.get(i));
                for(int j = 0; j < s.size(); j++){
                    arange.get(3).set(j, saida.get(3).get(j)*(1-Math.pow(saida.get(3).get(j),2)));
                }
                for(int j = 2; j >= 0; j--){
                    for(int k = 0; k < Numcamadas.get(j); k++){
                        for(int l = 0; l < Numcamadas.get(j+1); l++) {
                            arange.get(j).set(k, arange.get(j).get(k)+((k*Numcamadas.get(j)+l)*arange.get(j+1).get(l)));
                        }
                        arange.get(j).set(k, saida.get(j).get(k) * arange.get(j).get(k));
                    }
                }

                for(int j = 0; j < 4; j++){
                    for(int k = 0; k < Numcamadas.get(j); k++){
                        for(int l = 0; l < Numcamadas.get(j+1); l++) {
                            if (j == 0) {
                                dpesos.get(j).set((k * Numcamadas.get(j) + l), Alfa * arange.get(j).get(l) * E.get(i).get(k));
                            }else {
                                dpesos.get(j).set((k * Numcamadas.get(j) + l), Alfa * arange.get(j).get(l) * saida.get(i).get(k));
                            }
                            pesos.get(j).set((k * Numcamadas.get(j) + l), pesos.get(j).get(k)+dpesos.get(j).get(k));
                        }
                    }
                }
            }
            erro = 0.5*Math.pow(erro,2);

            c++;
        }
        return c;
    }
    public double calc_erro(ArrayList<Double> Entrada,ArrayList<Double> saida){
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

    public ArrayList<Double> Leitura(ArrayList<Double> entrada){
        int c1, c2;
        c1=c2=0;
        for(int i = 0; i < Numcamadas.size(); i++) {
            while (c2 < Numcamadas.get(i + 1)) {
                saida.get(i).set(c1, 0.0);
            }
            while (c1 < Numcamadas.get(i)) {
                while (c2 < Numcamadas.get(i + 1)) {
                    if (i == 0) {
                        saida.get(i).set(c2, saida.get(i).get(c2) + pesos.get(i).get((c1*Numcamadas.get(i))+c2) * entrada.get(c1));
                    }else{
                        saida.get(i).set(c2, saida.get(i).get(c2) + pesos.get(i).get((c1*Numcamadas.get(i))+c2) * saida.get(i-1).get(c1));
                    }
                    c2++;
                }
                saida.get(i).set(c1, saida.get(i).get(c1) + bias.get(i).get(c1));
                saida.get(i).set(c1, Math.tanh(saida.get(i).get(c1)));
                c2 = 0;
                c1++;
            }
        }
        return saida.get(3);
    }
}
