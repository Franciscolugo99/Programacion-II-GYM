

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Vista {

    static Scanner Leer = new Scanner(System.in);
    static Scanner Leer2 = new Scanner(System.in);

    public static Cliente agregarCliente() {
        Cliente c = new Cliente();
        System.out.println("1- Agregar Nuevo Cliente");
        Scanner Leer2 = new Scanner(System.in);
        System.out.println("Ingrese su nombre");
        c.setNombre(Leer2.nextLine());
        System.out.println("Ingrese su apellido");
        c.setApellido(Leer2.nextLine());
        System.out.println("Ingrese su edad");
        c.setEdad(Leer2.nextInt());
        System.out.println("Ingrese su DNI");
        c.setDni(Leer2.nextInt());
        System.out.println("Seleccione un Plan");
        System.out.println("1 - Mes completo  Precio -- $3000");
        System.out.println("2 - Medio Mes     Precio -- $1500");
        int op = Leer.nextInt();
        if (op == 1) {
            System.out.println("Mes completo");
            c.setPlan(3000);
        } else {
            System.out.println("Medio Mes");
            c.setPlan(1500);
        }
        return c;
    }


    public static int menuC() {
        try {
            System.out.println("-----------------------------------------");
            System.out.println("Menu Gym");
            System.out.println("1- Agregar Nuevo Cliente");
            System.out.println("2- Ver Clientes Registrados");
            System.out.println("3- Eliminar Clientes Registrados");
            System.out.println("4- Cambiar Couta de Cliente");
            System.out.println("5- Salir");
            System.out.println("-----------------------------------------");
            var opcM = Leer.nextInt();
            return opcM;
        } catch (InputMismatchException ex) {
            return 0;
        }
    }

    public static void verC(List<Cliente> al) {
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


}


