package Parcial3;

import Parcial3.modelos.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Erik on 9/19/2017.
 */
public class Learn {

//    private LinkedList<HashMap<Diagnostico, LinkedList<Tratamiento>>> resultadosLocales;
    private LinkedList<HashMap<Diagnostico, LinkedList<Tratamiento>>> resultadosRemotos;
    private LinkedList<Signo> signosAgregados;
    private LinkedList<Sintoma> sintomasAgregados;

//    LinkedList<Diagnostico> diagnosticosLocales = new LinkedList<>();
//    LinkedList<Tratamiento> tratamientosLocales = new LinkedList<>();
//    LinkedList<Diagnostico> diagnosticosRemotos = new LinkedList<>();
//    LinkedList<Tratamiento> tratamientosRemotos = new LinkedList<>();

    private KnowledgeBase localBase;
    private KnowledgeBase remoteBase;

    LinkedList<Signo> signosLocales = new LinkedList<>();
    LinkedList<Sintoma> sintomasLocales = new LinkedList<>();
    LinkedList<Signo> signosRemotos = new LinkedList<>();
    LinkedList<Sintoma> sintomasRemotos = new LinkedList<>();


    public Learn(LinkedList<HashMap<Diagnostico, LinkedList<Tratamiento>>> resultadosLocales,
                 LinkedList<HashMap<Diagnostico, LinkedList<Tratamiento>>> resultadosAprender,
                 LinkedList<Signo> signosAgregados, LinkedList<Sintoma> sintomasAgregados,
                 String url, String user, String pwd) {

//        this.resultadosLocales = resultadosLocales;
        this.resultadosRemotos = resultadosAprender;
        this.signosAgregados = signosAgregados;
        this.sintomasAgregados = sintomasAgregados;

        localBase = new KnowledgeBase();
        remoteBase = new KnowledgeBase(url, user, pwd);

        //Se agregan a una lista los signos y sintomas locales
//        for(Signo s: signosAgregados) {
//            try {
//                signosLocales.add(localBase.search(s));
//            } catch (SQLException e) {
//            }
//        }
//        for(Sintoma s : sintomasAgregados) {
//            try {
//                sintomasLocales.add(localBase.search(s));
//            } catch (SQLException e) {
//            }
//        }
//
//        //Se agregan a una lista los signos y sintomas locales
//        for(Signo s: signosAgregados) {
//            try {
//                signosRemotos.add(remoteBase.search(s));
//            } catch (SQLException e) {
//            }
//        }
//        for(Sintoma s : sintomasAgregados) {
//            try {
//                sintomasRemotos.add(remoteBase.search(s));
//            } catch (SQLException e) {
//            }
//        }
    }

    public void actualizarConocimiento() {
        if(resultadosRemotos.size() > 0) {
//            if (resultadosRemotos.size() == resultadosLocales.size()) {   //misma cantidad de diagnosticos obtenidos
                for (HashMap<Diagnostico, LinkedList<Tratamiento>> map : resultadosRemotos) {
                    for (Map.Entry<Diagnostico, LinkedList<Tratamiento>> entry : map.entrySet()) {
//                        if(!entry.getKey().getTexto().equals(entry.getKey().getTexto())) {
                            actualizarContenidoDiagnostico(entry);
//                        }
//                        diagnosticosLocales.add(entry.getKey());
//                        tratamientosLocales = entry.getValue();
                    }
                }
//            } else {   //Tamaños distintos
//                if(resultadosRemotos.size() > resultadosLocales.size()) {
//                    //Agregar diagnosticos
//                    for(HashMap<Diagnostico, LinkedList<Tratamiento>> map : resultadosRemotos) {
//                        for (Map.Entry<Diagnostico, LinkedList<Tratamiento>> entry : map.entrySet()) {
//                            actualizarContenidoDiagnostico(entry);
//                        }
//                    }
//                } else {
//                    //Eliminar diagnosticos
//                }
//            }
        }
        else {
            //No hay datos que actualizar
        }
    }

