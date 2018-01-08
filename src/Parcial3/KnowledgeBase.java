package Parcial3;

import javax.swing.*;
import java.sql.*;
import java.util.LinkedList;

/**
 * Created by Erik on 9/19/2017.
 */
public class KnowledgeBase {
        Connection conn = null;
    String url = "jdbc:mysql://localhost:3306/diagnostico";
    String user = "root";
    String pwd = "";

    public KnowledgeBase() {
        try {
            DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
            Connection conn =(Connection) DriverManager.getConnection(url, user, pwd);
            JOptionPane.showMessageDialog(null, "Conectado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la conexión");
            e.printStackTrace();
        }
    }

    //-------------------PACIENTES---------------------------//

    public void save(Paciente p) throws SQLException {
        PreparedStatement st = conn.prepareStatement("INSERT INTO paciente VALUES(?,?,?,?,?)");
//        st.setInt(1, p.getId());
        st.setString(1, p.getNombre());
        st.setString(2, p.getDireccion());
        st.setString(3,  p.getCiudad());
        st.setString(4, p.getTelefono());
        st.setString(5, p.getEmail());
        st.executeUpdate();

//        ResultSet rs = st.executeQuery("select last_insert_id() as last_id from paciente");
//        return rs.getInt("last_id");
    }

    public LinkedList<Paciente> getPacientes() throws SQLException {
        LinkedList<Paciente> results = new LinkedList<>();
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT * FROM paciente");
        st.executeQuery();
        rs = st.getResultSet();

        while (rs.next()) {
            Paciente p = new Paciente();
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            p.setDireccion(rs.getString(3));
            p.setCiudad(rs.getString(4));
            p.setTelefono(rs.getString(5));
            p.setEmail(rs.getString(6));
            results.add(p);
        }

        return results;
    }

    public Paciente search(Paciente p) throws SQLException {
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT * FROM paciente WHERE id='" + p.getId() + "'");
        st.executeQuery();
        rs = st.getResultSet();

        while (rs.next()) {
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            p.setDireccion(rs.getString(3));
            p.setCiudad(rs.getString(4));
            p.setTelefono(rs.getString(5));
            p.setEmail(rs.getString(6));
        }
        return p;
    }

