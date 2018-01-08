//package Parcial3;//package cliente.servidor;
////import cliente.servidor.Conexion;
//import java.io.*;
//import java.net.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javax.swing.*;
//
//public class Servidor extends JFrame {
//    private JTextArea areaPantalla;
//    private DatagramSocket socket;
//    public Connection conn;
//    String mensaje;
//    Usuario user;
//    KnowledgeBase db;
//    public Servidor() {
//        super("Servidor");
//        user=new Usuario();
//        db=new  DataBase();
//        areaPantalla = new JTextArea();
//        getContentPane().add(new JScrollPane(areaPantalla),
//                BorderLayout.CENTER);
//        setSize(400, 300);
//        setVisible(true);
//        try {
//            socket = new DatagramSocket(5000);
//        } catch (SocketException excepcionSocket) {
//            excepcionSocket.printStackTrace();
//            System.exit(1);
//        }
//    }
//    private void esperarPaquetes() throws SQLException {
//
//        String [] informacion=new String[100];
//        Boolean ban=false;
//        while (true) {  // loop infinito
//         try {  byte datos[] = new byte[100];
//                DatagramPacket recibirPaquete = new DatagramPacket(datos, datos.length);
//                socket.receive(recibirPaquete);//espera el paquete
//
//              String cad=( new String(recibirPaquete.getData(), 0,recibirPaquete.getLength()));
//
//                if (recibirPaquete.getLength() != 0) {
//                    JOptionPane.showMessageDialog(this, "Solicitud de Registro");
//                    informacion=cad.split("-");
//
//                    if(informacion[0].equals("usuario")){ // cuando es nuevo el usuario maximo id
//                      if(informacion[1].equals("new"))
//                        mensaje= String.valueOf( db.MaxId(user))+"-";
//                      else if(informacion[1].equals("save")){
//                          user.setId_usuario(Integer.parseInt(informacion[2]));
//                          user.setNombre(informacion[3]);
//                          user.setEmail(informacion[4]);
//                          user.setPassword(informacion[5]);
//                          user.setRol(informacion[6]);
//                          db.Save(user);
//                       }else if(informacion[1].equals("search")){
//                          Usuario user= new Usuario();
//                          user.setId_usuario(Integer.parseInt(informacion[2]));
//                          user=db.search(user);
//                          if(user.getNombre()!=null){
//                             mensaje= user.getId_usuario()+"-"+user.getNombre()+"-"+
//                                  user.getEmail();
//                          }else{
//                             mensaje= "0-No se encontro-";
//                          }
//
//                      }else if(informacion[1].equals("edit")){
//                        user.setId_usuario(Integer.parseInt(informacion[2]));
//                        user.setNombre(informacion[3]);
//                        user.setEmail(informacion[4]);
//                        user.setPassword(informacion[5]);
//                        user.setRol(informacion[6]);
//                        db.Update(user);
//
//
//                      }else if(informacion[1].equals("del")){
//                      user.setId_usuario(Integer.parseInt(informacion[2]));
//                      db.Delete(user);
//
//                       }else if(informacion[1].equals("table")){
//                           Usuario user= new Usuario();
//                          user.setId_usuario(Integer.parseInt(informacion[2]));
//                          user=db.search(user);
//                          if(user.getNombre()!=null){
//                             mensaje= user.getId_usuario()+"-"+user.getNombre()
//                                     +"-"+user.getEmail()+"-"+user.getPassword()
//                                     +"-"+user.getRol();
//                          }else
//                              mensaje="0-";
//                      }
//
//                      else if(informacion[1].equals("login")){
//                          user.setEmail(informacion[2]);
//                          user.setPassword(informacion[3]);
//                          user=db.Login(user);
//                          if(user.getRol()!=null){
//                              mensaje = user.getRol()+"-"+user.getEmail()+"-"+ user.getPassword();
//                          }else{
//                              mensaje="0-No se encontro-";
//                          }
//
//                      }
//                    }
//                    datos=mensaje.getBytes();
//                    DatagramPacket enviarPack = new DatagramPacket(datos, datos.length,
//                     recibirPaquete.getAddress(), recibirPaquete.getPort());
//                    socket.send(enviarPack);
//                }
//                mostrarMensaje("\nRegistro Ingresado:"
//                + "\nDel host:" + recibirPaquete.getPort()
//                + "\nInformacion almacenada:\n\t" + new String(recibirPaquete.getData(),
//                0, recibirPaquete.getLength()) + datos);
//            //envida paquete al cliente
//            }catch (IOException excepcionES) {
//                mostrarMensaje(excepcionES.toString() + "\n");
//                excepcionES.printStackTrace();
//            }
//        }
//    }
//    private void enviarPaqueteACliente(DatagramPacket recibirPaquete) throws IOException {
//        byte datos[] = new byte[100];
//        datos = mensaje.getBytes();
//        DatagramPacket enviarPaquete = new DatagramPacket(
//                datos, datos.length,
//                recibirPaquete.getAddress(), recibirPaquete.getPort());
//        socket.send(enviarPaquete);//enviar el paquete
//        mostrarMensaje("Paquete enviado\n");
//    }
//    private void mostrarMensaje(final String mensajeAMostrar) {
//         SwingUtilities.invokeLater(
//                new Runnable() {//clase interna para asegurar que la giu se actualice apropiadamente
//                    public void run()//actualiza areaPantalla
//                    {
//                        areaPantalla.append(mensajeAMostrar);
//                        areaPantalla.setCaretPosition(
//                                areaPantalla.getText().length());
//                    }
//                }
//                );
//    }
//
//    @SuppressWarnings("unchecked")
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">
//    private void initComponents() {
//
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        GroupLayout layout = new GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addGap(0, 400, Short.MAX_VALUE)
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addGap(0, 300, Short.MAX_VALUE)
//        );
//
//        pack();
//    }// </editor-fold>
//
//    public static void main(String args[]) throws SQLException {
//        Servidor aplicacion = new Servidor();
//        aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        aplicacion.esperarPaquetes();
//    }
//    // Variables declaration - do not modify
//    // End of variables declaration
//}
