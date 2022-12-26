import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Controlador {
    public static void main(String[] args) {

        boolean bandera = true;
        int opc=0;
        do {
            try {
                opc = Vista.menuC();
                Cliente cli = null;
                try {
                    if (0 != opc) {
                        switch (opc) {
                            case 1:
                                cli = Vista.agregarCliente();
                                try {
                                    Modelo.saveClient(cli);
                                } catch (Exception e) {
                                    Modelo.guardarTxt(cli);
                                }
                                break;
                            case 2:
                                try {
                                    var list = Modelo.recuperarClientes();
                                    Vista.verC(list);
                                } catch (Exception e) {
                                    var list3 = Modelo.recuperarTxt();
                                    Vista.verC(list3);
                                }
                                break;
                            case 3:
                                try {
                                    var list2 = Modelo.recuperarClientes();
                                    Vista.ver(list2);
                                    String rem = Vista.eliminarCliente();
                                    if (rem.equals("s")) {
                                        System.out.println("Cancelado...");
                                        break;
                                    } else {
                                        Modelo.eliminarCliente(rem);
                                    }

                                }catch (Exception e){
                                    var list2 = Modelo.recuperarTxt();
                                    Vista.ver(list2);
                                    String rem = Vista.eliminarCliente();
                                    if (rem.equals("s")) {
                                        System.out.println("Cancelado...");
                                        break;
                                    } else {
                                        Modelo.eliminarClienteTxt(rem);
                                    }
                                }

                                break;
                            case 4:
                                int dni = Vista.saveDni();
                                String cuota = Vista.newCouta();
                                try {
                                    Modelo.buscarCliente(dni, cuota);
                                }catch (Exception e){
                                    Modelo.buscarClienteTxt(dni,cuota);
                                }

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