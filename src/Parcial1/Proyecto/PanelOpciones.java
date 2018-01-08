package Parcial1.Proyecto;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Erik on 9/2/2017.
 */
public class PanelOpciones extends JPanel implements ActionListener {
    private final String REINICIAR_BUTTON = "Reiniciar";
    private final String MOSTRAR_JUGADAS_BUTTON = "Mostrar jugadas";
    JButton reiniciar_btn;
    JButton mostrar_jugadas_btn;

    public PanelOpciones() {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        reiniciar_btn = new JButton(REINICIAR_BUTTON);
        mostrar_jugadas_btn = new JButton(MOSTRAR_JUGADAS_BUTTON);

        reiniciar_btn.addActionListener(this);
        mostrar_jugadas_btn.addActionListener(this);

        this.add(reiniciar_btn);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(mostrar_jugadas_btn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            if(e.getSource().equals(reiniciar_btn)) {
                Main.reiniciarJuego = true;
            }
            if(e.getSource().equals(mostrar_jugadas_btn)) {
                Main.mostrarJugadas = true;
            }
        }
    }
}
