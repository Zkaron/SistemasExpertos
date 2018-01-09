package Parcial3;

import Parcial3.modelos.*;

import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.*;

public class inicio extends JFrame {

    KnowledgeBase db;
    Paciente paciente;
    Tratamiento tratamiento;
    Diagnostico diagnostico;
    Sintoma sintoma;
    Signo signo;

    private LinkedList<Paciente> pacientesList;
    private LinkedList<Tratamiento> tratamientosList;
    private LinkedList<Diagnostico> diagnosticosList;
    private LinkedList<Signo> signosList;
    private LinkedList<Sintoma> sintomasList;

    private LinkedList<Tratamiento> agregadosTratamiento;
    private LinkedList<Signo> agregadosSigno;
    private LinkedList<Sintoma> agregadosSintoma;

    private SignosListModel list_Signo_Diagnostico;
    private SintomasListModel list_Sintoma_Diagnostico;
    private DiagnosticosListModel list_Diagnostico;
    
    private ComboSignosListModel combo_list_Signo_Diagnostico;
    private ComboSintomasListModel combo_list_Sintoma_Diagnostico;
    private ComboDiagnosticosListModel combo_list_Diagnostico;

    public inicio() {
        pacientesList = new LinkedList<>();
        tratamientosList = new LinkedList<>();
        diagnosticosList = new LinkedList<>();
        signosList = new LinkedList<>();
        sintomasList = new LinkedList<>();

        agregadosTratamiento = new LinkedList<>();
        agregadosSigno = new LinkedList<>();
        agregadosSintoma = new LinkedList<>();

        list_Signo_Diagnostico = new SignosListModel();
        list_Sintoma_Diagnostico = new SintomasListModel();
        list_Diagnostico = new DiagnosticosListModel();
        
        combo_list_Signo_Diagnostico = new ComboSignosListModel();
        combo_list_Sintoma_Diagnostico = new ComboSintomasListModel();
        combo_list_Diagnostico = new ComboDiagnosticosListModel();

        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Sistema diagnostico");
        db = new KnowledgeBase();
        paciente = new Paciente();
        tratamiento = new Tratamiento();
        diagnostico = new Diagnostico();
        sintoma = new Sintoma();
        signo = new Signo();
        
        jList_Signo_Diagnostico.setModel(list_Signo_Diagnostico);
        jList_Sintoma_Diagnostico.setModel(list_Sintoma_Diagnostico);
        jList_Tratamiento_Diagnostico.setModel(list_Diagnostico);
        
        jcb_Signo_Diagnostico.setModel(combo_list_Signo_Diagnostico);
        jcb_Sintoma_Diagnostico.setModel(combo_list_Sintoma_Diagnostico);
        jcb_Tratamiento_Diagnostico.setModel(combo_list_Diagnostico);
        
        cargarPacientes();
        cargarSignos();
        cargarSintomas();
        cargarDiagnosticos();
       
        index();

    }

    public void index(){
        //Pacientes
        jbtnNuevo.setEnabled(true);
        jbtnGuardar.setEnabled(false);
        jbtnCancelar.setEnabled(false);
        jbtnEditar.setEnabled(false);
        jbtnBorrar.setEnabled(false);
        //Tratamientos
        jbtnNuevo_Enf.setEnabled(true);
        jbtnGuardar_Enf.setEnabled(false);
        jbtnCancelar_Enf.setEnabled(false);
        jbtnEditar_Enf.setEnabled(false);
        jbtnBorrar_Enf.setEnabled(false);
        //Diagnostico
        jbtnNuevo_Diagnostico.setEnabled(true);
        jbtnCancelar_Diagnostico.setEnabled(false);
        jbtnGenerar.setEnabled(false);
        jbtnAgregar_Signo_Diagnostico.setEnabled(false);
        jbtnAgregar_Sintoma_Diagnostico.setEnabled(false);
        jbtnEliminar_Signo_Diagnostico.setEnabled(false);
        jbtnEliminar_Sintoma_Diagnostico.setEnabled(false);
        //Sinomas
        jbtnNuevo_Sintoma.setEnabled(true);
        jbtnGuardar_Sintoma.setEnabled(false);
        jbtnCancelar_Sintoma.setEnabled(false);
        jbtnEditar_Sintoma.setEnabled(false);
        jbtnBorrar_Sintoma.setEnabled(false);
        //Signos
        jbtnNuevo_Signo.setEnabled(true);
        jbtnGuardar_Signo.setEnabled(false);
        jbtnCancelar_Signo.setEnabled(false);
        jbtnEditar_Signo.setEnabled(false);
        jbtnBorrar_Signo.setEnabled(false);

        limpiar_formularios();
        deshabilitar_formularios();

    }

    public void deshabilitar_formularios() {
        //Pacientes
        jtxtID.setEditable(false);
        jtxtNombre.setEditable(false);
        jtxtDireccion.setEditable(false);
        jtxtCiudad.setEditable(false);
        jtxtTelefono.setEditable(false);
        jtxtEmail.setEditable(false);
        //Tratamientos
        jtxtID_Enf.setEditable(false);
        jTextAreaTratamiento.setEditable(false);
        //Diagnostico
        jtxtID_Diagnostico.setEditable(false);
        jtxtDiagnostico.setEditable(false);
        //Sintomas
        jtxtID_Sintoma.setEditable(false);
        jtxtNombre_Sintoma.setEditable(false);
        jtxtZona_Sintoma.setEditable(false);
        jtxtTiempo_Sintoma.setEditable(false);
        //Signos
        jtxtID_Signo.setEditable(false);
        jtxtNombre_Signo.setEditable(false);
    }

    public void habilitar_formularios() {
        //Pacientes
        jtxtID.setEditable(false);
        jtxtNombre.setEditable(true);
        jtxtDireccion.setEditable(true);
        jtxtCiudad.setEditable(true);
        jtxtTelefono.setEditable(true);
        jtxtEmail.setEditable(true);
        //Tratamientos
        jtxtID_Enf.setEditable(false);
        jTextAreaTratamiento.setEditable(true);
        //Diagnostico
        jtxtID_Diagnostico.setEditable(false);
        jtxtDiagnostico.setEditable(true);
        //Sintomas
        jtxtID_Sintoma.setEditable(false);
        jtxtNombre_Sintoma.setEditable(true);
        jtxtZona_Sintoma.setEditable(true);
        jtxtTiempo_Sintoma.setEditable(true);
        //Signos
        jtxtID_Signo.setEditable(false);
        jtxtNombre_Signo.setEditable(true);
    }

