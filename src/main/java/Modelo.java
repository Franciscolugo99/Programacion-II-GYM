import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Modelo {

    static Scanner Leer = new Scanner(System.in);
    static Scanner Leer2 = new Scanner(System.in);


    public Modelo() {
    }

    public static Connection conctarBD() throws SQLException {
        Connection conn = Conexion.getInstancia().conectar();
        return conn;
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

    public static int recupararID(Connection con) {
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


    public static void eliminarCliente(String rem) {
        String queryDL = "UPDATE `gym`.`clientes` SET `Estado` = '1' WHERE (`DNI` =" + rem + ")";
        try {
            Connection con = Conexion.getInstancia().conectar();
            Statement statement = con.createStatement();
            statement.executeUpdate(queryDL);
            System.out.println("Cliente Eliminado");

        } catch (SQLException e) {
            System.out.println("Fallo eliminar de la base de datos");
        }

    }

    public static boolean saveClient(Cliente c) {
        boolean salida;
        try {
            Connection con = Conexion.getInstancia().conectar();
            PreparedStatement QueryInsert = con.prepareStatement("Insert into Clientes value (?,?,?,?,?,?,?)");
            QueryInsert.setString(1, null);
            QueryInsert.setString(2, c.getNombre());
            QueryInsert.setString(3, c.getApellido());
            QueryInsert.setInt(4, c.getEdad());
            QueryInsert.setInt(5, c.getDni());
            QueryInsert.setInt(6, c.getPlan());
            QueryInsert.setInt(7, c.getEstado());
            int clienteInsert = QueryInsert.executeUpdate();
            salida = true;
            //--------------------------------------------
        } catch (SQLException ex) {
            System.out.println("Error");
            salida = false;
        }
        return salida;
    }

    public static List<Cliente> recuperarClientes() {
        List<Cliente> result = new ArrayList<>();
        try {
            Connection conn = Conexion.getInstancia().conectar();
            ResultSet rs = conn.prepareStatement("Select * from Clientes where Estado = 1;").executeQuery();
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
            System.out.println("Error Sql, recuperado del txt ");
             result = Modelo.recuperarTxt();

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


    // Base de datos txt serializable

    public static void guardarTxt(Cliente o){
        ArrayList<Cliente> clientesTxt = new ArrayList<>();
        clientesTxt.addAll(Modelo.recuperarTxt());
        clientesTxt.add(o);
        try {
            ObjectOutputStream escribiendoFihcero= new ObjectOutputStream(new FileOutputStream("C:/MisFicheros/cliente.txt"));
            escribiendoFihcero.writeObject(clientesTxt);
            escribiendoFihcero.close();
        }catch (Exception e){
            System.out.println("Fallo la conexion del txt guardar");
        }
    }
    public static void guardarTxt2(List <Cliente> Lc){
        ArrayList<Cliente> clientesTxt = (ArrayList<Cliente>) Lc;
        try {
            ObjectOutputStream escribiendoFihcero= new ObjectOutputStream(new FileOutputStream("C:/MisFicheros/cliente.txt"));
            escribiendoFihcero.writeObject(Lc);
            escribiendoFihcero.close();
        }catch (Exception e){
            System.out.println("Fallo la conexion del txt guardar");
        }
    }

    public static List<Cliente> recuperarTxt() {
        List<Cliente> recuperarTxt = new ArrayList<>();

        try {
            ObjectInputStream recuperandoFichero = new ObjectInputStream(new FileInputStream("C:/MisFicheros/cliente.txt"));
            recuperarTxt = (ArrayList<Cliente>) recuperandoFichero.readObject();

        } catch (Exception e) {
            System.out.println("Fallo la conexion del txt recuperar");
        }
        return recuperarTxt;
    }

// 46253381
    public static List<Cliente> buscarClienteTxt(int dni, String couta) {
        var cli = recuperarTxt();
        try {
            for (int i = 0; i < cli.size(); i++) {
                if (cli.get(i).getDni() == dni) {
                    cli.get(i).setPlan(Integer.parseInt(couta));
                    System.out.println("Encontrado");
                    System.out.println(cli.get(i));
                    guardarTxt2(cli);
                    break;
                }else {
                    System.out.println("no econtrado");
                }
            }
            return cli;
        } catch (Exception e) {
            return cli;
        }

    }

    public static  void eliminarClienteTxt (String dni){
        var clientes = recuperarTxt();
        for (int i = 0; i <clientes.size(); i++) {
            if (Integer.parseInt(dni) == clientes.get(i).getDni()) {
                clientes.remove(i);
                System.out.println("Eliminado correctamente");
                guardarTxt2(clientes);
                break;
            }else {
                System.out.println("El usuario no fue encontrado");
            }
        }

    }



}


