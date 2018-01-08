package Parcial1.Proyecto;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Erik on 9/2/2017.
 */
public class JugarGato {
    int tablero[][];
    int casillasRestantes;
    //int jugador;

    public LinkedList<Point> historialJugadas;

    public JugarGato(int rows, int cols) {
        tablero = new int[rows][cols];
        casillasRestantes = rows * cols;
        historialJugadas = new LinkedList<>();
    }

    public void setJugadaTablero(Point p, int jugador) {
        if(jugador == 0) {
            tablero[p.x][p.y] = 1;
            casillasRestantes--;
        } else if(jugador == 1) {
            tablero[p.x][p.y] = 2;
            casillasRestantes--;
        }
        historialJugadas.add(p);
    }


    public boolean juegoTerminadoP1(int[][] tablero) {
        boolean terminado = false;
        if(
                (tablero[0][0] == 1 && tablero[1][0] == 1 && tablero[2][0] == 1) ||
                (tablero[0][1] == 1 && tablero[1][1] == 1 && tablero[2][1] == 1) ||
                (tablero[0][2] == 1 && tablero[1][2] == 1 && tablero[2][2] == 1) ||  //horizontal victories

                (tablero[0][0] == 1 && tablero[0][1] == 1 && tablero[0][2] == 1) ||
                (tablero[1][0] == 1 && tablero[1][1] == 1 && tablero[1][2] == 1) ||
                (tablero[2][0] == 1 && tablero[2][1] == 1 && tablero[2][2] == 1) ||   //vertical victories

                (tablero[0][0] == 1 && tablero[1][1] == 1 && tablero[2][2] == 1) ||
                (tablero[0][2] == 1 && tablero[1][1] == 1 && tablero[2][0] == 1)      //diagonal victories
                ) {
            terminado = true;
        }
        return  terminado;
    }

    public boolean juegoTerminadoP2(int[][] tablero) {
        boolean terminado = false;
        if(
                (tablero[0][0] == 2 && tablero[1][0] == 2 && tablero[2][0] == 2) ||
                        (tablero[0][1] == 2 && tablero[1][1] == 2 && tablero[2][1] == 2) ||
                        (tablero[0][2] == 2 && tablero[1][2] == 2 && tablero[2][2] == 2) ||  //horizontal victories

                        (tablero[0][0] == 2 && tablero[0][1] == 2 && tablero[0][2] == 2) ||
                        (tablero[1][0] == 2 && tablero[1][1] == 2 && tablero[1][2] == 2) ||
                        (tablero[2][0] == 2 && tablero[2][1] == 2 && tablero[2][2] == 2) ||   //vertical victories

                        (tablero[0][0] == 2 && tablero[1][1] == 2 && tablero[2][2] == 2) ||
                        (tablero[0][2] == 2 && tablero[1][1] == 2 && tablero[2][0] == 2)      //diagonal victories
                ) {
            terminado = true;
        }
        return  terminado;
    }

    public LinkedList<Point> caminoMasLargo(Point beginning) {
        LinkedList<Point> path = new LinkedList<>();
        Stack<Point> opened = new Stack<>();
        LinkedList closed = new LinkedList<>();

        int jugador = (casillasRestantes + 1) % 2;
        int[][] matrizJugadas = copyMatrix();
        boolean isClosed;

        opened.add(beginning);
        while(opened.size() > 0) {   //mientras hay nodos en la lista
            boolean hasWon = false;
            isClosed = false;
            Point current = opened.pop();

            Iterator<Point> it = closed.iterator();
            while (it.hasNext()) {   //search if the element is already closed
                if (current.equals(it.next())) {
                    isClosed = true;
                    break;
                }
            }
            if(!isClosed) {
                path.add(current);
                for (int i = 0; i < matrizJugadas.length; i++) {
                    if (matrizJugadas[current.y][i] == 0) {
                        opened.add(new Point(current.y, i));
                    }
                }
                if(jugador % 2 != 0) {  //check player turn
                    matrizJugadas[current.x][current.y] = 1;
                    if(juegoTerminadoP1(matrizJugadas)) {    //Player 1 has won
                        hasWon = true;
                    }
                } else {
                    matrizJugadas[current.x][current.y] = 2;
                    if(juegoTerminadoP2(matrizJugadas)) {  //Player 2 has won
                        hasWon = true;
                    }
                }
                jugador++;   //It's next player turn
                if(!hasWon) {
                    closed.add(current);   //close the current element
                } else {
                    return path;
                }

            } else {

            }
        }
        //path.removeLast();
        //path.clear();
        return path;
    }

    private int[][] copyMatrix() {
        int[][] matrix = new int[tablero.length][tablero.length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                matrix[i][j] = tablero[i][j];
            }
        }
        return matrix;
    }

    private LinkedList<Point> generatePath(LinkedList<Point> savedSteps) {
        return savedSteps;
    }
}