    public void limpiar_formularios() {
        //Pacientes
        jtxtDato.setText("");
        jtxtID.setText("");
        jtxtNombre.setText("");
        jtxtDireccion.setText("");
        jtxtCiudad.setText("");
        jtxtTelefono.setText("");
        jtxtEmail.setText("");
        //Tratamientos
        jtxtDato_Tratamiento.setText("");
        jtxtID_Enf.setText("");
        jTextAreaTratamiento.setText("");
        //Diagnostico
        jtxtID_Diagnostico.setText("");
        jtxtDiagnostico.setText("");
        //Sintomas
        jtxtDato_Sintoma.setText("");
        jtxtID_Sintoma.setText("");
        jtxtNombre_Sintoma.setText("");
        jtxtZona_Sintoma.setText("");
        jtxtTiempo_Sintoma.setText("");
        //Signos
        jtxtDato_Signo.setText("");
        jtxtID_Signo.setText("");
        jtxtNombre_Signo.setText("");
    }

    public void guardar_paciente() {
        Paciente p = new Paciente();
        //p.setId(Integer.parseInt(jtxtID.getText()));
        p.setNombre(jtxtNombre.getText());
        p.setDireccion(jtxtDireccion.getText());
        p.setCiudad(jtxtCiudad.getText());
        p.setTelefono(jtxtTelefono.getText());
        p.setEmail(jtxtEmail.getText());

        try {
            db.save(p);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void editar_paciente() {
        Paciente p = new Paciente();
        //p.setId(Integer.parseInt(jtxtID.getText()));
        p.setNombre(jtxtNombre.getText());
        p.setDireccion(jtxtDireccion.getText());
        p.setCiudad(jtxtCiudad.getText());
        p.setTelefono(jtxtTelefono.getText());
        p.setEmail(jtxtEmail.getText());

        try {
            db.update(p);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardar_tratamiento() {
//        tratamiento.setId(Integer.parseInt(jtxtID_Enf.getText()));
        tratamiento.setTexto(jtxtDiagnostico.getText());

        int tamanio_tratamiento = jList_Tratamiento_Diagnostico.getModel().getSize();

        for(int i = 0; i < tamanio_tratamiento; i++) {
            Diagnostico nombreDiagnostico = new Diagnostico();
            nombreDiagnostico.setTexto(jList_Tratamiento_Diagnostico.getModel().getElementAt(i));
            try {
                Diagnostico d;
                d = db.searchByName(nombreDiagnostico);
                tratamiento.setDiagnosticoId(d.getId());
                db.save(tratamiento);
                System.out.println("Se guardó '" + tratamiento.getTexto() + "'");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void editar_tratamiento() {
        tratamiento.setId(Integer.parseInt(jtxtID_Enf.getText()));

        try {
            Tratamiento nombre = db.search(tratamiento);  //Se busca nombre a traves del id
            agregadosTratamiento = db.searchByName(nombre);   //SE obtienen todos los tratamientos con el nombre a editar
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < agregadosTratamiento.size(); i++) {  //Se eliminan los tratamientos obtenidos
            try {
                for(Tratamiento t : agregadosTratamiento) {
                    db.delete(t);
                    System.out.println("Se eliminó '" + t.getTexto() + "'");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        guardar_tratamiento();  //Se insertan los editados
    }
    
    public void guardar_sintoma() {
//        sintoma.setId(Integer.parseInt(jtxtID_Sintoma.getText()));
        sintoma.setNombre(jtxtNombre_Sintoma.getText());
        try {
            db.save(sintoma);
            System.out.println("Se guardó '" + sintoma.getNombre() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editar_sintoma() {
//        sintoma.setId(Integer.parseInt(jtxtID_Sintoma.getText()));
        sintoma.setNombre(jtxtNombre_Sintoma.getText());
        try {
            db.update(sintoma);
            System.out.println("Se modificó '" + sintoma.getNombre() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void guardar_signo() {
        //signo.setId(Integer.parseInt(jtxtID_Signo.getText()));
        signo.setNombre(jtxtNombre_Signo.getText());
        try {
            db.save(signo);
            System.out.println("Se guardó '" + signo.getNombre() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editar_signo() {
        signo.setId(Integer.parseInt(jtxtID_Signo.getText()));
        signo.setNombre(jtxtNombre_Signo.getText());
        try {
            db.update(signo);
            System.out.println("Se editó '" + signo.getNombre() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void guardar_diagnostico() {
//        diagnostico.setId(Integer.parseInt(jtxtID_Enf.getText()));
        diagnostico.setTexto(jtxtDiagnostico.getText());
        try {
            db.save(diagnostico);
            System.out.println("Se guardó '" + diagnostico.getTexto() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        try {
            diagnostico = db.searchByName(diagnostico);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        int tamanio_signos = jList_Signo_Diagnostico.getModel().getSize();
        for(int i = 0; i < tamanio_signos; i++) {
            Signo s = new Signo();
            s.setNombre(jList_Signo_Diagnostico.getModel().getElementAt(i));
            try {
                s = db.searchByName(s);
                db.save(s, diagnostico);
                System.out.println("Se guardó Signo_Diagnostico: '" + s.getId() + "', '" + diagnostico.getId() + "'");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        int tamanio_sintomas = jList_Sintoma_Diagnostico.getModel().getSize();
        for(int i = 0; i < tamanio_sintomas; i++) {
            Sintoma s = new Sintoma();
            s.setNombre(jList_Sintoma_Diagnostico.getModel().getElementAt(i));
            try {
                s = db.searchByName(s);
                db.save(s, diagnostico);
                System.out.println("Se guardó Sintoma_Diagnostico: '" + s.getId() + "', '" + diagnostico.getId() + "'");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //d.setSigno(list_Signo_Diagnostico.getText());
        //d.setSintoma(list_Sintoma_Diagnostico.getText());
    }

    public void editar_diagnostico() {
        diagnostico.setId(Integer.parseInt(jtxtID_Enf.getText()));
        try {
            Diagnostico nombre = db.search(diagnostico);
            nombre.setTexto(jtxtDiagnostico.getText());
            db.update(nombre);
            System.out.println("Se guardó '" + diagnostico.getTexto() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int tamanio_signos = jList_Signo_Diagnostico.getModel().getSize();
        for(int i = 0; i < tamanio_signos; i++) {
            Signo s = new Signo();
            s.setNombre(jList_Signo_Diagnostico.getModel().getElementAt(i));
            try {
                s = db.searchByName(s);
                db.update(s, diagnostico);
                System.out.println("Se guardó Signo_Diagnostico: '" + s.getId() + "', '" + diagnostico.getId() + "'");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < tamanio_signos; i++) {
            Signo s = new Signo();
            s.setNombre(jList_Signo_Diagnostico.getModel().getElementAt(i));
            try {
                s = db.searchByName(s);
                db.update(s, diagnostico);
                System.out.println("Se guardó Signo_Diagnostico: '" + s.getId() + "', '" + diagnostico.getId() + "'");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        int tamanio_sintomas = jList_Sintoma_Diagnostico.getModel().getSize();
        for(int i = 0; i < tamanio_sintomas; i++) {
            Sintoma s = new Sintoma();
            s.setNombre(jList_Sintoma_Diagnostico.getModel().getElementAt(i));
            try {
                s = db.searchByName(s);
                db.update(s, diagnostico);
                System.out.println("Se guardó Sintoma_Diagnostico: '" + s.getId() + "', '" + diagnostico.getId() + "'");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void cargarPacientes(){
        try {
            pacientesList = db.getPacientes();
        } catch (SQLException ex) {
//            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        jcbPacientes.removeAllItems();
        for(Paciente p : pacientesList) {
            jcbPacientes.addItem(p.getNombre());
        }
    }
    
    public void cargarSignos(){
        try {
            signosList = db.getSignos();
        } catch (SQLException ex) {
//            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }

        for(int i = combo_list_Signo_Diagnostico.getSize() - 1; i >= 0 ; i--) {
            combo_list_Signo_Diagnostico.removeSigno(i);
        }
        for(Signo s : signosList) {
            combo_list_Signo_Diagnostico.addSigno(s);
        }
    }
    
    public void cargarSintomas(){
        try {
            sintomasList = db.getSintomas();
        } catch (SQLException ex) {
//            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = combo_list_Sintoma_Diagnostico.getSize() - 1; i >= 0; i--) {
            combo_list_Sintoma_Diagnostico.removeSintoma(i);
        }
        for(Sintoma s : sintomasList) {
            combo_list_Sintoma_Diagnostico.addSintoma(s);
        }
    }

    public void cargarDiagnosticos() {
        try {
            diagnosticosList = db.getDiagnosticos();
        } catch (SQLException ex) {
//            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i = combo_list_Diagnostico.getSize() - 1; i >= 0 ; i--) {
            combo_list_Diagnostico.removeDiagnostico(i);
        }
        for(Diagnostico d : diagnosticosList) {
            combo_list_Diagnostico.addDiagnostico(d);
        }
        
        //jcb_Tratamiento_Diagnostico.removeAllItems();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new JTabbedPane();
        jPanel1 = new JPanel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel2 = new JLabel();
        jLabel7 = new JLabel();
        jbtnNuevo = new JButton();
        jtxtNombre = new JTextField();
        jbtnGuardar = new JButton();
        jtxtDireccion = new JTextField();
        jbtnCancelar = new JButton();
        jtxtCiudad = new JTextField();
        jbtnEditar = new JButton();
        jtxtTelefono = new JTextField();
        jLabel1 = new JLabel();
        jtxtID = new JTextField();
        jbtnBorrar = new JButton();
        jtxtEmail = new JTextField();
        jbtnBuscar = new JButton();
        jtxtDato = new JTextField();
        jLabel3 = new JLabel();
        jPanel3 = new JPanel();
        jLabel24 = new JLabel();
        jLabel25 = new JLabel();
        jcb_Sintoma_Diagnostico = new JComboBox<>();
        jLabel27 = new JLabel();
        jtxtID_Diagnostico = new JTextField();
        jLabel29 = new JLabel();
        jtxtDiagnostico = new JTextField();
        jbtnAgregar_Signo_Diagnostico = new JButton();
        jbtnEliminar_Signo_Diagnostico = new JButton();
        jbtnAgregar_Sintoma_Diagnostico = new JButton();
        jbtnEliminar_Sintoma_Diagnostico = new JButton();
        jbtnNuevo_Diagnostico = new JButton();
        jbtnCancelar_Diagnostico = new JButton();
        jbtnGenerar = new JButton();
        jcbPacientes = new JComboBox<>();
        jLabel28 = new JLabel();
        jcb_Signo_Diagnostico = new JComboBox<>();
        jScrollPane3 = new JScrollPane();
        jList_Signo_Diagnostico = new JList<>();
        jScrollPane4 = new JScrollPane();
        jList_Sintoma_Diagnostico = new JList<>();
        jPanel2 = new JPanel();
        jLabel8 = new JLabel();
        jtxtDato_Tratamiento = new JTextField();
        jbtnBuscar_Enf = new JButton();
        jbtnNuevo_Enf = new JButton();
        jbtnGuardar_Enf = new JButton();
        jbtnCancelar_Enf = new JButton();
        jbtnBorrar_Enf = new JButton();
        jLabel14 = new JLabel();
        jtxtID_Enf = new JTextField();
        jLabel19 = new JLabel();
        jbtnEditar_Enf = new JButton();
        jScrollPane1 = new JScrollPane();
        jTextAreaTratamiento = new JTextArea();
        jLabel23 = new JLabel();
        jcb_Tratamiento_Diagnostico = new JComboBox<>();
        jButtonAgregar_Diagnostico = new JButton();
        jButtonEliminar_Diagnostico = new JButton();
        jScrollPane2 = new JScrollPane();
        jList_Tratamiento_Diagnostico = new JList<>();
        jPanel4 = new JPanel();
        jLabel9 = new JLabel();
        jtxtDato_Sintoma = new JTextField();
        jbtnBuscar_Sintoma = new JButton();
        jtxtID_Sintoma = new JTextField();
        jLabel10 = new JLabel();
        jtxtNombre_Sintoma = new JTextField();
        jLabel11 = new JLabel();
        jbtnGuardar_Sintoma = new JButton();
        jbtnCancelar_Sintoma = new JButton();
        jbtnEditar_Sintoma = new JButton();
        jbtnBorrar_Sintoma = new JButton();
        jbtnNuevo_Sintoma = new JButton();
        jtxtZona_Sintoma = new JTextField();
        jLabel21 = new JLabel();
        jtxtTiempo_Sintoma = new JTextField();
        jLabel22 = new JLabel();
        jPanel5 = new JPanel();
        jbtnGuardar_Signo = new JButton();
        jbtnCancelar_Signo = new JButton();
        jLabel12 = new JLabel();
        jbtnEditar_Signo = new JButton();
        jbtnBorrar_Signo = new JButton();
        jtxtDato_Signo = new JTextField();
        jbtnNuevo_Signo = new JButton();
        jbtnBuscar_Signo = new JButton();
        jtxtID_Signo = new JTextField();
        jLabel16 = new JLabel();
        jtxtNombre_Signo = new JTextField();
        jLabel17 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Ciudad:");

        jLabel5.setText("Telefono:");

        jLabel6.setText("Email:");

        jLabel2.setText("Nombre:");

        jLabel7.setText("Ingrese ID del paciente:");

        jbtnNuevo.setText("Nuevo");
        jbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevoActionPerformed(evt);
            }
        });

        jbtnGuardar.setText("Guardar");
        jbtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarActionPerformed(evt);
            }
        });

        jbtnCancelar.setText("Cancelar");
        jbtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelarActionPerformed(evt);
            }
        });

        jbtnEditar.setText("Editar");
        jbtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarActionPerformed(evt);
            }
        });

        jLabel1.setText("ID:");

        jbtnBorrar.setText("Borrar");
        jbtnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBorrarActionPerformed(evt);
            }
        });

        jbtnBuscar.setText("Buscar");
        jbtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarActionPerformed(evt);
            }
        });

        jLabel3.setText("Direccion:");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtnNuevo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnGuardar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnCancelar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnEditar)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnBorrar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtxtDato, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnBuscar)))
                .addGap(50, 50, 50))
            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(105, 105, 105)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6))
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jtxtID, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jtxtDireccion, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jtxtEmail, GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtTelefono, GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtCiudad, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
                        .addComponent(jtxtNombre, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(146, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtDato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnBuscar)
                    .addComponent(jLabel7))
                .addGap(254, 254, 254)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnNuevo)
                    .addComponent(jbtnGuardar)
                    .addComponent(jbtnCancelar)
                    .addComponent(jbtnEditar)
                    .addComponent(jbtnBorrar))
                .addContainerGap(110, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(113, 113, 113)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxtDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxtCiudad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxtTelefono, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(jtxtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addContainerGap(170, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Pacientes", jPanel1);

        jLabel24.setText("Signo:");

        jLabel25.setText("Sintoma:");

        jLabel27.setText("ID:");

        jLabel29.setText("Diagnostico:");

        jbtnAgregar_Signo_Diagnostico.setText("Agregar");
        jbtnAgregar_Signo_Diagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAgregar_Signo_DiagnosticoActionPerformed(evt);
            }
        });

        jbtnEliminar_Signo_Diagnostico.setText("Eliminar");
        jbtnEliminar_Signo_Diagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminar_Signo_DiagnosticoActionPerformed(evt);
            }
        });

        jbtnAgregar_Sintoma_Diagnostico.setText("Agregar");
        jbtnAgregar_Sintoma_Diagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAgregar_Sintoma_DiagnosticoActionPerformed(evt);
            }
        });

        jbtnEliminar_Sintoma_Diagnostico.setText("Eliminar");
        jbtnEliminar_Sintoma_Diagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEliminar_Sintoma_DiagnosticoActionPerformed(evt);
            }
        });

        jbtnNuevo_Diagnostico.setText("Nuevo");
        jbtnNuevo_Diagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevo_DiagnosticoActionPerformed(evt);
            }
        });

        jbtnCancelar_Diagnostico.setText("Cancelar");
        jbtnCancelar_Diagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelar_DiagnosticoActionPerformed(evt);
            }
        });

        jbtnGenerar.setText("GENERAR");
        jbtnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGenerarActionPerformed(evt);
            }
        });

        jLabel28.setText("Paciente:");

        jScrollPane3.setViewportView(jList_Signo_Diagnostico);

        jList_Sintoma_Diagnostico.setModel(new AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(jList_Sintoma_Diagnostico);

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel29))
                                .addGap(18, 18, 18))
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel24))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jcb_Signo_Diagnostico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcb_Sintoma_Diagnostico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)))
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtDiagnostico, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtID_Diagnostico, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbPacientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jbtnGenerar))
                            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtnAgregar_Signo_Diagnostico)
                                    .addComponent(jbtnEliminar_Signo_Diagnostico)
                                    .addComponent(jbtnAgregar_Sintoma_Diagnostico)
                                    .addComponent(jbtnEliminar_Sintoma_Diagnostico)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jbtnNuevo_Diagnostico)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnCancelar_Diagnostico)
                        .addGap(227, 227, 227)))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbPacientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jtxtID_Diagnostico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtDiagnostico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jcb_Signo_Diagnostico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jbtnAgregar_Signo_Diagnostico)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtnEliminar_Signo_Diagnostico)))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jbtnAgregar_Sintoma_Diagnostico))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jcb_Sintoma_Diagnostico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25))))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtnEliminar_Sintoma_Diagnostico)
                        .addGap(59, 59, 59))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)))
                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCancelar_Diagnostico)
                    .addComponent(jbtnNuevo_Diagnostico)
                    .addComponent(jbtnGenerar))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Diagnosticos", jPanel3);

        jLabel8.setText("Ingrese ID del tratamiento:");

        jbtnBuscar_Enf.setText("Buscar");
        jbtnBuscar_Enf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscar_EnfActionPerformed(evt);
            }
        });

        jbtnNuevo_Enf.setText("Nuevo");
        jbtnNuevo_Enf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevo_EnfActionPerformed(evt);
            }
        });

        jbtnGuardar_Enf.setText("Guardar");
        jbtnGuardar_Enf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardar_EnfActionPerformed(evt);
            }
        });

        jbtnCancelar_Enf.setText("Cancelar");
        jbtnCancelar_Enf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelar_EnfActionPerformed(evt);
            }
        });

        jbtnBorrar_Enf.setText("Borrar");
        jbtnBorrar_Enf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBorrar_EnfActionPerformed(evt);
            }
        });

        jLabel14.setText("ID:");

        jLabel19.setText("Tratamiento:");

        jbtnEditar_Enf.setText("Editar");
        jbtnEditar_Enf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditar_EnfActionPerformed(evt);
            }
        });

        jTextAreaTratamiento.setColumns(20);
        jTextAreaTratamiento.setRows(5);
        jScrollPane1.setViewportView(jTextAreaTratamiento);

        jLabel23.setText("Diagnosticos:");

        jButtonAgregar_Diagnostico.setText("Agregar");

        jButtonEliminar_Diagnostico.setText("Eliminar");

        jScrollPane2.setViewportView(jList_Tratamiento_Diagnostico);

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel8)
                        .addGap(12, 12, 12)
                        .addComponent(jtxtDato_Tratamiento, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnBuscar_Enf))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jtxtID_Enf, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jbtnNuevo_Enf)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtnGuardar_Enf)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnCancelar_Enf)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnEditar_Enf)
                                .addGap(18, 18, 18)
                                .addComponent(jbtnBorrar_Enf)
                                .addGap(0, 11, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(17, 17, 17)
                                .addComponent(jcb_Tratamiento_Diagnostico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jButtonAgregar_Diagnostico)
                                    .addComponent(jButtonEliminar_Diagnostico))))))
                .addGap(97, 97, 97))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel8))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jtxtDato_Tratamiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jbtnBuscar_Enf))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jtxtID_Enf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(jcb_Tratamiento_Diagnostico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jButtonAgregar_Diagnostico)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonEliminar_Diagnostico)
                        .addGap(18, 92, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)))
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnNuevo_Enf)
                    .addComponent(jbtnGuardar_Enf)
                    .addComponent(jbtnCancelar_Enf)
                    .addComponent(jbtnEditar_Enf)
                    .addComponent(jbtnBorrar_Enf))
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Tratamientos", jPanel2);

        jLabel9.setText("Ingrese el ID del sintoma:");

        jbtnBuscar_Sintoma.setText("Buscar");
        jbtnBuscar_Sintoma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscar_SintomaActionPerformed(evt);
            }
        });

        jLabel10.setText("ID:");

        jLabel11.setText("Nombre:");

        jbtnGuardar_Sintoma.setText("Guardar");
        jbtnGuardar_Sintoma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardar_SintomaActionPerformed(evt);
            }
        });

        jbtnCancelar_Sintoma.setText("Cancelar");
        jbtnCancelar_Sintoma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelar_SintomaActionPerformed(evt);
            }
        });

        jbtnEditar_Sintoma.setText("Editar");
        jbtnEditar_Sintoma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditar_SintomaActionPerformed(evt);
            }
        });

        jbtnBorrar_Sintoma.setText("Borrar");
        jbtnBorrar_Sintoma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBorrar_SintomaActionPerformed(evt);
            }
        });

        jbtnNuevo_Sintoma.setText("Nuevo");
        jbtnNuevo_Sintoma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevo_SintomaActionPerformed(evt);
            }
        });

        jLabel21.setText("Zona:");

        jLabel22.setText("Tiempo:");

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel9)
                                .addComponent(jLabel10)
                                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel11))
                                .addComponent(jLabel21))
                            .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(jtxtDato_Sintoma, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                            .addGap(38, 38, 38)
                                            .addComponent(jbtnBuscar_Sintoma))
                                        .addComponent(jtxtID_Sintoma, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jtxtNombre_Sintoma)))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(jtxtTiempo_Sintoma))))
                        .addComponent(jtxtZona_Sintoma, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jbtnNuevo_Sintoma)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnGuardar_Sintoma)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnCancelar_Sintoma)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnEditar_Sintoma)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnBorrar_Sintoma)))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jtxtDato_Sintoma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnBuscar_Sintoma))
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtID_Sintoma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jtxtNombre_Sintoma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtZona_Sintoma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtTiempo_Sintoma, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnNuevo_Sintoma)
                    .addComponent(jbtnGuardar_Sintoma)
                    .addComponent(jbtnCancelar_Sintoma)
                    .addComponent(jbtnEditar_Sintoma)
                    .addComponent(jbtnBorrar_Sintoma))
                .addContainerGap(195, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sintomas", jPanel4);

        jbtnGuardar_Signo.setText("Guardar");
        jbtnGuardar_Signo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardar_SignoActionPerformed(evt);
            }
        });

        jbtnCancelar_Signo.setText("Cancelar");
        jbtnCancelar_Signo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelar_SignoActionPerformed(evt);
            }
        });

        jLabel12.setText("Ingrese el ID del signo:");

        jbtnEditar_Signo.setText("Editar");
        jbtnEditar_Signo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditar_SignoActionPerformed(evt);
            }
        });

        jbtnBorrar_Signo.setText("Borrar");
        jbtnBorrar_Signo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBorrar_SignoActionPerformed(evt);
            }
        });

        jbtnNuevo_Signo.setText("Nuevo");
        jbtnNuevo_Signo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevo_SignoActionPerformed(evt);
            }
        });

        jbtnBuscar_Signo.setText("Buscar");
        jbtnBuscar_Signo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscar_SignoActionPerformed(evt);
            }
        });

        jLabel16.setText("ID:");

        jLabel17.setText("Nombre:");

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jbtnNuevo_Signo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnGuardar_Signo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnCancelar_Signo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnEditar_Signo)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnBorrar_Signo))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jtxtDato_Signo, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jbtnBuscar_Signo))
                            .addComponent(jtxtID_Signo, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtNombre_Signo))))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jtxtDato_Signo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnBuscar_Signo))
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtID_Signo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jtxtNombre_Signo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(53, 53, 53)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnNuevo_Signo)
                    .addComponent(jbtnGuardar_Signo)
                    .addComponent(jbtnCancelar_Signo)
                    .addComponent(jbtnEditar_Signo)
                    .addComponent(jbtnBorrar_Signo))
                .addContainerGap(225, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Signos", jPanel5);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 600, 480));
        jTabbedPane1.getAccessibleContext().setAccessibleName("tab6");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jbtnBorrar_EnfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBorrar_EnfActionPerformed
        tratamiento.setId(Integer.parseInt(jtxtID_Enf.getText()));

        try {
            db.delete(tratamiento);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        index();
    }//GEN-LAST:event_jbtnBorrar_EnfActionPerformed

    private void jbtnCancelar_EnfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelar_EnfActionPerformed
        index();
    }//GEN-LAST:event_jbtnCancelar_EnfActionPerformed

    private void jbtnGuardar_EnfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardar_EnfActionPerformed
        guardar_tratamiento();
        index();
    }//GEN-LAST:event_jbtnGuardar_EnfActionPerformed

    private void jbtnNuevo_EnfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevo_EnfActionPerformed
        jbtnNuevo_Enf.setEnabled(false);
        jbtnGuardar_Enf.setEnabled(true);
        jbtnCancelar_Enf.setEnabled(true);
        jbtnEditar_Enf.setEnabled(false);
        jbtnBorrar_Enf.setEnabled(false);
        jButtonAgregar_Diagnostico.setEnabled(true);
        jButtonEliminar_Diagnostico.setEnabled(true);

        limpiar_formularios();
        habilitar_formularios();

