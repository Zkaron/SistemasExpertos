package Parcial3.modelos;

/**
 * Created by Erik on 1/7/2018.
 */
public class Diagnostico {
    private int id;
    private String texto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public int hashCode()
    {
        return texto.hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof Diagnostico)) {
            return false;
        }
        Diagnostico d = (Diagnostico)o;
        return d.getId() == id && d.getTexto().equals(this.getTexto());
    }
}
