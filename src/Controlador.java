import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Controlador {
    public static void main(String[] args) {
        Scanner Leer = new Scanner(System.in);
        Modelo model = new Modelo();
        boolean bandera = true;
        do {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/GYM", "root", "root");
                Statement statement = conn.createStatement();
                ResultSet Query = statement.executeQuery("SELECT * From Clientes");
                PreparedStatement QueryInsert = conn.prepareStatement("Insert into Clientes value (?,?,?,?,?,?)");
                int opc = Vista.menuC();
                try {
                    if (0 != opc) {
                        switch (opc) {
                            case 1:
                                Cliente c = Vista.agregarCliente();
                                int filas = model.Id(conn,QueryInsert,Query);
                                QueryInsert.setInt(1, filas);
                                QueryInsert.setString(2, c.getNombre());
                                QueryInsert.setString(3, c.getApellido());
                                QueryInsert.setInt(4, c.getEdad());
                                QueryInsert.setInt(5, c.getDNI());
                                QueryInsert.setInt(6, c.getPlan());

                                int clienteInsert = QueryInsert.executeUpdate();
                                System.out.println(clienteInsert +" Cliente Registrado");


                                model.cerrarConex(statement,Query,conn);
                                break;
                            case 2:
                                model.verClientes(Query);

                                model.cerrarConex(statement,Query,conn);
                                break;
                            case 3:
                                System.out.println("----------------------------------------------");
                                model.verClientes(Query);
                                System.out.println("----------------------------------------------");
                                String rem = Vista.eliminarCliente ();
                                if (rem.equals("s")) {
                                    System.out.println("Cancelado...");
                                    break;
                                } else {
                                    String queryDL = "DELETE FROM `gym`.`Clientes` WHERE (`DNI` =" + rem + ")";
                                    model.eliminarCliente(statement,queryDL);
                                }

                                model.cerrarConex(statement,Query,conn);
                                 break;
                            case 4:
                               int dni =   Vista.modifCliente();
                                model.buscarCliente(conn, dni);

                                model.cerrarConex(statement,Query,conn);
                                break;
                            case 5:
                                bandera = false;
                                break;
                        }
                    } else {
                        System.out.println(":( Dato no valido ");
                        break;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println(ex + " fallo");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e + " La connexion a la Base de Datos fallo");
            }
        } while (bandera != false);

    }
}