package com.curso.tarea3;

import android.util.Log;
import android.util.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;
import java.util.Vector;


public class Baraja {
    private Vector<Carta> base_pica;
    private Vector<Carta> base_trebol;
    private Vector<Carta> base_diamante;
    private Vector<Carta> base_corazon;
    private Vector<Deque<Carta>> mazo_restante;
    private int numero_carta[] = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    private int[] numero_carta_restante;
    private Carta a_1, a_2, a_3, a_4;
    private String palo[] = { "pica", "trebol", "diamante", "corazon"};


    public void inicio(){
        //inicio mazos superiores
        base_pica = new Vector<Carta>();
        base_trebol = new Vector<Carta>();
        base_diamante = new Vector<Carta>();;
        base_corazon = new Vector<Carta>();
        a_1 = new Carta();
        a_1.setNumero(1);
        a_1.setPalo("pica");
        a_1.setEstado("visible");
        base_pica.add(a_1);
        a_2 = new Carta();
        a_2.setNumero(1);
        a_2.setPalo("trebol");
        a_2.setEstado("visible");
        base_trebol.add(a_2);
        a_3 = new Carta();
        a_3.setNumero(1);
        a_3.setPalo("diamante");
        a_3.setEstado("visible");
        base_diamante.add(a_3);
        a_4 = new Carta();
        a_4.setNumero(1);
        a_4.setPalo("corazon");
        a_4.setEstado("visible");
        base_corazon.add(a_4);
        Log.d("ESTADO ",base_pica.get(0).getPalo());
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
        //traspaso de mazo_temporal a mazo_restante
        mazo_restante = new Vector<Deque<Carta>>();
        inicializando_numero_carta_restante();
        knuth_mazo_restante();
        int j = 0;
        for (int i = 0; i < 48; i++){
            if (i == 0){
                Deque<Carta> mazo_temporal = new LinkedList<Carta>();
                mazo_restante.add(mazo_temporal);
                mazo_restante.elementAt(j).add(temporal.elementAt(numero_carta_restante[i]));
                Log.d("NUEVO ",String.valueOf(mazo_restante.elementAt(j).getLast().getNumero()));
                Log.d("NUEVO ",mazo_restante.elementAt(j).getLast().getPalo());
            }
            else if(i != 0 && (i % 4) == 0){
                Deque<Carta> mazo_temporal = new LinkedList<Carta>();
                mazo_restante.add(mazo_temporal);
                mazo_restante.elementAt(j).add(temporal.elementAt(numero_carta_restante[i]));
                Log.d("NUEVO ",String.valueOf(mazo_restante.elementAt(j).getLast().getNumero()));
                Log.d("NUEVO ",mazo_restante.elementAt(j).getLast().getPalo());
                j++;
            }
            else {
                mazo_restante.elementAt(j).add(temporal.elementAt(numero_carta_restante[i]));
                Log.d("NUEVO ",String.valueOf(mazo_restante.elementAt(j).getLast().getNumero()));
                Log.d("NUEVO ",mazo_restante.elementAt(j).getLast().getPalo());
            }
        }

        for (int i = 0; i < mazo_restante.size(); i ++){
            mazo_restante.elementAt(i).getFirst().setEstado("visible");
        }
    }
    //guarda las posiciones posibles del mazo_temporal
    public void inicializando_numero_carta_restante(){
        numero_carta_restante = new int[48];
        for(int i = 0; i < 48; i++){
            numero_carta_restante[i] = i;
        }
    }
    //desordenar de números en cada pilas
    public void knuth_pilas(){
        Random r = new Random();
        for (int i = 11; i > 0; i--) {
            int indice = r.nextInt(i);
            int tmp = numero_carta[indice];
            numero_carta[indice] = numero_carta[i];
            numero_carta[i] = tmp;
        }
    }
    //desordenar todas las cartas del mazo_restante
    public void knuth_mazo_restante(){
        Random r = new Random();
        for (int i = 47; i > 0; i--) {
            int indice = r.nextInt(i);
            int tmp = numero_carta_restante[indice];
            numero_carta_restante[indice] = numero_carta_restante[i];
            numero_carta_restante[i] = tmp;
        }
    }
    //insertar carta de una de las 12 pilas a una base
    public void insertar_a_base(int index_pila_inicio, String palo_base_destino){
        if(mazo_restante.elementAt(index_pila_inicio).size() != 0){
            if(mazo_restante.elementAt(index_pila_inicio).getFirst().getPalo() == palo_base_destino){
                if(palo_base_destino == "pica"){
                    if ((mazo_restante.elementAt(index_pila_inicio).getFirst().getNumero() - 1) == base_pica.lastElement().getNumero()){
                        base_pica.add(mazo_restante.elementAt(index_pila_inicio).getFirst());
                        mazo_restante.elementAt(index_pila_inicio).removeFirst();
                    }
                }
                else if (palo_base_destino == "trebol"){
                    if ((mazo_restante.elementAt(index_pila_inicio).getFirst().getNumero() - 1) == base_trebol.lastElement().getNumero()){
                        base_trebol.add(mazo_restante.elementAt(index_pila_inicio).getFirst());
                        mazo_restante.elementAt(index_pila_inicio).removeFirst();
                    }
                }
                else if (palo_base_destino == "diamante"){
                    if ((mazo_restante.elementAt(index_pila_inicio).getFirst().getNumero() - 1) == base_diamante.lastElement().getNumero()){
                        base_diamante.add(mazo_restante.elementAt(index_pila_inicio).getFirst());
                        mazo_restante.elementAt(index_pila_inicio).removeFirst();
                    }
                }
                else if (palo_base_destino == "corazon"){
                    if ((mazo_restante.elementAt(index_pila_inicio).getFirst().getNumero() - 1) == base_corazon.lastElement().getNumero()){
                        base_corazon.add(mazo_restante.elementAt(index_pila_inicio).getFirst());
                        mazo_restante.elementAt(index_pila_inicio).removeFirst();
                    }
                }
            }
        }
    }
    //inserción entre cartas de las 12 pilas.
    public void insertar_a_pila(int index_pila_inicio, int index_pila_destino){
        if((mazo_restante.elementAt(index_pila_inicio).size() > 0) && (mazo_restante.elementAt(index_pila_destino).size() > 0) ){
            if (mazo_restante.elementAt(index_pila_inicio).getFirst().getPalo() == mazo_restante.elementAt(index_pila_destino).getFirst().getPalo()){
                if ((mazo_restante.elementAt(index_pila_inicio).getFirst().getNumero() - 1) ==mazo_restante.elementAt(index_pila_destino).getFirst().getNumero()){
                    mazo_restante.elementAt(index_pila_destino).addFirst(mazo_restante.elementAt(index_pila_inicio).getFirst());
                    mazo_restante.elementAt(index_pila_inicio).removeFirst();
                }
            }
        }
    }
    public void repartir(){
        Vector<Carta> temporal = new Vector<Carta>();
        for (int i = 0; i < mazo_restante.size(); i++){
            int k = mazo_restante.elementAt(i).size();
            for (int j = 0; j < k; j++){
                temporal.add(mazo_restante.elementAt(i).getFirst());
                mazo_restante.elementAt(i).removeFirst();
            }
        }
        int k = 0;
        for (int i = 0; i < mazo_restante.size(); i++){
            for (int j = 0; j < 4; j++){
                if(temporal.size() == k){
                    break;
                }
                else {
                    mazo_restante.elementAt(i).addLast(temporal.elementAt(k));
                    Log.d("REPARTIR_NUMERO ", String.valueOf(mazo_restante.elementAt(i).getLast().getNumero()));
                    Log.d("REPARTIR_PALO ", mazo_restante.elementAt(i).getLast().getPalo());
                    k++;
                }
            }
            if (temporal.size() == k){
                break;
            }
        }

    }
    //chequear si el juego se terminó
    public boolean juego_terminado(){
        if ((base_pica.size() == 13) && (base_trebol.size() == 13) && (base_diamante.size() == 13) && (base_corazon.size() == 13)){
            return true;
        }
        else {
            return false;
        }
    }
    public int cartas_en_bases(){
        return (base_pica.size() + base_trebol.size() + base_diamante.size() + base_corazon.size() - 4);
    }

    public void insertar(){//INCOMPLETO
        for(int i = 0; i < mazo_restante.size(); i++){
            for(int j = 0; j < mazo_restante.elementAt(i).size(); j++){
                if(mazo_restante.elementAt(j).getLast().getPalo() == "corazon"){

                }
            }
        }
    }
}
