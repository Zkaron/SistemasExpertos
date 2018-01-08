package Parcial1.Proyecto;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * Created by Erik on 9/2/2017.
 */
public class Main extends JFrame implements Runnable {
    public static final String FRAME_TITLE = "Juego del Gato";
    public static boolean reiniciarJuego = false;
    public static boolean mostrarJugadas = false;
    public final int FRAME_WIDTH = 600;
    public final int FRAME_HEIGHT = 600;

    private JPanel panel;
    private Thread victoryThread;

    private TableroGato tablero;
    private PanelOpciones opc;

    public Main() {
        this.setTitle(FRAME_TITLE);
        this.setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        initComponents();

        victoryThread = new Thread(this);
        victoryThread.run();
    }

    public void initComponents() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        tablero = new TableroGato(new Dimension(FRAME_WIDTH, FRAME_HEIGHT / 5 * 4));
        opc = new PanelOpciones();

        panel.add(tablero);
        panel.add(opc);

        this.add(panel);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void reiniciarJuego() {
        this.remove(panel);
        this.revalidate();

        initComponents();
        reiniciarJuego = false;
    }

    public void mostrarJugadas() {
        Window windows[] = this.getOwnedWindows();
        for (Window win : windows) {
            win.setVisible(false);
        }
        LinkedList<LinkedList<Point>> caminos = new LinkedList<>();
        for (int i = 0; i < tablero.juego.tablero.length; i++) {
            for (int j = 0; j < tablero.juego.tablero.length; j++) {
                JDialog diag = new JDialog(this, "Jugadas");
                diag.setSize(300, 300);
                diag.setLocation(600 + (i * 300), 0 + (j * 300));
                LinkedList<Point> jugada = tablero.juego.caminoMasLargo(new Point(i, j));
                TableroGato gato = new TableroGato(new Dimension(diag.getWidth(), diag.getHeight()), jugada);
                if (gato.juego.casillasRestantes >= 1 && jugada.size() > 1) {
                    diag.add(gato);
                    diag.setVisible(true);
                }
            }
        }

        mostrarJugadas = false;
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

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void run() {
        while (true) {
            try {
                victoryThread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(reiniciarJuego) {
                reiniciarJuego();
            }
            if(mostrarJugadas) {
                mostrarJugadas();
            }
        }
    }
}
