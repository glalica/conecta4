package com.example.glalica.conecta4_v1;

import android.widget.Toast;

import java.util.Random;

public class Game {
    static final int NFILAS = 7;
    static final int NCOLUMNAS =7;
    static final int VACIO = 0;
    static final int MAQUINA =1;
    static final int JUGADOR =2;
    private final String MAQUINASTR="1111";
    private final String JUGADORSTR="2222";
    private boolean juego_activo=true;
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

    public boolean estaVacio(int i, int j) {
        return (tablero[i][j]== VACIO);
    }

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

    public boolean sePuedeColocarFicha(int i, int j){
        if (i<(NFILAS-1))
            return tablero[i][j] == VACIO && !estaVacio(i+1,j);
        else
            return tablero[i][j] == VACIO;
    }

    public void juegaMaquina() {
        int i;
        int fila = -1, columna=0;
        Random r = new Random();

        do {
            columna = r.nextInt(NCOLUMNAS);
            for (i = NFILAS-1; i >0 ; i--)
                if (tablero[i][columna] == VACIO){
                        fila = i;
                        break;
                    }
        } while (fila < 0);

        tablero[fila][columna] = MAQUINA;
    }

    public int getTablero(int i, int j) {
        return tablero[i][j];
    }

    public boolean estaMaquina(int i, int j) {
        return tablero[i][j] == MAQUINA;
    }

     public void setJugador(int i, int j) {
        tablero[i][j] = JUGADOR;
    }

    public boolean finalJuego() {
        if (tableroLleno() || !juego_activo)
            return true;
        return false;
    }

    boolean comprobarCuatro(int turno) {
        if (comprobarFilas(turno) || comprobarColumnas(turno) || comprobarDiagonales(turno)) {
            juego_activo = false;
            return true;
        }
        return false;
    }

    boolean comprobarFilas(int turno) {
        String cadena = turno == MAQUINA ? MAQUINASTR : JUGADORSTR;

        for (int i = 0; i < NFILAS; i++) {
            String str = "";
            for (int j = 0; j < NCOLUMNAS; j++)
                str += Integer.toString(tablero[i][j]);
            if (str.contains(cadena))
                return true;
        }
        return false;
    }

    /*************************************************************************
     Completa este metodo.
     Parametro: turno que puede ser MAQUINA o JUGADOR.
     Retorno: true si se el jugador correspondiente al turno tiene cuatro fichas
     contiguas verticalmente, o false en caso contrario.
     *************************************************************************/
    boolean comprobarColumnas(int turno) {
        String cadena = turno == MAQUINA ? MAQUINASTR : JUGADORSTR;

        for (int j=0; j<NCOLUMNAS; j++){
            String str = "";
            for (int i=0; i<NFILAS; i++)
                str += Integer.toString((tablero[i][j]));
            if (str.contains(cadena)) return true;
        }
        return false;
    }

    boolean comprobarDiagonales(int turno) {
        String cadena = turno == MAQUINA ? MAQUINASTR : JUGADORSTR;

        for (int i = 0; i < 3; i++) {
            String str = "";
            for (int k = 0; k < 6 - i; k++)
                str += Integer.toString(tablero[i + k][k]);
            if (str.contains(cadena))
                return true;
        }

        for (int j = 1; j < 4; j++) {
            String str = "";
            for (int k = 0; k < 7 - j; k++)
                str += Integer.toString(tablero[k][j + k]);
            if (str.contains(cadena))
                return true;
        }

        return false;
    }


/*    public void juegaMaquina() {
        int i;
        int fila = -1, columna = 0;
        Random r = new Random();

        do {
            columna = r.nextInt(NCOLUMNAS);
            for (i = 0; i < NFILAS; i++)
                if (tablero[i][columna] == VACIO) {
                    fila = i;
                    break;
                }
        } while (fila < 0);
        tablero[fila][columna] = MAQUINA;
    }
*/

}
