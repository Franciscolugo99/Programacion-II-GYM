import java.util.Scanner;

public class prueba {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        prueba ejemplo = new prueba();
        String entrada;
        boolean b;
        /*do {
            System.out.println("Escriba");
            entrada = leer.nextLine();
            b = validar(entrada);
            System.out.println(validar2(entrada));
        } while (b == true);

         */

        validarEdad(leer.nextLine());
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


    public static boolean validar(String cadena) {
        if (cadena.matches("[0-9]*")) {
            return true;
        } else {
            return true;
        }
    }
    public static boolean validar1(String cadena) {
        if (cadena.matches("[a-z]{20}")) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean validar2(String cadena) {
        if (cadena.matches("[0-9]{1,2}")) {
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

    public static int convertir(String numero) {
        // Se reemplazan todos los caracteres que no correspondan a un numero
        // por espacio
        numero = numero.replaceAll("[^0-9]", "");

        // Si la cadena queda vacia
        if (numero.equals("")) {
            numero = "0";
        }

        return Integer.parseInt(numero);
    }


}
