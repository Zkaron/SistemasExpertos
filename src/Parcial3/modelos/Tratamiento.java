package Parcial3.modelos;

/**
 * Created by Erik on 1/7/2018.
 */
public class Tratamiento {
    private int id;
    private String texto;
    private int diagnosticoId;

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

    public int getDiagnosticoId() {
        return diagnosticoId;
    }

    public void setDiagnosticoId(int diagnosticoId) {
        this.diagnosticoId = diagnosticoId;
    }
}
