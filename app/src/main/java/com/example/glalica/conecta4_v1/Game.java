package com.example.glalica.conecta4_v1;

import android.widget.Toast;

import java.util.Random;

/**
 * Created by glalica on 07/11/2015.
 */
public class Game {
    static final int NFILAS = 7;
    static final int NCOLUMNAS =7;
    static final int VACIO = 0;
    static final int MAQUINA =1;
    static final int JUGADOR =2;

    private int tablero[][];

    /* Comienza el juego poniendo todo el tablero vacio */
    public Game(){
        tablero = new int[NFILAS][NCOLUMNAS];

        /* Lleno el tablero con CEROS ya que está vacío.-*/
        for (int i=0; i<NFILAS; i++)
            for (int j=0; j<NCOLUMNAS; j++)
                tablero[i][j] = VACIO;
    }

    public void jugamos(int i, int j){
//        if (estaVacio(i,j))
            if (sePuedeColocarFicha(i,j)) {
                ponerJugador(i, j);
                            }
            /* Deberua mandar un mensaje per no se como*/
            /* POSICION ERRONEA */

    }


    /*************************************************************************
     Parametros: indices i y j del tablero.
     Retorno: cierto si la posicion tablero[i][j] esta vacia (su valor
     es VACIO) y falso en caso contrario
     *************************************************************************/

    public boolean estaVacio(int i, int j) {
        return (tablero[i][j]== VACIO);
    }

    /*************************************************************************
     Parametros: indices i y j del tablero.
     Retorno: cierto si la posicion tablero[i][j] esta ocupada por el
     jugador (su valor es JUGADOR) y falso en caso contrario
     *************************************************************************/
    public boolean estaJugador(int i, int j) {
        return tablero[i][j] == JUGADOR;
    }

    public void ponerJugador(int i, int j) {
        tablero[i][j] = JUGADOR;
    }

    public boolean tableroLleno() {
        for (int i=0; i<NFILAS; i++)
            for (int j=0; j<NCOLUMNAS; j++)
                if (tablero[i][j] == VACIO)
                    return false;

        return true;
    }

    /*************************************************************************
     Parametros: indices i y j del tablero.
     Retorno: cierto si se puede colocar ficha en la posicion (i,j) del
     tablero. Debes comprobar que esa posicion del tablero esta vacia
     (su valor es VACIO) y que es la posicion vacia mas baja del tablero.
     En caso contrario, la funcion debe devolver false.
     *************************************************************************/
    public boolean sePuedeColocarFicha(int i, int j){
        if (i<(NFILAS-1))
            return tablero[i][j] == VACIO && !estaVacio(i+1,j);
        else
            return tablero[i][j] == VACIO;
    }


    public void juegaMaquina() {
        int i;
        int fila = -1, columna;
        Random r = new Random();

        do {
            columna = r.nextInt(NCOLUMNAS);

            for (i = NFILAS-1; i > 0; i--)
                if (tablero[i][columna] == VACIO) {
                    fila = i;
                    break;
                }
        } while (fila < 0);

        tablero[fila][columna] = MAQUINA;
    }

    public int getTablero(int i, int j) {
        return tablero[i][j];
    }


   /*
    Además de estos métodos será conveniente que añadas métodos de acceso al tablero para consultar el estado de cada posición.

*/
}