//        try {
//            jtxtID_Enf.setText(String.valueOf(db.getMax_id_enfermedad()));
//        } catch (SQLException ex) {
//            //Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_jbtnNuevo_EnfActionPerformed

    private void jbtnBuscar_EnfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscar_EnfActionPerformed
//
//        habilitar_formularios();
//        jbtnNuevo_Enf.setEnabled(false);
//        jbtnGuardar_Enf.setEnabled(false);
//        jbtnCancelar_Enf.setEnabled(true);
//        jbtnEditar_Enf.setEnabled(true);
//        jbtnBorrar_Enf.setEnabled(true);
//
//        e.setId_enfermedad(Integer.parseInt(jtxtDato_Tratamiento.getText()));
//
//        try {
//
//            if(e.getId_enfermedad() >= db.getMax_id_enfermedad()){
//                deshabilitar_formularios();
//                limpiar_formularios();
//                JOptionPane.showMessageDialog(null, "El número del ID es mayor del ultimo registrado", "Error de captura",JOptionPane.ERROR_MESSAGE);
//                index();
//            }else{
//
//                e = db.Search_Enf(e);
//
//                jtxtID_Enf.setText(String.valueOf(e.getId_enfermedad()));
//                jtxtNombre_Tratamiento.setText(e.getNombre());
//                jtxtTipo_Enf.setText(e.getTipo());
//                jtxtMedicamento_Enf.setText(e.getMed_sug());
//
//                demo.logMessage(6);
//            }
//
//        } catch (SQLException ex) {
//
//        }
    }//GEN-LAST:event_jbtnBuscar_EnfActionPerformed

    
    
    //          PACIENTES
    
    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscarActionPerformed
