package Parcial3;

import Parcial3.modelos.Diagnostico;
import Parcial3.modelos.Tratamiento;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Erik on 9/19/2017.
 */
public class Learn {

    HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> resultadosLocales;
    private HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> resultadosRemotos;
    private int numeroSintomas;
    private int numeroSignos;

    public Learn(HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> resultadosLocales, HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> resultadosAprender, int nSignos, int nSintomas) {
        this.resultadosLocales = resultadosLocales;
        this.resultadosRemotos = resultadosAprender;
        this.numeroSignos = nSignos;
        this.numeroSintomas = nSintomas;
    }

    public void actualizarConocimiento() {
        LinkedList<Diagnostico> diagnosticosLocales;
        LinkedList<Tratamiento> tratamientosLocales;
        LinkedList<Diagnostico> diagnosticosRemotos;
        LinkedList<Tratamiento> tratamientosRemotos;

        for(Map.Entry<LinkedList<Tratamiento>, LinkedList<Diagnostico>> entry : resultadosLocales.entrySet()) {
            tratamientosLocales = entry.getKey();
            diagnosticosLocales = entry.getValue();
        }

        for(Map.Entry<LinkedList<Tratamiento>, LinkedList<Diagnostico>> entry : resultadosRemotos.entrySet()) {
            tratamientosRemotos = entry.getKey();
            diagnosticosRemotos = entry.getValue();
        }


    }

}
