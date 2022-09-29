import java.sql.*;
import java.util.Scanner;

public class Modelo {
    static Scanner Leer = new Scanner(System.in);
    static Scanner Leer2 = new Scanner(System.in);

    public Modelo() {
    }

    public  void verClientes (ResultSet rs) throws SQLException {
        int con=0;
        while (rs.next()){
            con++;
            System.out.println(rs.getInt(1) + " #Nombre:" + rs.getString(2) + " #Apellido:" + rs.getString(3) + " #Edad:" + rs.getInt(4) + " #DNI:" + rs.getInt(5) + " #Couta:" + rs.getInt(6));
        }
        System.out.println("Total de Clientes Registrados: " +con);
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

    public  int Id (Connection con, PreparedStatement ps , ResultSet rs) {
        int id = 1;
        try{
            ps = con.prepareStatement("Select max(ID) from Clientes");
            rs = ps.executeQuery();
            while (rs.next())
                id = rs.getInt(1)+1;
        }catch (Exception e){
            System.out.println("no hay clientes registrados");
        }
        return id;
    }



    public void eliminarCliente(Statement st, String rem)  {
        try {

            st.executeUpdate(rem);
            System.out.println("Cliente Eliminado");

        }catch (SQLException e){
            System.out.println("Fallo eliminar de la base de datos");
        }

    }

    public void cerrarConex(Statement statement, ResultSet query , Connection conn) throws SQLException {
        statement.close();
        query.close();
        conn.close();
    }

}