//
//        habilitar_formularios();
//        jbtnNuevo.setEnabled(false);
//        jbtnGuardar.setEnabled(false);
//        jbtnCancelar.setEnabled(true);
//        jbtnEditar.setEnabled(true);
//        jbtnBorrar.setEnabled(true);
//
//        p.setId_paciente(Integer.parseInt(jtxtDato.getText()));
//
//        try {
//
//            if(p.getId_paciente() >= db.getMax_id()){
//                deshabilitar_formularios();
//                limpiar_formularios();
//                JOptionPane.showMessageDialog(null, "El número del ID es mayor del ultimo registrado", "Error de captura",JOptionPane.ERROR_MESSAGE);
//                index();
//            }else{
//
//                p = db.Search(p);
//
//                jtxtID.setText(String.valueOf(p.getId_paciente()));
//                jtxtNombre.setText(p.getNombre());
//                jtxtDireccion.setText(p.getDireccion());
//                jtxtTelefono.setText(p.getTelefono());
//                jtxtCiudad.setText(p.getCiudad());
//                jtxtEmail.setText(p.getEmail());
//
//                demo.logMessage(7);
//
//            }
//
//        } catch (SQLException ex) {
//
//        }

    }//GEN-LAST:event_jbtnBuscarActionPerformed

    private void jbtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBorrarActionPerformed
        paciente.setId(Integer.parseInt(jtxtID.getText()));

        try {
            db.delete(paciente);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        index();
    }//GEN-LAST:event_jbtnBorrarActionPerformed

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditarActionPerformed
        editar_paciente();
        index();
    }//GEN-LAST:event_jbtnEditarActionPerformed

    private void jbtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelarActionPerformed
        index();
    }//GEN-LAST:event_jbtnCancelarActionPerformed

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        guardar_paciente();
        index();
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevoActionPerformed
        jbtnNuevo.setEnabled(false);
        jbtnGuardar.setEnabled(true);
        jbtnCancelar.setEnabled(true);
        jbtnEditar.setEnabled(false);
        jbtnBorrar.setEnabled(false);

        limpiar_formularios();
        habilitar_formularios();

