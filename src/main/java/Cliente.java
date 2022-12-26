import java.io.Serializable;
import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Cliente implements Serializable {
    private String nombre;
    private String apellido;
    private int edad;
    private int dni;

    private int plan;
    private int estado;

    public Cliente() {
    }


    public Cliente(String nombre, String apellido, int edad, int DNI, int plan ,int estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.dni = DNI;
        this.plan = plan;
        this.estado = estado;

    }

    public static void buscarCliente(int dni) {
        try {
            Connection conn = Conexion.getInstancia().conectar();
            Scanner Leer2 = new Scanner(System.in);
            String queryBuscar = "SELECT * From Clientes where DNI =" + dni;
            Statement consulta = conn.createStatement();
            ResultSet registro = consulta.executeQuery(queryBuscar);
            if (registro.next()) {
                System.out.println(registro.getInt(1) + " #Nombre:" + registro.getString(2) +
                        " #Apellido:" + registro.getString(3) + " #Edad:" + registro.getInt(4) +
                        " #DNI:" + registro.getInt(5) + " #Couta:" + registro.getInt(6));
                System.out.println(" ");
                System.out.println("Ingrese la nueva Couta");
                String couta = Leer2.nextLine();
                String queryUp = "UPDATE `gym`.`Clientes` SET Couta = '" + couta + "' WHERE (`DNI` =" + dni + ")";
                int countUpdate = conn.prepareStatement(queryUp).executeUpdate();
                System.out.println("Cambios realizados:" + countUpdate);
            } else {
                System.out.println("DNI de cliente no encontrado");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getDni() {
        return dni;
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

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", edad=" + edad +
                ", DNI=" + dni +
                ", plan=" + plan +
                '}';
    }

    public void saveClient(int f) {
        try {
            Connection con = Conexion.getInstancia().conectar();
            PreparedStatement QueryInsert = con.prepareStatement("Insert into Clientes value (?,?,?,?,?,?)");
            QueryInsert.setInt(1, f);
            QueryInsert.setString(2, nombre);
            QueryInsert.setString(3, apellido);
            QueryInsert.setInt(4, edad);
            QueryInsert.setInt(5, dni);
            QueryInsert.setInt(6, plan);
            int clienteInsert = QueryInsert.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error");
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDni());
    }
}