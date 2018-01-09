package Parcial3.modelos;

import javax.swing.*;

/**
 * Created by Erik on 1/8/2018.
 */
public class ComboSintomasListModel extends SintomasListModel implements ComboBoxModel {
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
