package Parcial1.matrizAdyacente;

//import java.awt.*;
//import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

class Main {
    private int[][] matriz;
    private Scanner in;
    private int tamanioMatriz;

    /**
     * Clase principal que lee y muestra una matriz
     */
    public Main() {
        in = new Scanner(System.in);
        crearMatrizPorDefecto();
        tamanioMatriz = matriz.length;

        /*for(int i = 0; i < tamanioMatriz; i++) {
            for(int j = 0; j < tamanioMatriz; j++) {
                matriz[i][j] = 0;
            }
        }   //inicializa la matriz en ceros*/
    }

    private void crearMatrizPorDefecto() {
        matriz = new int[][]
                {{1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0}};
    }

    public void leer() {
        System.out.println("Ingrese cuantos nodos ingresar");
        tamanioMatriz = in.nextInt();
        matriz = new int[tamanioMatriz][tamanioMatriz];

        int count = 1;
        while(true) {
            if(count > tamanioMatriz) break;
            System.out.println("Ingrese Posicion del nodo " + count + " [del 0 al "
                    + (tamanioMatriz - 1) + "]"
                    + " --- presione s para salir");

            String input = in.next();
            if(input.equals("s")) break;
            int x = Integer.parseInt(input);
            int y = in.nextInt();
            System.out.println("Ingrese el nodo(valor)");
            matriz[x][y] = in.nextInt();
            count++;
        }
    }
    public void mostrar() {
        //System.out.print("[");
        System.out.println();
        for(int i = 0; i < tamanioMatriz; i++) {
            for(int j = 0; j < tamanioMatriz; j++) {
                System.out.print(matriz[i][j]);
                if(j != tamanioMatriz - 1) {
                    System.out.print(" ,");
                }
            }
            System.out.println();
        }
    }

    public void mostrarCamino(LinkedList<Integer> camino) {
        //System.out.println();
        for(int i = 0; i < camino.size(); i++) {
            System.out.print(camino.get(i));
            if (i != camino.size() - 1) {
                System.out.print("->");
            }
        }
        System.out.println();
    }

    public int menu() {
        System.out.println();
        int opc = 0;
        System.out.println("Menu");
        System.out.println("[1] Registrar");
        System.out.println("[2] Mostrar Matriz");
        System.out.println("[3] Mostrar Caminos");
        System.out.println("[4] Camino mas largo");
        System.out.println("[5] Camino mas corto");
        System.out.println("[6] Salir");
        System.out.println("Seleccionar opcion");
        opc = in.nextInt();
        return opc;
    }

    public static void main(String[] args) {
        Main main = new Main();
        int opc;
        int beggining;
        int destine;
        Adyacente ady;
        LinkedList<Integer> camino;

        do {
            opc = main.menu();
            switch(opc) {
                case 1:
                    main.leer();
                    break;
                case 2:
                    main.mostrar();
                    break;
                case 3:
                    beggining = 0;
                    destine = main.matriz.length - 1;
                    ady = new Adyacente(main.matriz);
                    for(; beggining != destine; beggining++) {
                        for(; destine != 0; destine--) {
                            camino = ady.caminoMasLargo(beggining, destine);
                            if (!camino.isEmpty()) {
                                System.out.print("Inicial[" + beggining + "], Destino[" + destine + "] => ");
                                main.mostrarCamino(camino);
                            }
                        /*camino = ady.caminoMasCorto(beggining, destine);
                        if(!camino.isEmpty()) {
                            main.mostrarCamino(camino);
                        }*/
                        }
                        destine = main.matriz.length - 1;
                    }
                    //main.mostrarCamino(ady.caminoMasCorto(beggining, destine));
                    break;
                case 4:
                    System.out.println("Ingrese nodo inicial");
                    beggining = main.in.nextInt();
                    System.out.println("Ingrese nodo destino");
                    destine = main.in.nextInt();
                    ady = new Adyacente(main.matriz);
                    camino = ady.caminoMasLargo(beggining, destine);
                    if(camino.isEmpty()) {
                        System.out.println("No se puede llegar al nodo destino");
                    } else {
                        main.mostrarCamino(camino);
                    }
                    break;
                case 5:
                    System.out.println("Ingrese nodo inicial");
                    beggining = main.in.nextInt();
                    System.out.println("Ingrese nodo destino");
                    destine = main.in.nextInt();
                    ady = new Adyacente(main.matriz);
                    camino = ady.caminoMasCorto(beggining, destine);
                    if(camino.isEmpty()) {
                        System.out.println("No se puede llegar al nodo destino");
                    } else {
                        main.mostrarCamino(camino);
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("No se lee correctamente la entrada");
                    break;
            }
        } while(opc != 6);
    }
}