    private void actualizarContenidoDiagnostico(Map.Entry<Diagnostico, LinkedList<Tratamiento>> entry) {
        Diagnostico diagnostico = new Diagnostico();
        boolean existeDiagnostico = false;
        try {
            LinkedList<Diagnostico> diagnosticoComparar = localBase.getDiagnosticos();
            for(Diagnostico d : diagnosticoComparar) {
                if(d.getTexto().equals(entry.getKey().getTexto())) {   //Busca si el diagnostico remoto ya existe en el local
                    existeDiagnostico = true;
                    diagnostico = d;
                    break;
                }
            }
            if(!existeDiagnostico) {   //Agrega el diagnostico y si existe su tratamiento  (Seguro debo revisar tratamietos independientemente de si existe o no)
                localBase.save(entry.getKey()); //guarda diagnostico
                diagnostico = localBase.searchByName(entry.getKey()); // Se obtiene el diagnostico para tener el ID
                boolean isEqual = false;

                //Guardar tratamientos del diagnostico agregado
                LinkedList<Tratamiento> tratamientoComparar = localBase.getTratamientos();
                for(int i = 0; i < entry.getValue().size(); i++) {
                    for(Tratamiento t : tratamientoComparar) {
                        if(t.getTexto().equals(entry.getValue().get(i).getTexto())) {
                            isEqual = true;
                            break;
                        }
                    }
//                    if(tratamientoComparar.get(i).getTexto().equals(entry.getValue().get(i).getTexto()) &&
//                            tratamientoComparar.get(i).getDiagnosticoId() == entry.getValue().get(i).getDiagnosticoId()) {
                    if(isEqual)
                        continue; //Si son iguales no agregarlos

                    entry.getValue().get(i).setDiagnosticoId(diagnostico.getId());  //El id apunta al diagnostico equivalente local
                    localBase.save(entry.getValue().get(i));  //son distintos, agregarlo a lista tratamientos
//                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        if(existeDiagnostico) {   //Se revisa si los signos y sintomas son los mismos
            //Listas de los identificadores
            LinkedList<SignoDiagnostico> signosDiagnosticoLocal = new LinkedList<>();
            LinkedList<SintomaDiagnostico> sintomasDiagnosticoLocal = new LinkedList<>();
            LinkedList<SignoDiagnostico> signosDiagnosticoRemoto = new LinkedList<>();
            LinkedList<SintomaDiagnostico> sintomasDiagnosticoRemoto = new LinkedList<>();
            //Listas con los objetos
            LinkedList<Signo> signosLocales;
            LinkedList<Sintoma> sintomasLocales;
            LinkedList<Signo> signosRemotos;
            LinkedList<Sintoma> sintomasRemotos;

            //Se obtienen las relaciones de signos y sintomas con diagnostico
            try {
                signosDiagnosticoLocal = localBase.searchSignoDiagnosticoByDiagnostico(diagnostico);
                sintomasDiagnosticoLocal = localBase.searchSintomaDiagnosticoByDiagnostico(diagnostico);

                signosDiagnosticoRemoto = remoteBase.searchSignoDiagnosticoByDiagnostico(entry.getKey());
                sintomasDiagnosticoRemoto = remoteBase.searchSintomaDiagnosticoByDiagnostico(entry.getKey());
            } catch (SQLException e) {
            }

            //Se comparan los datos locales con los existentes remotos, asi se agregan signos y sintomas nuevos

//            if(signosDiagnosticoLocal.size() != signosDiagnosticoRemoto.size()) {  //Actualizar signos locales (SEGURO SE GENERARÄN SIGNOS)
                /*
                  TAMBIEN SE PUEDE OPTAR POR TENER UN HILO ESCUCHANDO CAMBIOS EN SIGNOS Y SINTOMAS
                3. se obtienen todos los signos locales (actualiza ids)
                4. compara texto de todos los signos locales contra el de los añadidos remotamente
                5.
                 */
                //-----------1. Guardar en listas los signos y sintomas locales y remotos -----------*/
                //Se obtienen todos los signos y sintomas del diagnostico local
                signosLocales = obtenerSignosDeSignosDiagnostico(signosDiagnosticoLocal);
                sintomasLocales = obtenerSintomasDeSintomaDiagnostico(sintomasDiagnosticoLocal);
                //Se obtienen todos los signos y sintomas del diagnostico remoto
                signosRemotos = obtenerSignosDeSignosDiagnostico(signosDiagnosticoRemoto);
                sintomasRemotos = obtenerSintomasDeSintomaDiagnostico(sintomasDiagnosticoRemoto);

                /*-----------2. Comparar si existe o no el signo/sintoma respecto el remoto al local
                                2.1 Si existe no ocurre nada
                                2.2 Si no existe, se agrega
                */
                try {
                    LinkedList<Signo> todosSignosLocales = localBase.getSignos();
                    LinkedList<Sintoma> todosSintomasLocales = localBase.getSintomas();

                    //Se guardan en la base los signos y sintomas que no existan localmente
                    guardarSignoBaseLocalSiNoExiste(todosSignosLocales, signosRemotos);
                    guardarSintomaBaseLocalSiNoExiste(todosSintomasLocales, sintomasRemotos);
                } catch (SQLException e) {
                }

                // Se actualizan las listas de signos y sintomas locales
                signosLocales = new LinkedList<>();
                sintomasLocales = new LinkedList<>();
                for(Signo s : signosRemotos) {
                    try {
                        signosLocales.add(localBase.searchByName(s));
                    } catch (SQLException e) {
                    }
                }
                for(Sintoma s : sintomasRemotos) {
                    try {
                        sintomasLocales.add(localBase.searchByName(s));
                    } catch (SQLException e) {
                    }
                }

                //Borrar todos los signos_diagnostico y sintomas_diagnostico locales relacionados al diagnostico
                try {
                    for(SignoDiagnostico sd : signosDiagnosticoLocal) {
                        localBase.deleteSignoDiagnostico(sd);
                    }
                    for(SintomaDiagnostico sd : sintomasDiagnosticoLocal) {
                        localBase.deleteSintomaDiagnostico(sd);
                    }
//                    for(SignoDiagnostico sd : signosDiagnosticoRemoto) {   //SE deben reemplazar por el equivalente local
//                        Signo s = new Signo();
//                        s.setId(sd.getSignoId());
//                    }
                } catch (SQLException e) {
                }

                //Se agrega la nueva version de las relaciones
                try {
                    for(Signo s : signosLocales) {
                        localBase.save(s, diagnostico);
                    }
                    for(Sintoma s : sintomasLocales) {
                        localBase.save(s, diagnostico);
                    }
                } catch (SQLException e) {
                }
//            }
//        } else {   //Los signos y sintomas se vinculan al nuevo diagnostico
//            try {
//                for (Signo s : signosRemotos) {
//                    localBase.save(s, diagnostico);
//                }
//                for (Sintoma s : sintomasRemotos) {
//                    localBase.save(s, diagnostico);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }

    private LinkedList<Signo> obtenerSignosDeSignosDiagnostico(LinkedList<SignoDiagnostico> signoDiagnosticos) {
        LinkedList<Signo> signos = new LinkedList<>();
        try {
            for(SignoDiagnostico sd : signoDiagnosticos) {
                Signo s = new Signo();
                s.setId(sd.getSignoId());
                s = localBase.search(s);
                signos.add(s);
            }
        } catch (SQLException e) {
        }
        return signos;
    }

    private LinkedList<Sintoma> obtenerSintomasDeSintomaDiagnostico(LinkedList<SintomaDiagnostico> sintomaDiagnosticos) {
        LinkedList<Sintoma> sintomas = new LinkedList<>();
        try {
            for(SintomaDiagnostico sd : sintomaDiagnosticos) {
                Sintoma s = new Sintoma();
                s.setId(sd.getSintomaId());
                s = localBase.search(s);
                sintomas.add(s);
            }
        } catch (SQLException e) {
        }
        return sintomas;
    }


    /**
     * Agrega los sintomas que no existan en la base local pero si en la remota
     * @param signosLocales
     * @param signosRemotos
     */
    private void guardarSignoBaseLocalSiNoExiste(LinkedList<Signo> signosLocales, LinkedList<Signo> signosRemotos) {
        for(Signo sRemoto : signosRemotos) {
            boolean existeLocal = false;
            for(Signo sLocal : signosLocales) {
                if(sRemoto.getNombre().equals(sLocal.getNombre())) {
                    existeLocal = true;
                    break;
                }
            }
            if(!existeLocal) {
                try {
                    localBase.save(sRemoto);
                } catch (SQLException e) {
                }
            }
        }
    }

    private void guardarSintomaBaseLocalSiNoExiste(LinkedList<Sintoma> sintomasLocales, LinkedList<Sintoma> sintomasRemotos) {
        for(Sintoma sRemoto : sintomasRemotos) {
            boolean existeLocal = false;
            for(Sintoma sLocal : sintomasLocales) {
                if(sRemoto.getNombre().equals(sLocal.getNombre())) {
                    existeLocal = true;
                    break;
                }
            }
            if(!existeLocal) {
                try {
                    localBase.save(sRemoto);
                } catch (SQLException e) {
                }
            }
        }
    }

    public void actualizarBDSintomas(LinkedList<Sintoma> sintomasRemotos) {

    }
}
