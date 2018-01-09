package Parcial3.modelos;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Erik on 1/8/2018.
 */
public class ComboSignosListModel extends SignosListModel implements ComboBoxModel {
    String selection = null;

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (String)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }
}
