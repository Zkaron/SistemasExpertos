package Parcial3;

import Parcial3.modelos.Diagnostico;
import Parcial3.modelos.Signo;
import Parcial3.modelos.Sintoma;
import Parcial3.modelos.Tratamiento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * Created by Erik on 9/19/2017.
 */
public class InferenceEngine {
    public int signosySintomasRequeridos;
    private LinkedList<Signo> signos;
    private LinkedList<Sintoma> sintomas;
    private HashMap<Diagnostico, Integer> map;
    private HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> resultadosLocales;

    private boolean isRemoteConnected = false;
    
    Connection conn = null;
    String url = "jdbc:mysql://localhost/diagnostico";
//    String url = "jdbc:mysql://192.168.1.68:3306/diagnostico";
    String user = "root";
    String pwd = "";

    
    public InferenceEngine(LinkedList<Signo> signos, LinkedList<Sintoma> sintomas) {
        this.signos = signos;
        this.sintomas = sintomas;
        map = new HashMap<>();
        
        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            conn =(Connection) DriverManager.getConnection(url, user, pwd);
            //JOptionPane.showMessageDialog(null, "Conectado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public InferenceEngine(LinkedList<Signo> signos, LinkedList<Sintoma> sintomas, HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> resultadosLocales, String url, String user, String pwd) {
        this.url = url;
        this.user = user;
        this.pwd = pwd;

        this.resultadosLocales = resultadosLocales;
        this.signos = signos;
        this.sintomas = sintomas;
        map = new HashMap<>();

        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            conn =(Connection) DriverManager.getConnection(url, user, pwd);
            //JOptionPane.showMessageDialog(null, "Conectado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión");
            e.printStackTrace();
            System.exit(0);
        }
        isRemoteConnected = true;
    }
    
    public HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> process() {
        HashMap<LinkedList<Tratamiento>, LinkedList<Diagnostico>> resultadosFinales = new HashMap<>();
        LinkedList<Tratamiento> tratamientosFinales = new LinkedList<>();
        LinkedList<Diagnostico> diagnosticosFinales = new LinkedList<>();

        for(int i = 0; i < signos.size(); i++) {
            LinkedList<Diagnostico> diagnosticos = new LinkedList<>();
            try {
                diagnosticos = queryDiagnosticoBySigno(signos.get(i));
            } catch(SQLException e) {
                e.printStackTrace();
            }

            for(int j = 0; j < diagnosticos.size(); j++) {
                if(!map.containsKey(diagnosticos.get(j))) {
                    map.put(diagnosticos.get(j), 1);
                } else {
                    map.replace(diagnosticos.get(j), map.get(diagnosticos.get(j)), map.get(diagnosticos.get(j)) + 1);
                }
            }
        }
        
        for(int i = 0; i < sintomas.size(); i++) {
            LinkedList<Diagnostico> diagnosticos = new LinkedList<>();
            try {
                diagnosticos = queryDiagnosticoBySintoma(sintomas.get(i));
            } catch(SQLException e) {
                e.printStackTrace();
            }
            for(int j = 0; j < diagnosticos.size(); j++) {
                if(!map.containsKey(diagnosticos.get(j))) {
                    map.put(diagnosticos.get(j), 1);
                } else {
                    map.replace(diagnosticos.get(j), map.get(diagnosticos.get(j)), map.get(diagnosticos.get(j)) + 1);
                }
            }
        }
        
        int maxValue = 0;
        for(Diagnostico d : map.keySet()) {
            if(maxValue < map.get(d)) {
                maxValue = map.get(d);
            }
        }
        
        if(maxValue > 0) {
            //Se obtienen todos los diagnosticos con mayor ponderacion
            for(Map.Entry<Diagnostico, Integer> entry : map.entrySet()) {
                if(entry.getValue() == maxValue) {
                    diagnosticosFinales.add(entry.getKey());
                }
            }
            
            //Se encuentran los tratamientos a través de los diagnosticos obtenidos
            for(Diagnostico d : diagnosticosFinales) {
                try {
                    tratamientosFinales = queryTratamientoByDiagnostico(d);
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            
            resultadosFinales.put(tratamientosFinales, diagnosticosFinales);
        }

        try {
            close();   //Close connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(isRemoteConnected) {
            Learn learn = new Learn(resultadosLocales, resultadosFinales, signos, sintomas, url, user, pwd);
            learn.actualizarConocimiento();
        }

        return resultadosFinales;
    }
    
    private LinkedList<Diagnostico> queryDiagnosticoBySigno(Signo signo) throws SQLException {
        LinkedList<Diagnostico> diagnosticos = new LinkedList<>();
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT (diagnostico_id) FROM signo_diagnostico WHERE signo_id='" + signo.getId() + "'");
        st.executeQuery();
        rs = st.getResultSet();

        LinkedList<Integer> diagnosticos_id = new LinkedList<>();
        while(rs.next()) {
            diagnosticos_id.add(rs.getInt(1));
        }
        for(int i = 0; i < diagnosticos_id.size(); i++) {
            st = conn.prepareStatement("SELECT * FROM diagnostico WHERE id='" + diagnosticos_id.get(i) + "'");
            st.executeQuery();
            rs = st.getResultSet();
            
            while (rs.next()) {
                Diagnostico d = new Diagnostico();
                d.setId(rs.getInt(1));
                d.setTexto(rs.getString(2));
                diagnosticos.add(d);
            }
        }
        return diagnosticos;
    }
    
    private LinkedList<Diagnostico> queryDiagnosticoBySintoma(Sintoma sintoma) throws SQLException {
        LinkedList<Diagnostico> diagnosticos = new LinkedList<>();
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT (diagnostico_id) FROM sintoma_diagnostico WHERE sintoma_id='" + sintoma.getId() + "'");
        st.executeQuery();
        rs = st.getResultSet();
        
        LinkedList<Integer> diagnosticos_id = new LinkedList<>();
        while(rs.next()) {
            diagnosticos_id.add(rs.getInt(1));
        }
        for(int i = 0; i < diagnosticos_id.size(); i++) {
            st = conn.prepareStatement("SELECT * FROM diagnostico WHERE id='" + diagnosticos_id.get(i) + "'");
            st.executeQuery();
            rs = st.getResultSet();
            
            while (rs.next()) {
                Diagnostico d = new Diagnostico();
                d.setId(rs.getInt(1));
                d.setTexto(rs.getString(2));
                diagnosticos.add(d);
            }
        }

        return diagnosticos;
    }
    
    private LinkedList<Tratamiento> queryTratamientoByDiagnostico(Diagnostico diagnostico) throws SQLException {
        LinkedList<Tratamiento> tratamientos = new LinkedList<>();
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT * FROM tratamiento WHERE diagnostico_id='" + diagnostico.getId() + "'");
        st.executeQuery();
        rs = st.getResultSet();
        
        while(rs.next()) {
           Tratamiento t = new Tratamiento();
           t.setId(rs.getInt(1));
           t.setTexto(rs.getString(2));
           t.setDiagnosticoId(rs.getInt(3));
           tratamientos.add(t);
        }
        
        return tratamientos;
    }

    public void close() throws SQLException {
        conn.close();
    }
}