//        try {
//            jtxtID.setText(String.valueOf(db.getMax_id()));
//        } catch (SQLException ex) {
//            //Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_jbtnNuevoActionPerformed

    
    
    
    //          SINTOMAS
    
    private void jbtnGuardar_SintomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardar_SintomaActionPerformed
        guardar_sintoma();
        index();
        cargarSintomas();
    }//GEN-LAST:event_jbtnGuardar_SintomaActionPerformed

    private void jbtnCancelar_SintomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelar_SintomaActionPerformed
        index();
    }//GEN-LAST:event_jbtnCancelar_SintomaActionPerformed

    private void jbtnEditar_SintomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditar_SintomaActionPerformed
        editar_sintoma();
        index();
        cargarSintomas();
    }//GEN-LAST:event_jbtnEditar_SintomaActionPerformed

    private void jbtnBorrar_SintomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBorrar_SintomaActionPerformed

        sintoma.setId(Integer.parseInt(jtxtID_Sintoma.getText()));

        try {
            db.delete(sintoma);
        } catch (SQLException ex) {
        }

        index();
        cargarSintomas();
    }//GEN-LAST:event_jbtnBorrar_SintomaActionPerformed

    private void jbtnNuevo_SintomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevo_SintomaActionPerformed
        jbtnNuevo_Sintoma.setEnabled(false);
        jbtnGuardar_Sintoma.setEnabled(true);
        jbtnCancelar_Sintoma.setEnabled(true);
        jbtnEditar_Sintoma.setEnabled(false);
        jbtnBorrar_Sintoma.setEnabled(false);

        limpiar_formularios();
        habilitar_formularios();

