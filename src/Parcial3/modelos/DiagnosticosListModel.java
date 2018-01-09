package Parcial3.modelos;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.AbstractListModel;

/**
 *
 * @author luis
 */
public class DiagnosticosListModel extends AbstractListModel {
    private LinkedList<Diagnostico> lista = new LinkedList<>();

    @Override
    public int getSize() {
        return lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        Diagnostico d = lista.get(index);
        return d.getTexto();
    }
    
    public void addDiagnostico(Diagnostico s) {
        lista.add(s);
        this.fireIntervalAdded(this, getSize(), getSize() + 1);
    }
    
    public void removeDiagnostico(int index) {
        lista.remove(index);
        this.fireIntervalRemoved(this, getSize(), getSize() + 1);
    }
    
    public Diagnostico getDiagnostico(int index) {
        return lista.get(index);
    }
    
}
