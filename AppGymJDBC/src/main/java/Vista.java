

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Vista {

    static Scanner Leer = new Scanner(System.in);
    static Scanner Leer2 = new Scanner(System.in);
    static Cliente c = new Cliente();

    public static  Cliente agregarCliente() {
        Scanner Leer2 = new Scanner(System.in);
        System.out.println("1- Agregar Nuevo Cliente");

        System.out.println("Ingrese su nombre");
        var val = Leer2.nextLine();
        var name = valiCadena(val);
        String name1 = validarDato(name);
        c.setNombre(name1);


        System.out.println("Ingrese su apellido");
        var val1 = Leer2.nextLine();
        valiCadena(val1);
        String ape = validarDato(val1);
        c.setApellido(ape);


        System.out.println("Ingrese su edad");
        var edad = Leer2.nextLine();
        String eda = validarDato(edad);
        int ed = validarEdad(eda);
        c.setEdad(ed);

        System.out.println("Ingrese su DNI");
        String dni = Leer.nextLine();
         var d = esNumerico(dni);
         c.setDni(Integer.parseInt(d));


        System.out.println("Seleccione un Plan");
        System.out.println("1 - Mes completo  Precio -- $3000");
        System.out.println("2 - Medio Mes     Precio -- $1500");
        int op = Leer.nextInt();
        validarPlan(String.valueOf(op));
        if (op == 1) {
            System.out.println("Mes completo");
            c.setPlan(3000);
        } else {
            System.out.println("Medio Mes");
            c.setPlan(1500);
        }
        c.setEstado(1);
        return c;
    }


    public static int menuC() {
        Scanner Leer3 = new Scanner(System.in);
        String opcM;
        try {
            System.out.println("-----------------------------------------");
            System.out.println("Menu Gym");
            System.out.println("1- Agregar Nuevo Cliente");
            System.out.println("2- Ver Clientes Registrados");
            System.out.println("3- Eliminar Clientes Registrados");
            System.out.println("4- Cambiar Cuota de Cliente");
            System.out.println("5- Salir");
            System.out.println("-----------------------------------------");
            opcM = Leer3.nextLine();
            System.out.println("Dato ingresado: " + opcM);
           return esNumerico2(opcM);
        } catch (InputMismatchException ex) {
            System.out.println("Fallo la opción ingresada del menu ");
            return 0;
        }
    }

    public static void verC(List<Cliente> al) {
        Scanner Leer3 = new Scanner(System.in);
            for (int i = 0; i < al.size(); i++) {
                System.out.println(i + "-" + al.get(i));
            }
            int datoS = 0 ;
        System.out.println("-----------------------");
        System.out.println("Ingrese un número para volver al menú ");
            do {
                while (!Leer3.hasNextInt()){
                    String next = Leer3.next();
                    System.out.println(next + " No es un numero valido");
                    System.out.println("1- Volver al  menu");
                }
                datoS = Leer3.nextInt();
                break;
            }while (1 == datoS);

    }
    public static void ver(List<Cliente> al) {
        for (int i = 0; i < al.size(); i++) {
            System.out.println(i + "-" + al.get(i));
        }
    }





    public static String eliminarCliente() {
        System.out.println("2- Eliminar Cliente");
        System.out.println("Ingrese el DNI del Cliente que desea Eliminar");
        System.out.println("Ingrese la letra S si quiere volver");
        String rem = Leer2.nextLine();
        return rem;
    }

    public static int saveDni() {
        System.out.println("Ingrese el numero de DNI del Cliente");
        int dni = Leer.nextInt();
        return dni;
    }

    public static String newCouta() {
        System.out.println("Ingrese la nueva Couta");
        String couta = Leer2.nextLine();
        return couta;
    }

    private static String esNumerico(String cadena) {
            if (cadena.length() == 8 ) {
                return cadena;
            }else {
                do {
                    System.out.println("Ingrese un DNI valido intente de nuevo");
                    cadena = Leer.nextLine();
                } while (cadena.length() != 8);
                return cadena;
            }
    }

    public static boolean validar(String cadena) {
        if (cadena.matches("[a-z]*")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean validarInt(String cadena) {
        if (cadena.matches("[0-9]{1,2}")) {
            return true;
        } else {
            return false;
        }
    }


    public static String valiCadena( String cadena){
        Scanner Leer = new Scanner(System.in);
        if (validar(cadena)) {
            System.out.println("Correcto");
            return cadena;
        }else {
            String dato;
            do {
                System.out.println("Dato ingresado no valido intente nuevamente ");
                dato = Leer.nextLine();
                cadena = dato;
                return cadena;
            } while (validar(dato) == false);

        }
    }


    private static int esNumerico2(String cadena) {
        try {
            int dato = Integer.parseInt(cadena);

            if (dato < 6 && dato > 0) {
                return dato;
            }
        } catch (NumberFormatException e) {
            System.out.println("No ingresate un número intente nuevamente ");
        }
        return menuC();
    }


    public static int validarEdad(String cadena){
        Scanner Leer = new Scanner(System.in);
        int dato = Integer.parseInt(cadena);
        if (cadena.matches("[0-9]{2}")) {
           return dato;
        }else {
            String dato2;
            do {
                System.out.println("Ingrese una edad valida");
                dato2 = Leer.nextLine();
            } while (validarInt(dato2) == false);
            dato = Integer.parseInt(dato2);
            return dato;
        }

    }

    public static String validarDato(String dato){
        Scanner Leer = new Scanner(System.in);
        while (dato.equals("")){
            System.out.println("No a ingresado un dato intente de nuevo");
            dato = Leer.nextLine();
        }
        return dato;
    }

    public static int validarPlan(String op){
        Scanner Leer = new Scanner(System.in);
        int dato = Integer.parseInt(op);
            if (op.matches("[1-2]{1}")) {
                return dato;
            }else {
                do {
                    System.out.println("Ingrese un opcion correcta 1 o 2");
                    String entrada = Leer.nextLine();
                    return Integer.parseInt(entrada);
                }while (op.matches("[1-2]{1}"));

            }
    }

}