//        try {
//            jtxtID_Sintoma.setText(String.valueOf(db.getMax_id_sintoma()));
//        } catch (SQLException ex) {
//            //Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jbtnNuevo_SintomaActionPerformed

    
    
    //          SIGNOS
    
    private void jbtnGuardar_SignoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardar_SignoActionPerformed
        guardar_signo();
        index();
        cargarSignos();
    }//GEN-LAST:event_jbtnGuardar_SignoActionPerformed

    private void jbtnCancelar_SignoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelar_SignoActionPerformed
        index();
    }//GEN-LAST:event_jbtnCancelar_SignoActionPerformed

    private void jbtnEditar_SignoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditar_SignoActionPerformed
        editar_signo();

        try {
            db.update(signo);
        } catch (SQLException ex) {

        }

        index();
        cargarSignos();
    }//GEN-LAST:event_jbtnEditar_SignoActionPerformed

    private void jbtnBorrar_SignoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBorrar_SignoActionPerformed
        signo.setId(Integer.parseInt(jtxtID_Signo.getText()));

        try {
            db.delete(signo);
        } catch (SQLException ex) {
            
        }

        index();
        cargarSignos();
    }//GEN-LAST:event_jbtnBorrar_SignoActionPerformed

    private void jbtnNuevo_SignoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevo_SignoActionPerformed
        jbtnNuevo_Signo.setEnabled(false);
        jbtnGuardar_Signo.setEnabled(true);
        jbtnCancelar_Signo.setEnabled(true);
        jbtnEditar_Signo.setEnabled(false);
        jbtnBorrar_Signo.setEnabled(false);

        limpiar_formularios();
        habilitar_formularios();

