import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Modelo {
    static Scanner Leer = new Scanner(System.in);
    static Scanner Leer2 = new Scanner(System.in);


    public Modelo() {
    }

    public   void buscarCliente (Connection conn , int dni) {
        try{

            String queryBuscar = "SELECT * From Clientes where DNI ="+dni;
            Statement consulta = conn.createStatement();
            ResultSet registro = consulta.executeQuery(queryBuscar);
            if (registro.next()){
                String nom = registro.getString("DNI");
                System.out.println(registro.getInt(1) + " #Nombre:" + registro.getString(2) +
                        " #Apellido:" + registro.getString(3) + " #Edad:" + registro.getInt(4) +
                        " #DNI:" + registro.getInt(5) + " #Couta:" + registro.getInt(6));
                System.out.println(" ");
                System.out.println("Ingrese la nueva Couta");
                String couta = Leer2.nextLine();
                String queryUp = "UPDATE `gym`.`Clientes` SET Couta = '" + couta + "' WHERE (`DNI` =" + dni + ")";
                int countUpdate = conn.prepareStatement(queryUp).executeUpdate();
                System.out.println("Cambios realizados:"+ countUpdate);
            }else {
                System.out.println("DNI de cliente no encontrado");
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int recupararID(Connection con) {
        int id = 1;
        try {
            ResultSet Query;
            PreparedStatement QueryInsert;
            QueryInsert = con.prepareStatement("Select max(ID) from Clientes");
            Query = QueryInsert.executeQuery();
            while (Query.next()) id = Query.getInt(1) + 1;
        } catch (Exception e) {
            System.out.println("no hay clientes registrados");
        }
        return id;
    }


    public void eliminarCliente(String rem) {
        String queryDL = "DELETE FROM `gym`.`Clientes` WHERE (`DNI` =" + rem + ")";
        try {
            Connection con = Conexion.getInstancia().conectar();
            Statement statement = con.createStatement();
            statement.executeUpdate(queryDL);
            System.out.println("Cliente Eliminado");

        } catch (SQLException e) {
            System.out.println("Fallo eliminar de la base de datos");
        }

    }

    public void saveClient(Cliente c, int f) {
        try {
            Connection con = Conexion.getInstancia().conectar();
            PreparedStatement QueryInsert = con.prepareStatement("Insert into Clientes value (?,?,?,?,?,?)");
            QueryInsert.setInt(1, f);
            QueryInsert.setString(2, c.getNombre());
            QueryInsert.setString(3, c.getApellido());
            QueryInsert.setInt(4, c.getEdad());
            QueryInsert.setInt(5, c.getDni());
            QueryInsert.setInt(6, c.getPlan());
            int clienteInsert = QueryInsert.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error");
        }
    }

    public static List<Cliente> recuperarClientes() {
        List<Cliente> result = new ArrayList<>();
        try {
            Connection conn = Conexion.getInstancia().conectar();
            ResultSet rs = conn.prepareStatement("Select * from Clientes;").executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setNombre(rs.getString(2));
                cliente.setApellido(rs.getString(3));
                cliente.setEdad(rs.getInt(4));
                cliente.setDni(rs.getInt(5));
                cliente.setPlan(rs.getInt(6));
                result.add(cliente);
            }
        } catch (SQLException ex) {
            System.out.println("Error");
        }
        return result;
    }

    public static void buscarCliente(int dni, String couta) {
        try {
            Connection conn = Conexion.getInstancia().conectar();
            String queryBuscar = "SELECT * From Clientes where DNI =" + dni;
            Statement consulta = conn.createStatement();
            ResultSet registro = consulta.executeQuery(queryBuscar);
            if (registro.next()) {
                System.out.println(registro.getInt(1) + " #Nombre:" + registro.getString(2) + " #Apellido:" + registro.getString(3) + " #Edad:" + registro.getInt(4) + " #DNI:" + registro.getInt(5) + " #Couta:" + registro.getInt(6));
                System.out.println(" ");
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


}


