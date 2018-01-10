package Parcial3;

import Parcial3.modelos.Diagnostico;
import Parcial3.modelos.Signo;
import Parcial3.modelos.Sintoma;
import Parcial3.modelos.Tratamiento;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Erik on 9/19/2017.
 */
public class Learn {

    HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> resultadosLocales;
    private HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> resultadosRemotos;
    private LinkedList<Signo> signosAgregados;
    private LinkedList<Sintoma> sintomasAgregados;

    LinkedList<Diagnostico> diagnosticosLocales = new LinkedList<>();
    LinkedList<Tratamiento> tratamientosLocales = new LinkedList<>();
    LinkedList<Diagnostico> diagnosticosRemotos = new LinkedList<>();
    LinkedList<Tratamiento> tratamientosRemotos = new LinkedList<>();

    private KnowledgeBase localBase;
    private KnowledgeBase remoteBase;

    LinkedList<Signo> signosLocales = new LinkedList<>();
    LinkedList<Sintoma> sintomasLocales = new LinkedList<>();
    LinkedList<Signo> signosRemotos = new LinkedList<>();
    LinkedList<Sintoma> sintomasRemotos = new LinkedList<>();

    private int diferenciasPermitidas = 3;

    public Learn(HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> resultadosLocales,
                 HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> resultadosAprender,
                 LinkedList<Signo> signosAgregados, LinkedList<Sintoma> sintomasAgregados,
                 String url, String user, String pwd) {
        this.resultadosLocales = resultadosLocales;
        this.resultadosRemotos = resultadosAprender;

        this.signosAgregados = signosAgregados;
        this.sintomasAgregados = sintomasAgregados;

        localBase = new KnowledgeBase();
        remoteBase = new KnowledgeBase(url, user, pwd);

        for(Signo s: signosAgregados) {
            try {
                signosLocales.add(localBase.search(s));
            } catch (SQLException e) {

            }
        }
        for(Sintoma s : sintomasAgregados) {
            try {
                sintomasLocales.add(localBase.search(s));
            } catch (SQLException e) {

            }
        }

        for(Signo s: signosAgregados) {
            try {
                signosRemotos.add(remoteBase.search(s));
            } catch (SQLException e) {

            }
        }
        for(Sintoma s : sintomasAgregados) {
            try {
                sintomasRemotos.add(remoteBase.search(s));
            } catch (SQLException e) {

            }
        }
    }

    public void actualizarConocimiento() {
        for(Map.Entry<LinkedList<Tratamiento>, LinkedList<Diagnostico>> entry : resultadosLocales.entrySet()) {
            tratamientosLocales = entry.getKey();
            diagnosticosLocales = entry.getValue();
        }

        for(Map.Entry<LinkedList<Tratamiento>, LinkedList<Diagnostico>> entry : resultadosRemotos.entrySet()) {
            tratamientosRemotos = entry.getKey();
            diagnosticosRemotos = entry.getValue();
        }

        if(diagnosticosRemotos.size() > 0) {
            if(diagnosticosRemotos.size() == diagnosticosLocales.size()) {
                for(int i = 0; i < diagnosticosRemotos.size(); i++) {
                    if(!diagnosticosRemotos.get(i).getTexto().equals(diagnosticosLocales.get(i).getTexto())) {
                        //No coincide el diagnostico, actualizalo
                        actualizarContenidoDiagnostico(diagnosticosLocales.get(i), diagnosticosRemotos.get(i));
                    } else {
                        //Diagnosticos son iguales, revisar los signos y sintomas

                    }
                }
//                if(tratamientosRemotos.size() > 0) {
//                    for (int i = 0; i < tratamientosRemotos.size(); i++) {
//                        if(!tratamientosRemotos.get(i).equals(tratamientosLocales.get(i))) {
//                            //Distinto tratamiento, actualizarlo
//                                return;
//                        }
//                    }
//                }
            } else {
//                if(diagnosticosRemotos.size() - diagnosticosLocales.size() >= diferenciasPermitidas) {
                    //Diagnostico distinto, la remota reemplaza local
                reemplazarDiagnosticos(diagnosticosLocales, diagnosticosRemotos);
//                }
                //no hacer cambios
            }

        } else {
            //No hay diagnosticos, entonces no hacer cambios
            return;
        }
    }

    private void actualizarContenidoDiagnostico(Diagnostico actual, Diagnostico reemplazo) {
        borrarDiagnosticos(actual);
        agregarDiagnosticos(reemplazo);
    }

    private void reemplazarDiagnosticos(LinkedList<Diagnostico> actuales, LinkedList<Diagnostico> nuevos) {
        for(Diagnostico a : actuales) {  //borra los actuales
            borrarDiagnosticos(a);
//            agregarDiagnosticos(a);
        }
        for(Diagnostico n : nuevos) {  //Agrega los nuevos
            agregarDiagnosticos(n);
        }
    }

    private void borrarDiagnosticos(Diagnostico borrar) {
//        try {
            borrarTratamiento(borrar); //Primero borramos el tratamiento
//            //borramos llaves foraneas del diagnostico
//            for(Signo s : signosLocales) {
//                localBase.delete(s, borrar);
//            }
//            for(Sintoma s : sintomasLocales) {
//                localBase.delete(s, borrar);
//            }
//
//            //Procedemos a borrar el diagnostico
//            localBase.delete(borrar);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    private void agregarDiagnosticos(Diagnostico agregar) {
        boolean agregado = false;
        try {   //Se guarda el diagnostico
            LinkedList<Diagnostico> aComparar = localBase.getDiagnosticos();
            for(Diagnostico d : aComparar) {
                if(d.getTexto().equals(agregar.getTexto())) {
                    agregado = true;
                    break;
                }
            }
            if(!agregado) {
                localBase.save(agregar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(!agregado) {
            Diagnostico diagnostico = new Diagnostico();
            try { // Se obtiene (junto con su ID)
                diagnostico = localBase.searchByName(agregar);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //Los signos y sintomas se vinculan al nuevo diagnostico
            try {
                for (Signo s : signosRemotos) {
                    localBase.save(s, diagnostico);
                }
                for (Sintoma s : sintomasRemotos) {
                    localBase.save(s, diagnostico);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            agregarTratamiento(diagnostico);
        }
    }

    public void borrarTratamiento(Diagnostico diagnostico) {
        for(Tratamiento t: tratamientosLocales) {
            try {
                localBase.delete(t, diagnostico);
            } catch (SQLException e) {

            }
        }
    }
    public void agregarTratamiento(Diagnostico diagnostico) {
        for(Tratamiento t : tratamientosRemotos) {
            t.setDiagnosticoId(diagnostico.getId());
            try {
                localBase.save(t);
            } catch (SQLException e) {

            }
        }
    }



}
