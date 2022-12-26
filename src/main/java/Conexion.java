import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {

    private  Conexion(){

    }

    //creo una variable en la que guardo el estado de la conxion de BD
    private static Connection conn;

    //creo una variable para crear una sola instancia
    private static Conexion instancia;


    private  static final String URL = "jdbc:mysql://localhost:3306/GYM";
    private  static final String User = "root";
    private  static final String Pass = "root";

    public Connection conectar () throws SQLException {
        try {
            conn =DriverManager.getConnection(URL, User, Pass);
            System.out.println("Connexion exitosa");
             return conn;
        }catch (Exception e){
            System.out.println("Fallo la Connexion ");
        }

        return conn;
    }

    public static void cerrarConex() throws SQLException {
        try {
            System.out.println("La conexión se cerro ");
            conn.close();
        } catch (Exception e) {
           /* System.out.println("La connexion se cerro ");
            conn.close();
            */

        }/* finally {
            conn.close();
        }
        */

    }

    public static Conexion getInstancia(){
        if (instancia == null){
            instancia = new Conexion();
        }
            return instancia;

    }



}
