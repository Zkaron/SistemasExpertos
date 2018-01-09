package Parcial3.modelos;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.AbstractListModel;

/**
 *
 * @author luis
 */
public class SintomasListModel extends AbstractListModel {
    private LinkedList<Sintoma> lista = new LinkedList<>();

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        Sintoma s = lista.get(index);
        return s.getNombre();
    }
    
    public void addSintoma(Sintoma s) {
        lista.add(s);
        this.fireIntervalAdded(this, getSize(), getSize() + 1);
    }
    
    public void removeSintoma(int index) {
        lista.remove(index);
        this.fireIntervalRemoved(this, getSize(), getSize() + 1);
    }
    
    public Sintoma getSintoma(int index) {
        return lista.get(index);
    }
    
}
