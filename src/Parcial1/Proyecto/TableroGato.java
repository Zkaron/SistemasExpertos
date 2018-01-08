package Parcial1.Proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

/**
 * Created by Erik on 9/2/2017.
 * 1 es jugador 1
 * 2 es jugador 2
 */
public class TableroGato extends JPanel implements MouseListener {
     private final String fileSeparator = System.getProperty("file.separator");
     private final String currDir = System.getProperty("user.dir") + fileSeparator + "src" + fileSeparator + "Parcial1/Proyecto";
     private final String BLANK = currDir + fileSeparator + "res" + fileSeparator + "blank-image.jpg";
     private final String GATO_BLANCO = currDir + fileSeparator + "res" + fileSeparator + "gato-blanco.jpg";
     private final String GATO_NEGRO = currDir + fileSeparator + "res" + fileSeparator + "gato-negro.jpg";

    public final int NUMBER_OF_ROWS = 3;
    public final int NUMBER_OF_COLUMNS = 3;

     public boolean juegoTerminado;
     public int turnoJugador;

     private Image imgCeldaX;
     private Image imgCeldaY;
     private Image imgBlank;

     public Celda celdas[][];
     public JugarGato juego;

     private OpenImageManager imgManager;

     public TableroGato(Dimension d) {
         this.setSize(d);
         imgManager = new OpenImageManager();
         turnoJugador = 0;
         juegoTerminado = false;
         juego = new JugarGato(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);

         this.setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
         abrirImagenes();
         crearCeldas();
     }

    public TableroGato(Dimension d, LinkedList<Point> jugadas) {
        this.setSize(d);
        imgManager = new OpenImageManager();
        turnoJugador = 1;
        juegoTerminado = false;
        juego = new JugarGato(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS);

        this.setLayout(new GridLayout(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS));
        abrirImagenes();
        crearCeldas();

        int i = 0;
        for(Point jugada : jugadas) {
            if(i % 2 == 0) {
                juego.setJugadaTablero(jugada, 1);
                celdas[jugada.x][jugada.y].setIcon(new ImageIcon(imgCeldaX));
            } else {
                juego.setJugadaTablero(jugada, 2);
                celdas[jugada.x][jugada.y].setIcon(new ImageIcon(imgCeldaY));
            }
            i++;
        }
    }

    private void abrirImagenes() {
        imgCeldaX = imgManager.openFile(GATO_BLANCO).getScaledInstance(this.getWidth() /3, this.getHeight() / 3, WIDTH);
        imgCeldaY = imgManager.openFile(GATO_NEGRO).getScaledInstance(this.getWidth() /3, this.getHeight() / 3, WIDTH);
        imgBlank = imgManager.openFile(BLANK).getScaledInstance(this.getWidth() /3, this.getHeight() / 3, WIDTH);
    }

     private void crearCeldas() {
         celdas = new Celda[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
         for(int i = 0; i < NUMBER_OF_COLUMNS; i++) {
             for(int j = 0; j < NUMBER_OF_ROWS; j++) {
                 celdas[i][j] = new Celda(i, j);
                 celdas[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                 celdas[i][j].setIcon(new ImageIcon(imgBlank));
                 celdas[i][j].addMouseListener(this);
                 this.add(celdas[i][j]);
             }
         }
     }

    @Override
    public void mouseClicked(MouseEvent e) {
         if(!juegoTerminado) {  //mientras ningun jugador haya ganado
             if (e.getSource() instanceof Celda) {
                 if (juego.tablero[((Celda) e.getSource()).getCeldaX()][((Celda) e.getSource()).getCeldaY()] == 0) {   //Si no se ha jugado en la casilla
                     juego.setJugadaTablero(new Point(((Celda) e.getSource()).getCeldaX(),
                             ((Celda) e.getSource()).getCeldaY()), turnoJugador);   //jugar como el jugador actual

                     if (turnoJugador == 0) {
                         ((Celda) e.getSource()).setIcon(new ImageIcon(imgCeldaX));
                         juegoTerminado = juego.juegoTerminadoP1(juego.tablero);   //revisa si jugador ha ganado
                         if(juegoTerminado) {
                             JOptionPane.showMessageDialog(this, "Jugador " + (turnoJugador + 1) + " gana!");
                         }

                         turnoJugador = 1;
                     } else if (turnoJugador == 1) {
                         ((JLabel) e.getSource()).setIcon(new ImageIcon(imgCeldaY));
                         juegoTerminado = juego.juegoTerminadoP2(juego.tablero);   //revisa si jugador ha ganado
                         if(juegoTerminado) {
                             JOptionPane.showMessageDialog(this, "Jugador " + (turnoJugador + 1) + " gana!");
                         }

                         turnoJugador = 0;
                     }
                 }   //  La celda seleccionada no tiene icono
                 else {
                    //No hacer nada
                 }   // Ya ha sido jugada esa celda
             } //Se ha seleccionado una celda del tablero
         } else {
             JOptionPane.showMessageDialog(this, "Juego Terminado." +
             "\nPulse reiniciar para iniciar una nueva partida");
         }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
