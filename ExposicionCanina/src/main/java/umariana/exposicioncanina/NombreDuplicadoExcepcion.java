package umariana.exposicioncanina;

/**
  @author
 * Sebastian Felipe Moreno 
 * Carlos Andres Quenan
 
 */
// Se crea una clase con el fin de crear una excepcion para luego llamarla en la clase main
public class NombreDuplicadoExcepcion extends Exception{
    public NombreDuplicadoExcepcion (){
        super("YA EXISTE UN PERRO CON ESTE NOMBRE, INTENTE NUEVAMENTRE");
    }
}
