

import java.util.Objects;

public class Cliente {
    private String nombre;
    private String apellido;
    private int edad;
    private int DNI;

    private int plan;


    public Cliente() {
    }

    public Cliente(String nombre, String apellido, int edad, int DNI, int plan) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.DNI = DNI;
        this.plan = plan;

    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public void msjDeRegistro() {
        System.out.println("Nuevo Cliente Registrado Correctamente");
    }

    public void msjDeeliminar() {
        System.out.println(" Cliente Eliminado Correctamente");
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", DNI=" + DNI +
                ", plan=" + plan +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(getDNI());
    }
}