//        try {
//            jtxtID_Signo.setText(String.valueOf(db.getMax_id_signo()));
//        } catch (SQLException ex) {
//            //Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jbtnNuevo_SignoActionPerformed

    
    //          DIAGNOSTICOS
    
    private void jbtnNuevo_DiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNuevo_DiagnosticoActionPerformed
        jbtnNuevo_Diagnostico.setEnabled(false);
        jbtnCancelar_Diagnostico.setEnabled(true);
        jbtnGenerar.setEnabled(true);
        jbtnAgregar_Signo_Diagnostico.setEnabled(true);
        jbtnAgregar_Sintoma_Diagnostico.setEnabled(true);
        jbtnEliminar_Signo_Diagnostico.setEnabled(true);
        jbtnEliminar_Sintoma_Diagnostico.setEnabled(true);

        limpiar_formularios();
        habilitar_formularios();

//        try {
//            jtxtID_Diagnostico.setText(String.valueOf(db.getMax_id_diagnostico()));
//        } catch (SQLException ex) {
//            //Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_jbtnNuevo_DiagnosticoActionPerformed

    private void jbtnCancelar_DiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelar_DiagnosticoActionPerformed
        index();
    }//GEN-LAST:event_jbtnCancelar_DiagnosticoActionPerformed

    private void jbtnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGenerarActionPerformed
        guardar_diagnostico();
        index();
        cargarDiagnosticos();
    }//GEN-LAST:event_jbtnGenerarActionPerformed

    
    
    //          BUSQUEDA DE SINTOMAS Y SIGNOS
    
    private void jbtnBuscar_SintomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscar_SintomaActionPerformed
//        habilitar_formularios();
//        jbtnNuevo_Sintoma.setEnabled(false);
//        jbtnGuardar_Sintoma.setEnabled(false);
//        jbtnCancelar_Sintoma.setEnabled(true);
//        jbtnEditar_Sintoma.setEnabled(true);
//        jbtnBorrar_Sintoma.setEnabled(true);
//
//        sintoma.setId_sintoma(Integer.parseInt(jtxtDato_Sintoma.getText()));
//
//        try {
//
//            if(sintoma.getId_sintoma() >= db.getMax_id_sintoma()){
//                deshabilitar_formularios();
//                limpiar_formularios();
//                JOptionPane.showMessageDialog(null, "El número del ID es mayor del ultimo registrado", "Error de captura",JOptionPane.ERROR_MESSAGE);
//                index();
//            }else{
//
//                sintoma = db.Search_Sin(sintoma);
//
//                jtxtID_Sintoma.setText(String.valueOf(sintoma.getId_sintoma()));
//                jtxtNombre_Sintoma.setText(sintoma.getNombre());
//                jtxtZona_Sintoma.setText(sintoma.getZona());
//                jtxtTiempo_Sintoma.setText(sintoma.getTiempo());
//
//                demo.logMessage(26);
//
//            }
//
//        } catch (SQLException ex) {
//
//        }
    }//GEN-LAST:event_jbtnBuscar_SintomaActionPerformed

    private void jbtnBuscar_SignoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBuscar_SignoActionPerformed
