package Parcial1.Proyecto;

import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * Created by Erik on 9/2/2017.
 */
public class Celda extends JLabel{
    private int celdaX;
    private int celdaY;

    public Celda(int x, int y) {
        celdaX = x;
        celdaY = y;
    }

    public void setCeldaIcon(BufferedImage img) {
        this.setIcon(new ImageIcon(img));
    }

    public int getCeldaX() {
        return celdaX;
    }

    public void setCeldaX(int celdaX) {
        this.celdaX = celdaX;
    }

    public int getCeldaY() {
        return celdaY;
    }

    public void setCeldaY(int celdaY) {
        this.celdaY = celdaY;
    }
}
