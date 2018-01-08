package Parcial3.Test;

import Parcial3.modelos.Diagnostico;
import Parcial3.KnowledgeBase;

import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by Erik on 1/7/2018.
 * No es seguro correr este test una vez llenada la base de datos
 */
public class ConnectionTest {
    KnowledgeBase db;

    public ConnectionTest() {
        db = new KnowledgeBase();

        Diagnostico d = new Diagnostico();
        d.setTexto("Se va a morir");
        try {
            db.save(d);
            System.out.println("Agregado diagnostico '" + d.getTexto() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        LinkedList<Diagnostico> diagnosticos = new LinkedList<>();
        try {
            diagnosticos = db.getDiagnosticos();
            System.out.println("Diagnosticos obtenidos exitosamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(diagnosticos.size() > 2) {
            String antiguo = diagnosticos.get(2).getTexto();
            diagnosticos.get(2).setTexto("Ahora me he actualizado");
            try {
                db.update(diagnosticos.get(2));
                System.out.println("'" + antiguo + "' actualizado a '" + diagnosticos.get(2).getTexto() + "'");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                d = db.search(diagnosticos.get(2));
                System.out.println("Encontrado: " + d.getTexto());
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                db.delete(diagnosticos.get(2));
                System.out.println("Eliminado '" + d.getTexto() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ConnectionTest conn = new ConnectionTest();
    }
}
