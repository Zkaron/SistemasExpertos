package Parcial3.sockets;

import java.awt.Image;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Cliente extends javax.swing.JFrame {
  String usuario;   
  Image nuevo;
  ImageIcon img1;
  Boolean ban;
   private DatagramSocket socket;
   
    private void llenarTabla( String[] tabla) 
    {
      // Logger.getLogger(FormAlumnos.class.getName()).log(Level.SEVERE, null, ex);
        DefaultTableModel modelo=(DefaultTableModel) jTUsuarios.getModel();
        int filas=jTUsuarios.getRowCount();
        for (int i = 0;filas>i; i++) {
            modelo.removeRow(0);
        }
      int i=0;
      while(i<2){
          modelo.addRow(new Object[]{"","","","",""});
          jTUsuarios.setValueAt(tabla[0], i, 0);
          jTUsuarios.setValueAt(tabla[1], i, 1);
          jTUsuarios.setValueAt(tabla[2], i, 2);
         // jTUsuarios.setValueAt(tabla[3], i, 3);
        //  jTUsuarios.setValueAt(tabla[4], i, 4);
          i++;
      }
    }
     private void esperarPaquetesTabla()
    {     try{  byte datos[]=new byte[100];
                DatagramPacket recibirPaquete = new DatagramPacket(datos, datos.length);
                socket.receive(recibirPaquete);//esperar un paquete
            String cad=( new String(recibirPaquete.getData(), 0,recibirPaquete.getLength()));
                 String []cad2=cad.split("-");           
             llenarTabla(cad2);
             
            } catch(IOException excepcion ){
                excepcion.printStackTrace();
            }
    }//fin del m
   
    public Cliente(String email, String rol) {  // Constructor
        
        this.ban=false;
        try {
          initComponents();     
          socket= new DatagramSocket();
      } catch (SocketException ex) {
        //  Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
      }
          enviarTable("usuario-table-1");
         // esperarPaquetesTabla();
          jLabel4.setText(email);
          ImageIcon img1 = new ImageIcon(getClass().getResource("iconos/add.png"));
           nuevo=img1.getImage();
           jbtnNuevo.setIcon(new ImageIcon(
           nuevo.getScaledInstance(jbtnNuevo.getWidth(), jbtnNuevo.getHeight(), WIDTH)));
           
            ImageIcon img2 = new ImageIcon(getClass().getResource("iconos/save.png"));
           nuevo=img2.getImage();
           jbtnSalvar.setIcon(new ImageIcon(
           nuevo.getScaledInstance(jbtnNuevo.getWidth(), jbtnNuevo.getHeight(), WIDTH)));
           
            ImageIcon img3 = new ImageIcon(getClass().getResource("iconos/cancel.png"));
           nuevo=img3.getImage();
           jbtnCancelar.setIcon(new ImageIcon(
           nuevo.getScaledInstance(jbtnNuevo.getWidth(), jbtnNuevo.getHeight(), WIDTH)));
           
            ImageIcon img4 = new ImageIcon(getClass().getResource("iconos/edit.png"));
           nuevo=img4.getImage();
           jbtnEditar.setIcon(new ImageIcon(
           nuevo.getScaledInstance(jbtnNuevo.getWidth(), jbtnNuevo.getHeight(), WIDTH)));
           
            ImageIcon img5 = new ImageIcon(getClass().getResource("iconos/delete.png"));
           nuevo=img5.getImage();
           jbtnBorrar.setIcon(new ImageIcon(nuevo.getScaledInstance(jbtnNuevo.getWidth(), jbtnNuevo.getHeight(), WIDTH)));
    }
    
   
 private void esperarPaquetes()
    {     try{  byte datos[]=new byte[100];
                DatagramPacket recibirPaquete = new DatagramPacket(datos, datos.length);
                socket.receive(recibirPaquete);//esperar un paquete
            String cad=( new String(recibirPaquete.getData(), 0,recibirPaquete.getLength()));
                String[]variables;
               
                variables=cad.split("-");
                if(variables[0].equals("0")){
                jtxtID.setText("");
               jtxtNombre.setText("");
                jtxtEmail.setText("");
                jbtnEditar.setEnabled(false);
                jbtnBorrar.setEnabled(false);
                
                }else{
                  jtxtID.setText(String.valueOf(variables[0]));
                  jtxtNombre.setText(String.valueOf(variables[1]));
                  jtxtEmail.setText(String.valueOf(variables[2]));
                jbtnEditar.setEnabled(true);
                jbtnBorrar.setEnabled(true);
                }
            } catch(IOException excepcion ){
                excepcion.printStackTrace();
            }
    }//fin del metodo esperarPaquete
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jlbl = new javax.swing.JLabel();
        jtxtID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jtxtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtxtEmail = new javax.swing.JTextField();
        jtxtPassword1 = new javax.swing.JTextField();
        j1 = new javax.swing.JLabel();
        jbtnNuevo = new javax.swing.JButton();
        jbtnBorrar = new javax.swing.JButton();
        jbtnSalvar = new javax.swing.JButton();
        jbtnCancelar = new javax.swing.JButton();
        jbtnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTUsuarios = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jcboRoles = new javax.swing.JComboBox();
        jbtnBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        jlbl.setText("ID:");
        jPanel1.add(jlbl);
        jlbl.setBounds(92, 49, 15, 14);
        jPanel1.add(jtxtID);
        jtxtID.setBounds(120, 50, 154, 20);

        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(70, 80, 50, 14);
        jPanel1.add(jtxtNombre);
        jtxtNombre.setBounds(120, 80, 200, 20);

        jLabel2.setText("E-mail:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(80, 110, 32, 14);
        jPanel1.add(jtxtEmail);
        jtxtEmail.setBounds(120, 110, 220, 20);
        jPanel1.add(jtxtPassword1);
        jtxtPassword1.setBounds(120, 140, 220, 20);

        j1.setText("Password:");
        jPanel1.add(j1);
        j1.setBounds(70, 140, 50, 14);

        jbtnNuevo.setToolTipText("");
        jbtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNuevoActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnNuevo);
        jbtnNuevo.setBounds(50, 300, 80, 50);

        jbtnBorrar.setEnabled(false);
        jbtnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBorrarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnBorrar);
        jbtnBorrar.setBounds(410, 300, 80, 50);

        jbtnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSalvarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnSalvar);
        jbtnSalvar.setBounds(140, 300, 80, 50);
        jPanel1.add(jbtnCancelar);
        jbtnCancelar.setBounds(230, 300, 80, 50);

        jbtnEditar.setEnabled(false);
        jbtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnEditar);
        jbtnEditar.setBounds(320, 300, 80, 50);

        jTUsuarios.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "E-mail", "Password", "Rol"
            }
        ));
        jScrollPane1.setViewportView(jTUsuarios);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(22, 180, 490, 100);

        jLabel3.setText("Roles");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(320, 50, 34, 14);

        jcboRoles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "Supervisor", "Empleado" }));
        jPanel1.add(jcboRoles);
        jcboRoles.setBounds(360, 50, 150, 20);

        jbtnBuscar.setText("Buscar");
        jbtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBuscarActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnBuscar);
        jbtnBuscar.setBounds(370, 80, 90, 40);

        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 10, 34, 14);

        jTabbedPane1.addTab("USUARIOS", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("CLIENTES", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("PROVEEDORES", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("COMPRAS", jPanel4);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("VENTAS", jPanel5);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 545, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("ARTICULOS", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>                        
    public void enviar( String mensaje){
      try{   
                    byte datos[]=mensaje.getBytes();
                    DatagramPacket enviarPaquete = new DatagramPacket(datos, 
                            datos.length, InetAddress.getLocalHost(),5000);
                    socket.send(enviarPaquete);//enviar paquete
                    esperarPaquetes();
                    socket= new DatagramSocket();
                }catch(IOException exceptionES){
                    exceptionES.printStackTrace();
                }   
    }
    
     public void enviarTable( String mensaje){
      try{   
                    byte datos[]=mensaje.getBytes();
                    DatagramPacket enviarPaquete = new DatagramPacket(datos, 
                            datos.length, InetAddress.getLocalHost(),5000);
                    socket.send(enviarPaquete);//enviar paquete
                    esperarPaquetesTabla();
                    socket= new DatagramSocket();
                }catch(IOException exceptionES){
                    exceptionES.printStackTrace();
                }   
    }
    private void jbtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {                                          
       enviar("usuario-new-");
      this.ban=true;
    }                                         

    private void jbtnSalvarActionPerformed(java.awt.event.ActionEvent evt) {                                           
         
              enviar("usuario-save-"+jtxtID.getText()+"-"+jtxtNombre.getText()+"-"+
              jtxtEmail.getText()+"-"+jtxtPassword1.getText()+"-"+
              jcboRoles.getSelectedItem());
             enviarTable("usuario-table-");
        
    }                                          

    private void jbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {                                           
       
        if(jtxtID.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Ingrese ID");
        }
        else{
            enviar("usuario-search-"+jtxtID.getText());
        }
       
        
    }                                          

    private void jbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {                                           
       enviar("usuario-edit-"+jtxtID.getText()+"-"+jtxtNombre.getText()+"-"+
              jtxtEmail.getText()+"-"+jtxtPassword1.getText()+"-"+
              jcboRoles.getSelectedItem());
       //this.ban=false;
    }                                          

    private void jbtnBorrarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        enviar("usuario-del-"+jtxtID.getText());
    }                                          

 
    // Variables declaration - do not modify                     
    private javax.swing.JLabel j1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTUsuarios;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtnBorrar;
    private javax.swing.JButton jbtnBuscar;
    private javax.swing.JButton jbtnCancelar;
    private javax.swing.JButton jbtnEditar;
    private javax.swing.JButton jbtnNuevo;
    private javax.swing.JButton jbtnSalvar;
    private javax.swing.JComboBox jcboRoles;
    private javax.swing.JLabel jlbl;
    private javax.swing.JTextField jtxtEmail;
    private javax.swing.JTextField jtxtID;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtPassword1;
    // End of variables declaration                   
}