    public void delete(Paciente p) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM paciente WHERE id='" + p.getId() + "'");
        st.execute();
    }

    public void update(Paciente p) throws SQLException {
        String nombre = "nombre='" + p.getNombre() + "',";
        String direccion = "direccion='" + p.getDireccion() + "', ";
        String ciudad = "ciudad='" + p.getCiudad() + "', ";
        String telefono = "telefono='" + p.getTelefono() + "', ";
        String email = "email='" + p.getEmail() + "' ";

        PreparedStatement st = conn.prepareStatement("UPDATE paciente SET " +
                nombre +
                direccion +
                ciudad +
                telefono +
                email +
                "WHERE id='" + p.getId() + "'");
        st.executeUpdate();
    }

    //-------------------DIAGNOSTICOS---------------------------//

    public void save(Diagnostico d) throws SQLException {
        PreparedStatement st = conn.prepareStatement("INSERT INTO diagnostico VALUES(?)");
        st.setString(1, d.getTexto());
        st.executeUpdate();
    }

    public LinkedList<Diagnostico> getDiagnosticos() throws SQLException {
        LinkedList<Diagnostico> results = new LinkedList<>();
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT * FROM diagnostico");
        st.executeQuery();
        rs = st.getResultSet();

        while (rs.next()) {
            Diagnostico d = new Diagnostico();
            d.setId(rs.getInt(1));
            d.setTexto(rs.getString(2));
            results.add(d);
        }

        return results;
    }

    public Diagnostico search(Diagnostico d) throws SQLException {
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT * FROM diagnostico WHERE id='" + d.getId() + "'");
        st.executeQuery();
        rs = st.getResultSet();

        while (rs.next()) {
            d.setId(rs.getInt(1));
            d.setTexto(rs.getString(2));
        }
        return d;
    }

    public void delete(Diagnostico d) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM diagnostico WHERE id='" + d.getId() + "'");
        st.execute();
    }

    public void update(Diagnostico d) throws SQLException {
        String texto = "texto='" + d.getTexto() + "',";

        PreparedStatement st = conn.prepareStatement("UPDATE diagnostico SET " +
                texto +
                "WHERE id='" + d.getId() + "'");
        st.executeUpdate();
    }

    //-------------------TRATAMIENTOS---------------------------//

    public void save(Tratamiento t) throws SQLException {
        PreparedStatement st = conn.prepareStatement("INSERT INTO tratamiento VALUES(?,?)");
        st.setString(1, t.getTexto());
        st.setInt(2, t.getDiagnosticoId());
        st.executeUpdate();
    }

    public LinkedList<Tratamiento> getTratamientos() throws SQLException {
        LinkedList<Tratamiento> results = new LinkedList<>();
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT * FROM tratamiento");
        st.executeQuery();
        rs = st.getResultSet();

        while (rs.next()) {
            Tratamiento t = new Tratamiento();
            t.setId(rs.getInt(1));
            t.setTexto(rs.getString(2));
            t.setDiagnosticoId(rs.getInt(3));
            results.add(t);
        }

        return results;
    }

    public Tratamiento search(Tratamiento t) throws SQLException {
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT * FROM tratamiento WHERE id='" + t.getId() + "'");
        st.executeQuery();
        rs = st.getResultSet();

        while (rs.next()) {
            t.setId(rs.getInt(1));
            t.setTexto(rs.getString(2));
            t.setDiagnosticoId(rs.getInt(3));
        }
        return t;
    }

    public void delete(Tratamiento t) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM tratamiento WHERE id='" + t.getId() + "'");
        st.execute();
    }

    public void update(Tratamiento t) throws SQLException {
        String texto = "texto='" + t.getTexto() + "',";

        PreparedStatement st = conn.prepareStatement("UPDATE tratamiento SET " +
                texto +
                "WHERE id='" + t.getId() + "'");
        st.executeUpdate();
    }

    //-------------------SIGNOS---------------------------//

    public void save(Signo s) throws SQLException {
        PreparedStatement st = conn.prepareStatement("INSERT INTO signo VALUES(?)");
        st.setString(1, s.getNombre());
        st.executeUpdate();
    }

    public LinkedList<Signo> getSignos() throws SQLException {
        LinkedList<Signo> results = new LinkedList<>();
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT * FROM signo");
        st.executeQuery();
        rs = st.getResultSet();

        while (rs.next()) {
            Signo s = new Signo();
            s.setId(rs.getInt(1));
            s.setNombre(rs.getString(2));
            results.add(s);
        }

        return results;
    }

    public Signo search(Signo s) throws SQLException {
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT * FROM signo WHERE id='" + s.getId() + "'");
        st.executeQuery();
        rs = st.getResultSet();

        while (rs.next()) {
            s.setId(rs.getInt(1));
            s.setNombre(rs.getString(2));
        }
        return s;
    }

    public void delete(Signo s) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM signo WHERE id='" + s.getId() + "'");
        st.execute();
    }

    public void update(Signo s) throws SQLException {
        String nombre = "nombre='" + s.getNombre() + "',";

        PreparedStatement st = conn.prepareStatement("UPDATE signo SET " +
                nombre +
                "WHERE id='" + s.getId() + "'");
        st.executeUpdate();
    }

    //-------------------SINTOMAS---------------------------//

    public void save(Sintoma s) throws SQLException {
        PreparedStatement st = conn.prepareStatement("INSERT INTO sintoma VALUES(?)");
        st.setString(1, s.getNombre());
        st.executeUpdate();
    }

    public LinkedList<Sintoma> getSintomas() throws SQLException {
        LinkedList<Sintoma> results = new LinkedList<>();
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT * FROM sintoma");
        st.executeQuery();
        rs = st.getResultSet();

        while (rs.next()) {
            Sintoma s = new Sintoma();
            s.setId(rs.getInt(1));
            s.setNombre(rs.getString(2));
            results.add(s);
        }

        return results;
    }

    public Sintoma search(Sintoma s) throws SQLException {
        ResultSet rs;
        PreparedStatement st = conn.prepareStatement("SELECT * FROM sintoma WHERE id='" + s.getId() + "'");
        st.executeQuery();
        rs = st.getResultSet();

        while (rs.next()) {
            s.setId(rs.getInt(1));
            s.setNombre(rs.getString(2));
        }
        return s;
    }

    public void delete(Sintoma s) throws SQLException {
        PreparedStatement st = conn.prepareStatement("DELETE FROM sintoma WHERE id='" + s.getId() + "'");
        st.execute();
    }

    public void update(Sintoma s) throws SQLException {
        String nombre = "nombre='" + s.getNombre() + "',";

        PreparedStatement st = conn.prepareStatement("UPDATE sintoma SET " +
                nombre +
                "WHERE id='" + s.getId() + "'");
        st.executeUpdate();
    }
}