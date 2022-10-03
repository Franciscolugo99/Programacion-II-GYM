import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class Controlador {
    public static void main(String[] args) {
        Modelo model = new Modelo();
        boolean bandera = true;
        do {
            try {
                int opc = Vista.menuC();
                try {
                    if (0 != opc) {
                        switch (opc) {
                            case 1:
                                Connection conn = Conexion.getInstancia().conectar();
                                Cliente cli = Vista.agregarCliente();
                                int filas = model.recupararID(conn);
                                model.saveClient(cli, filas);
                                break;
                            case 2:
                                var list = model.recuperarClientes();
                                Vista.verC(list);
                                break;
                            case 3:
                                var list2 = model.recuperarClientes();
                                Vista.verC(list2);
                                String rem = Vista.eliminarCliente();
                                if (rem.equals("s")) {
                                    System.out.println("Cancelado...");
                                    break;
                                } else {
                                    model.eliminarCliente(rem);
                                }
                                break;
                            case 4:
                                int dni = Vista.saveDni();
                                String couta = Vista.newCouta();
                                model.buscarCliente(dni, couta);
                                break;
                            case 5:
                                bandera = false;
                                Conexion.cerrarConex();
                                break;
                        }
                    } else {
                        System.out.println(":( Dato no valido ");
                        Conexion.cerrarConex();
                        break;
                    }
                } catch (InputMismatchException ex) {
                    System.out.println(ex + " fallo");
                    Conexion.cerrarConex();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e + " La connexion a la Base de Datos fallo");
            }
        } while (bandera != false);

    }
}