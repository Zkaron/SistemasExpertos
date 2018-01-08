package Parcial1.matrizAdyacente;

import java.util.*;

public class Adyacente {
    int [][] matriz;
    private int origin;
    private int destine;

    public Adyacente() {
            
    }

    public Adyacente(int[][] matriz) {
        this.matriz = matriz;
    }

    public void buscar(int[][] matriz) {

    }

    /*public LinkedList<Integer> caminoMasLargo(int inicio, int destino) {
        LinkedList<Integer> path = new LinkedList<>();
        LinkedList<Integer> steps = new LinkedList<>();
        boolean visited;
        steps.add(inicio);
        int i = -1;
        while(steps.size() > 0 || i == destino) {   //mientras hay nodos en la lista
            visited = false;
            i = steps.removeLast();
            for(int camino : path) {
                if(i == camino) {
                    visited = true;
                }
            }
            if(visited) continue;
            path.add(i);
            for(int j = 0; j < matriz.length; j++) {    //busca todos sus adyacentes
                if(matriz[i][j] != 0) {
                    steps.add(j);
                }
            }
        }
        return path;
    }*/

    public LinkedList<Integer> caminoMasCorto(int beginning, int destine) {
        LinkedList<Integer> path = new LinkedList<>();
        Queue<Integer> opened = new LinkedList<>();
        LinkedList closed = new LinkedList<>();
        boolean isClosed;

        setOrigin(beginning);
        setDestine(destine);
        opened.add(beginning);
        while(opened.size() > 0) {   //mientras hay nodos en la lista
            isClosed = false;
            int current = opened.remove();
            path.add(current);
            if(current == destine) {
                return generatePath(path);
            }

            Iterator<Integer> it = closed.iterator();
            while (it.hasNext()) {   //search if the element is already closed
                if (current == it.next()) {
                    isClosed = true;
                    break;
                }
            }
            if(!isClosed) {
                closed.add(current);   //close the current element
                for (int i = 0; i < matriz.length; i++) {
                    if (matriz[current][i] != 0 && matriz[current][i] != -1) {
                        opened.add(i);
                    }
                }
            } else {
                path.removeLast();
            }
        }
        path.clear();
        return path;
    }

    public LinkedList<Integer> caminoMasLargo(int beginning, int destine) {
        LinkedList<Integer> path = new LinkedList<>();
        Stack<Integer> opened = new Stack<>();
        LinkedList closed = new LinkedList<>();
        boolean isClosed;

        setOrigin(beginning);
        setDestine(destine);
        opened.add(beginning);
        while(opened.size() > 0) {   //mientras hay nodos en la lista
            isClosed = false;
            int current = opened.pop();
            path.add(current);
            if(current == destine) {
                return generatePath(path);
            }

            Iterator<Integer> it = closed.iterator();
            while (it.hasNext()) {   //search if the element is already closed
                if (current == it.next()) {
                    isClosed = true;
                    break;
                }
            }
            if(!isClosed) {
                closed.add(current);   //close the current element
                for (int i = 0; i < matriz.length; i++) {
                    if (matriz[current][i] != 0 && matriz[current][i] != -1) {
                        opened.add(i);
                    }
                }
            } else {
                path.removeLast();
            }
        }
        path.clear();
        return path;
    }

    private LinkedList<Integer> generatePath(LinkedList<Integer> savedSteps) {
        /*Collections.reverse(savedSteps);
        int current = savedSteps.get(0);
        while(current != origin) {
            Iterator<Integer> it = savedSteps.iterator();
            while (it.hasNext()) {

            }
        }*/
        return savedSteps;
    }

    public int[][] getMatriz() {
        return matriz;
    }

    public int getOrigin() {
        return origin;
    }

    public void setOrigin(int origin) {
        this.origin = origin;
    }

    public int getDestine() {
        return destine;
    }

    public void setDestine(int destine) {
        this.destine = destine;
    }
}