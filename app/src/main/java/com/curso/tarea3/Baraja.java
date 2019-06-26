package com.curso.tarea3;

import android.util.Log;
import android.util.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;


public class Baraja {
    private Vector<Carta> mazo_pica;
    private Vector<Carta> mazo_trebol;
    private Vector<Carta> mazo_diamante;
    private Vector<Carta>mazo_corazon;
    private Vector<Deque<Carta>> mazo_restante;
    private int numero_carta[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private int[] numero_carta_restante;
    private Carta a_1, a_2, a_3, a_4;
    private String palo[] = { "pica", "trebol", "diamante", "corazon"};


    public void inicio(){
        //inicio mazos superiores
        mazo_pica = new Vector<Carta>();
        mazo_trebol = new Vector<Carta>();
        mazo_diamante = new Vector<Carta>();;
        mazo_corazon = new Vector<Carta>();
        a_1 = new Carta();
        a_1.setNumero(1);
        a_1.setPalo("pica");
        a_1.setEstado("visible");
        mazo_pica.add(a_1);
        a_2 = new Carta();
        a_2.setNumero(1);
        a_2.setPalo("trebol");
        a_2.setEstado("visible");
        mazo_trebol.add(a_2);
        a_3 = new Carta();
        a_3.setNumero(1);
        a_3.setPalo("diamante");
        a_3.setEstado("visible");
        mazo_diamante.add(a_3);
        a_4 = new Carta();
        a_4.setNumero(1);
        a_4.setPalo("corazon");
        a_4.setEstado("visible");
        mazo_corazon.add(a_4);
        Log.d("ESTADO ",mazo_pica.get(0).getPalo());
        //inicio 12 mazos
        knuth_pilas();
        Vector<Carta> temporal = new Vector<Carta>();
        for (int j = 0 ; j < 4 ; j++) {
            for (int i = 0; i < 12; i++) {
                Carta carta_temporal = new Carta();
                carta_temporal.setNumero(numero_carta[i]);
                carta_temporal.setPalo(palo[j]);
                carta_temporal.setEstado("invisible");
                temporal.add(carta_temporal);
            }
        }
        mazo_restante = new Vector<Deque<Carta>>();
        inicializando_numero_carta_restante();
        knuth_mazo_restante();
        int j = 0;
        for (int i = 0; i < 48; i++){
            if (i == 0){
                Deque<Carta> mazo_temporal = new LinkedList<Carta>();
                mazo_restante.add(mazo_temporal);
                mazo_restante.elementAt(j).add(temporal.elementAt(numero_carta_restante[i]));
            }
            else if(i != 0 && (i % 4) == 0){
                Deque<Carta> mazo_temporal = new LinkedList<Carta>();
                mazo_restante.add(mazo_temporal);
                mazo_restante.elementAt(j).add(temporal.elementAt(numero_carta_restante[i]));
                j++;
            }
            else {
                mazo_restante.elementAt(j).add(temporal.elementAt(numero_carta_restante[i]));
            }
        }
        for (int i = 0; i < mazo_restante.size(); i ++){
                Log.d("AQUIII ",String.valueOf(mazo_restante.elementAt(i).getFirst().getNumero()));
                Log.d("AQUIII ",mazo_restante.elementAt(i).getFirst().getPalo());
        }
    }
    public void inicializando_numero_carta_restante(){
        numero_carta_restante = new int[48];
        for(int i = 0; i < 48; i++){
            numero_carta_restante[i] = i;
        }
    }

    public void knuth_pilas(){
        Random r = new Random();
        for (int i = 11; i > 0; i--) {
            int indice = r.nextInt(i);
            int tmp = numero_carta[indice];
            numero_carta[indice] = numero_carta[i];
            numero_carta[i] = tmp;
        }
    }
    public void knuth_mazo_restante(){
        Random r = new Random();
        for (int i = 47; i > 0; i--) {
            int indice = r.nextInt(i);
            int tmp = numero_carta_restante[indice];
            numero_carta_restante[indice] = numero_carta_restante[i];
            numero_carta_restante[i] = tmp;
        }
    }
}
