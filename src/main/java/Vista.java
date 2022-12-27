

import com.mysql.cj.protocol.a.BinaryRowFactory;
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
        var name = Leer2.nextLine();
        var name2 = valiCadena(name);
        String nameValidado = validarDato(name);
        c.setNombre(nameValidado);


        System.out.println("Ingrese su apellido");
        var apellido = Leer2.nextLine();
        var apellido2 = valiCadena(apellido);
        String apellidoValidado = validarDato(apellido2);
        c.setApellido(apellidoValidado);


        System.out.println("Ingrese su edad");
        var edad = Leer2.nextLine();
        String edad2 = validarDato(edad);
        int edadValidado = validarEdad(edad2);
        c.setEdad(edadValidado);

        System.out.println("Ingrese su DNI");
        String dni = Leer.nextLine();
        var dni2 = validarDato(dni);
        var dniValidado = validarDni(dni2);
        int dn2 = Modelo.buscarDni(dni2);
        var dniValidado2 = validarDniExistente(dniValidado,dn2);
        c.setDni(Integer.parseInt(dniValidado2));



        System.out.println("Seleccione un Plan");
        System.out.println("1 - Mes completo  Precio -- $3000");
        System.out.println("2 - Medio Mes     Precio -- $1500");
        int op = Leer.nextInt();
        String op1 = validarDato(String.valueOf(op));
        int opcValidado = validarPlan(String.valueOf(op1));
        if (opcValidado == 1) {
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
            var opcM2 = validarDato(opcM);
            var opcMvalidado = validarMenu(opcM2);
            return opcMvalidado;
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
        System.out.println("-------------------------------------------------------");
        System.out.println("2- Eliminar Cliente");
        System.out.println("Ingrese el DNI del Cliente que desea Eliminar");
        System.out.println("Ingrese la letra S si quiere volver");
        System.out.println("-------------------------------------------------------");
        String rem = Leer2.nextLine();
        var rem2 = validarDato(rem);
        var rem3 = validarDni(rem2);
        return rem3;
    }

    public static int saveDni() {
        System.out.println("Ingrese el numero de DNI del Cliente");
        int dni = Leer.nextInt();
        var dni2 = validarDato(String.valueOf(dni));
        String dniValidado = validarDni(String.valueOf(dni2));
        return Integer.parseInt(dniValidado);
    }

    public static String newCouta() {
        System.out.println("Seleccione un Plan");
        System.out.println("1 - Mes completo  Precio -- $3000");
        System.out.println("2 - Medio Mes     Precio -- $1500");
        String couta = Leer2.nextLine();
        var couta2 = validarDato(couta);
        var cuotaValidada= validarPlan(couta2);
        if (cuotaValidada == 1) {
            return String.valueOf(3000);
        }else {
            return String.valueOf(1500);
        }
    }

    private static String validarDni(String cadena) {
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
        while (validar(cadena) == false){
            System.out.println("Dato ingresado no valido intente nuevamente ");
            cadena = Leer.nextLine();

        }
        return cadena;
    }


    private static int validarMenu(String cadena) {
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
            System.out.println("No a ingresado nada intente nuevamente");
            dato = Leer.nextLine();
        }
        return dato;
    }

    public static int validarPlan(String op) {
        Scanner Leer = new Scanner(System.in);
        while (op.matches("[1,2]") == false) {
            System.out.println("Ingrese un opcion correcta 1 o 2");
            op = Leer.nextLine();
        }
        return Integer.parseInt(op);
    }

    public static boolean validarDniExistente2(int dni2, String entrada) {
        if (dni2 == Modelo.buscarDni(entrada)) {
            return true;
        } else {
            return false;
        }

    }
    public static String validarDniExistente(String cadena , int  b) {
        Scanner Leer = new Scanner(System.in);
        String cadena2;
        boolean band = true;
        if (b == 0) {
            System.out.println("El dni no esta registrado");
            return cadena;

        }else {
            do {
                System.out.println("Ingrese un DNI no registrado");
                cadena2 = Leer.nextLine();
                
                if (validarDniExistente2(b,cadena2)){
                    band = false;
                }
            } while (cadena2.length() != 8 && band == false);
            return cadena2;
        }

    }



}

