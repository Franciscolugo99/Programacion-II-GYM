import junit.framework.Assert;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class gymTest  {
    @Test
    public void test1(){
        //Voy a testear si el metodo buscar cliente cumple la function de encontrar a
        // un usuario por el número de dni de la base de datos y luego lo comparo

        var list2 = Modelo.recuperarClientes();
        Cliente Cprueba = new Cliente("Francisco",  "Lugo",  23,  46253381,  5000,1);
         var clie= list2.get(3);
         assertEquals(clie.getDni(), Cprueba.getDni());


    }







}
