package Parcial3.modelos;

/**
 * Created by Erik on 1/7/2018.
 */
public class Paciente {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String ciudad;
    private String email;

    public Paciente() {
        this.id = -1;
        this.nombre = "";
        this.direccion = "";
        this.telefono = "";
        this.ciudad = "";
        this.email = "";
    }

    public Paciente(Paciente p) {
        this.id = p.id;
        this.nombre = p.nombre;
        this.direccion = p.direccion;
        this.telefono = p.telefono;
        this.ciudad = p.ciudad;
        this.email = p.email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