//        habilitar_formularios();
//        jbtnNuevo_Signo.setEnabled(false);
//        jbtnGuardar_Signo.setEnabled(false);
//        jbtnCancelar_Signo.setEnabled(true);
//        jbtnEditar_Signo.setEnabled(true);
//        jbtnBorrar_Signo.setEnabled(true);
//
//        signo.setId_signo(Integer.parseInt(jtxtDato_Signo.getText()));
//
//        try {
//
//            if(signo.getId_signo() >= db.getMax_id_signo()){
//                deshabilitar_formularios();
//                limpiar_formularios();
//                JOptionPane.showMessageDialog(null, "El número del ID es mayor del ultimo registrado", "Error de captura",JOptionPane.ERROR_MESSAGE);
//                index();
//            }else{
//
//                signo = db.Search_Sig(signo);
//
//                jtxtID_Signo.setText(String.valueOf(signo.getId_signo()));
//                jtxtNombre_Signo.setText(signo.getNombre());
//
//                demo.logMessage(27);
//
//            }
//
//        } catch (SQLException ex) {
//
//        }
    }//GEN-LAST:event_jbtnBuscar_SignoActionPerformed

    
    
    
    
    //          AGREGAR Y ELIMINAR DE SINTOMAS Y SIGNOS EN LA TABLA """"DIAGNOSTICOS""""
    private void jbtnAgregar_Signo_DiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregar_Signo_DiagnosticoActionPerformed
        //list_Signo_Diagnostico.addElement((String) jcb_Signo_Diagnostico.getSelectedItem());
        list_Signo_Diagnostico.addSigno((Signo) combo_list_Signo_Diagnostico.getSigno(jcb_Signo_Diagnostico.getSelectedIndex()) );
    }//GEN-LAST:event_jbtnAgregar_Signo_DiagnosticoActionPerformed

    private void jbtnEliminar_Signo_DiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminar_Signo_DiagnosticoActionPerformed
        list_Signo_Diagnostico.removeSigno(jList_Signo_Diagnostico.getSelectedIndex());
    }//GEN-LAST:event_jbtnEliminar_Signo_DiagnosticoActionPerformed

    private void jbtnAgregar_Sintoma_DiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAgregar_Sintoma_DiagnosticoActionPerformed
        list_Sintoma_Diagnostico.addSintoma((Sintoma) combo_list_Sintoma_Diagnostico.getSintoma(jcb_Sintoma_Diagnostico.getSelectedIndex()));
    }//GEN-LAST:event_jbtnAgregar_Sintoma_DiagnosticoActionPerformed

    private void jbtnEliminar_Sintoma_DiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEliminar_Sintoma_DiagnosticoActionPerformed
        list_Sintoma_Diagnostico.removeSintoma(jList_Sintoma_Diagnostico.getSelectedIndex());
    }//GEN-LAST:event_jbtnEliminar_Sintoma_DiagnosticoActionPerformed

    //          ENFERMEDADES
    
    private void jbtnEditar_EnfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnEditar_EnfActionPerformed
        editar_tratamiento();
        index();
    }//GEN-LAST:event_jbtnEditar_EnfActionPerformed

    
    

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButtonAgregar_Diagnostico;
    private JButton jButtonEliminar_Diagnostico;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel14;
    private JLabel jLabel16;
    private JLabel jLabel17;
    private JLabel jLabel19;
    private JLabel jLabel2;
    private JLabel jLabel21;
    private JLabel jLabel22;
    private JLabel jLabel23;
    private JLabel jLabel24;
    private JLabel jLabel25;
    private JLabel jLabel27;
    private JLabel jLabel28;
    private JLabel jLabel29;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private JList<String> jList_Signo_Diagnostico;
    private JList<String> jList_Sintoma_Diagnostico;
    private JList<String> jList_Tratamiento_Diagnostico;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JScrollPane jScrollPane4;
    private JTabbedPane jTabbedPane1;
    private JTextArea jTextAreaTratamiento;
    private JButton jbtnAgregar_Signo_Diagnostico;
    private JButton jbtnAgregar_Sintoma_Diagnostico;
    private JButton jbtnBorrar;
    private JButton jbtnBorrar_Enf;
    private JButton jbtnBorrar_Signo;
    private JButton jbtnBorrar_Sintoma;
    private JButton jbtnBuscar;
    private JButton jbtnBuscar_Enf;
    private JButton jbtnBuscar_Signo;
    private JButton jbtnBuscar_Sintoma;
    private JButton jbtnCancelar;
    private JButton jbtnCancelar_Diagnostico;
    private JButton jbtnCancelar_Enf;
    private JButton jbtnCancelar_Signo;
    private JButton jbtnCancelar_Sintoma;
    private JButton jbtnEditar;
    private JButton jbtnEditar_Enf;
    private JButton jbtnEditar_Signo;
    private JButton jbtnEditar_Sintoma;
    private JButton jbtnEliminar_Signo_Diagnostico;
    private JButton jbtnEliminar_Sintoma_Diagnostico;
    private JButton jbtnGenerar;
    private JButton jbtnGuardar;
    private JButton jbtnGuardar_Enf;
    private JButton jbtnGuardar_Signo;
    private JButton jbtnGuardar_Sintoma;
    private JButton jbtnNuevo;
    private JButton jbtnNuevo_Diagnostico;
    private JButton jbtnNuevo_Enf;
    private JButton jbtnNuevo_Signo;
    private JButton jbtnNuevo_Sintoma;
    private JComboBox<String> jcbPacientes;
    private JComboBox<String> jcb_Signo_Diagnostico;
    private JComboBox<String> jcb_Sintoma_Diagnostico;
    private JComboBox<String> jcb_Tratamiento_Diagnostico;
    private JTextField jtxtCiudad;
    private JTextField jtxtDato;
    private JTextField jtxtDato_Signo;
    private JTextField jtxtDato_Sintoma;
    private JTextField jtxtDato_Tratamiento;
    private JTextField jtxtDiagnostico;
    private JTextField jtxtDireccion;
    private JTextField jtxtEmail;
    private JTextField jtxtID;
    private JTextField jtxtID_Diagnostico;
    private JTextField jtxtID_Enf;
    private JTextField jtxtID_Signo;
    private JTextField jtxtID_Sintoma;
    private JTextField jtxtNombre;
    private JTextField jtxtNombre_Signo;
    private JTextField jtxtNombre_Sintoma;
    private JTextField jtxtTelefono;
    private JTextField jtxtTiempo_Sintoma;
    private JTextField jtxtZona_Sintoma;
    // End of variables declaration//GEN-END:variables
}
