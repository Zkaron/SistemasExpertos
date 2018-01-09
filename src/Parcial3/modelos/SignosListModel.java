package Parcial3.modelos;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.AbstractListModel;

/**
 *
 * @author luis
 */
public class SignosListModel extends AbstractListModel {
    protected LinkedList<Signo> lista = new LinkedList<>();

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        Signo s = lista.get(index);
        return s.getNombre();
    }
    
    public void addSigno(Signo s) {
        lista.add(s);
        this.fireIntervalAdded(this, getSize(), getSize() + 1);
    }
    
    public void removeSigno(int index) {
        lista.remove(index);
        this.fireIntervalRemoved(this, getSize(), getSize() + 1);
    }
    
    public Signo getSigno(int index) {
        return lista.get(index);
    }
    
}
