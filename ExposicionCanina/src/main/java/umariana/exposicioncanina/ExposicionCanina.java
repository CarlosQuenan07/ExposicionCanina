package umariana.exposicioncanina;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import mundo.Perro;

/**
 *@author
 * Sebastian Felipe Moreno 
 * Carlos Andres Quenan
 */
public class ExposicionCanina {
    //creacion de una contenedora y inicializacion de un scanner
    private ArrayList<Perro> misPerros= new ArrayList<>();
    Scanner lector = new Scanner(System.in);
    
    //Metodo para mostrar el menu al usuario
    public void mostrarMenu() throws NombreDuplicadoExcepcion{
        boolean activo=true;
        do{
           System.out.println("====== MENU DE OPCIONES ======\n"
            +"1.Agregar un perro al sistema\n"
            +"2.Editar datos de un perro \n"
            +"3.Consultar lista de perros\n"
            +"4.Consultar datos de un perro en especifico\n"
            +"5.Consultar datos del perro ganador\n"
            +"6.Consultar datos del perro con menor puntaje\n"
            +"7.consultar datos del perro con mayor edad\n"
            +"8.borrar a un perro del sistema\n"
            +"9.Salir del programa");
           System.out.println("===========================================");
            
            // Leer la opción seleccionada por el usuario
            int opcion=lector.nextInt();
            //Creacion de un Switch para cada opcion del menu
            switch(opcion){
              case 1:
                  try {
                      agregarPerro();  
                  } 
                  catch (NombreDuplicadoExcepcion e){
                      System.out.println(e.getMessage());
                      System.out.println("===========================================");
                  }
                
              break;
              
              case 2:
                editarPerro();
              break;

              case 3:
                listarPerros();
              break;

              case 4:
                consultarPerro();
              break;
              
              case 5:
                consultarGanador();
              break;
              
              case 6:
                consultarPerdedor();
              break; 
              
              case 7:
                consultarViejo();
              break;
              
              case 8:
                borrarPerro();
              break;
                
              case 9:
                activo=false;
                System.out.println("Programa terminado");
                System.out.println("===========================================");
              break;

              default:
                System.out.println("Opcion no valida, intentalo nuevamente"); 
                System.out.println("===========================================");
            }
        }while(activo);
    }
    
    //Metodo para agregar un perro al sistema con una excepcion integrada
    public void agregarPerro() throws NombreDuplicadoExcepcion {
        System.out.println("===========Ingresa Los Datos Del Perro==============");
        System.out.println("Ingrese nombre del perro:");
        lector.nextLine(); 
        String nombre = lector.nextLine();
        // if para que muestre una excepcion en caso de que se quiera 
        // ingresar a un perro con un nombre repetido
        if (!misPerros.isEmpty()) {
            for (Perro p : misPerros) {
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    throw new NombreDuplicadoExcepcion();
                }
            }
        }
        System.out.println("Ingrese la raza del perro:");
        String raza = lector.nextLine();

        System.out.println("Ingrese la edad en años del perro:");
        int edad = lector.nextInt();
        lector.nextLine(); // Consumir el carácter de nueva línea pendiente

        System.out.println("Ingrese los puntos que obtuvo el perro:");
        int puntos = lector.nextInt();
        lector.nextLine(); // Consumir el carácter de nueva línea pendiente

        System.out.println("Ingrese el nombre de la imagen del perro:");
        String imagen = lector.nextLine();

        Perro nuevoPerro = new Perro(nombre, raza, edad, puntos, imagen);
        misPerros.add(nuevoPerro);
        System.out.println("El perro fue agregado al sistema");
        System.out.println("===========================================");
    }
    
    //Metodo para editar la informacion de un perro 
    private void editarPerro() {
        
        // if para mostrar un mesaje en caso de que no haya perros en la contenedora
        if (misPerros.isEmpty()) {
            System.out.println("Aun no hay perros registrados.");
            System.out.println("===========================================");
            return;
        }

        // Solicitar al usuario que seleccione el perro a modificar
        System.out.println("Ingrese el nombre del perro a editar: ");
        lector.nextLine();
        String nombrePerroEditar = lector.nextLine(); 

        // Buscar el perro con el nombre que brindó el usuario
        Perro perroEditar = null;
        for (Perro perro : misPerros) {
            if (perro.getNombre().equalsIgnoreCase(nombrePerroEditar)) {
                perroEditar = perro;
                break;
            }
        }

        // Verificar si se encontró el perro con el nombre proporcionado
        if (perroEditar == null) {
            System.out.println("NO SE ENCONTRO NINGUN PERRO CON ESE NOMBRE.");
            System.out.println("===========================================");
            return;
        }

        // Solicitar al usuario los nuevos datos
        System.out.println("======Ingrese los nuevos datos del perrito======");
        System.out.println("Ingrese el nombre del perro: ");
        String nuevoNombre = lector.nextLine();
        System.out.println("Ingrese la raza del perro: ");
        String nuevaRaza = lector.nextLine();   
        System.out.println("Ingrese la edad del perro: ");
        int nuevaEdad = lector.nextInt();
        System.out.println("Ingrese los puntos que obtuvo el perro: ");
        int nuevosPuntos = lector.nextInt();
        lector.nextLine(); // Consumir el carácter de nueva línea pendiente
        System.out.println("Ingrese el nombre de la imagen del perro: ");
        String nuevaFoto = lector.nextLine();  

        // Actualizar los datos del perro seleccionado
        perroEditar.setNombre(nuevoNombre);
        perroEditar.setRaza(nuevaRaza);
        perroEditar.setEdad(nuevaEdad);
        perroEditar.setPuntos(nuevosPuntos);
        perroEditar.setFoto(nuevaFoto);

        System.out.println("Los datos se han editado exitosamente:");
        System.out.println("===========================================");
    }

    //Metodo para listar a los perros por la edad, raza o puntaje.
    private void listarPerros () {

        //if para mostrar un mesaje en caso de que no haya perros en la contenedora
        if (misPerros.isEmpty()) {
        System.out.println("Aun no hay perros registrados.");
        System.out.println("===========================================");
        return;
        } 

        System.out.println("====== LISTAR PERROS======\n"
            +"1.Listar perros por edad\n"
            +"2.Listar perros por raza\n"
            +"3.Listar perros por puntaje\n");
        System.out.println("===========================================");
            
        // Leer la opción seleccionada por el usuario
        int opcion1=lector.nextInt();
        //Creacion de un Switch para cada opcion del menu
        switch(opcion1){
            case 1:
                System.out.println("====Listado de perros ordenada por la edad====");
                //Metodo burbuja para la comparacion de la edad de cada uno de los perros 
                for (int i=0; i<misPerros.size()-1;i++){
                    for (int j=i+1; j<misPerros.size();j++){
                        Perro perro1 = misPerros.get(i);
                        Perro perro2 = misPerros.get(j);
                        if (perro1.getEdad()< perro2.getEdad()){
                            misPerros.set(i, perro2);
                            misPerros.set(j, perro1);     
                        }
                    }
                }
                for (Perro i: misPerros){
                    System.out.println( i.getNombre());
                    System.out.println("Raza: \n"+ i.getRaza());
                    System.out.println("Edad: \n"+ i.getEdad());
                    System.out.println("Puntos: \n"+ i.getPuntos());
                    System.out.println("Imagen: \n"+ i.getFoto());
                    System.out.println("----------------------------------------"); 
                }
            break;
             
            case 2:
                System.out.println("====Listado de perros ordenado por raza====");
                // Utilizamos el método sort de la clase Collections para ordenar la lista de perros por raza
                Collections.sort(misPerros, new Comparator<Perro>() {
                    public int compare(Perro perro1, Perro perro2) {
                        return perro1.getRaza().compareTo(perro2.getRaza());
                    }
                });
                // Mostramos la lista ordenada
                for (Perro i : misPerros) {
                    System.out.println(i.getNombre());
                    System.out.println("Raza: \n" + i.getRaza());
                    System.out.println("Edad: \n" + i.getEdad());
                    System.out.println("Puntos: \n" + i.getPuntos());
                    System.out.println("Imagen: \n" + i.getFoto());
                    System.out.println("----------------------------------------");
                }
            break;

            case 3:
                
                System.out.println("====Listado de perros ordenado por puntaje====");
                //Metodo burbuja para la comparacion de la edad de cada uno de los perros
                for (int i=0; i<misPerros.size()-1;i++){
                    for (int j=i+1; j<misPerros.size();j++){
                        Perro perro1 = misPerros.get(i);
                        Perro perro2 = misPerros.get(j);
                        if (perro1.getPuntos()< perro2.getPuntos()){
                            misPerros.set(i, perro2);
                            misPerros.set(j, perro1);     
                        }
                    }
                }
                for (Perro i: misPerros){
                    System.out.println( i.getNombre());
                    System.out.println("Raza: \n"+ i.getRaza());
                    System.out.println("Edad: \n"+ i.getEdad());
                    System.out.println("Puntos: \n"+ i.getPuntos());
                    System.out.println("Imagen: \n"+ i.getFoto());
                    System.out.println("----------------------------------------"); 
                }
            break;
            default:
                System.out.println("Opcion no valida, intentalo nuevamente"); 
                System.out.println("===========================================");
        }
    }
    
    //Metodo para consultar los datos de un perro en especifico
    private void consultarPerro() {
        //if para mostrar un mesaje en caso de que no haya perros en la contenedora
        if (misPerros.isEmpty()) {
        System.out.println("Aun no hay perros registrados.");
        System.out.println("===========================================");
        return;
        }
        // Solicitar al usuario el nombre del perro que quiere consultar
        System.out.println("Ingrese el nombre del perro:");
        lector.nextLine();
        String nombrePerroConsultar = lector.nextLine(); 

        // Buscar el perro con el nombre brindado por el usuario
        Perro perroConsultado = null; 
        for (Perro perro : misPerros) {
            if (perro.getNombre().equals(nombrePerroConsultar)) { 
                perroConsultado = perro; 
                break;
            }
        }

        // Verificar si se encontró el perro
        if (perroConsultado == null) {
            System.out.println("NO SE ENCONTRO NINGUN PERRO CON ESE NOMBRE.");
            System.out.println("===========================================");
            return;
        }
        
        //se muestra los datos del perro consultado
        System.out.println("=========Perro consultado===========");
        System.out.println(perroConsultado.getNombre());
        System.out.println("Raza: "+ perroConsultado.getRaza());
        System.out.println("Edad: "+ perroConsultado.getEdad());
        System.out.println("Puntaje: "+ perroConsultado.getPuntos());
        System.out.println("Imagen: "+ perroConsultado.getFoto());
        System.out.println("===========================================");
    }
    
     private void consultarGanador() {
         //if para mostrar un mesaje en caso de que no haya perros en la contenedora
        if (misPerros.isEmpty()) {
        System.out.println("Aun no hay perros registrados.");
        System.out.println("===========================================");
        return;
        }

        // se crea variable de perro con mayor puntaje inicializada en 0
        Perro perroConMayorPuntaje = misPerros.get(0); 
        //se busca en la contenedora el perro con mayor puntaje comparando la variable anterior
        for (int i = 1; i < misPerros.size(); i++) {
            Perro perroActual = misPerros.get(i);
            if (perroActual.getPuntos() > perroConMayorPuntaje.getPuntos()) {
            perroConMayorPuntaje = perroActual;
            }
        }
        //Se muestran los datos del perro encontrado
        System.out.println("==========Perro ganador=============");
         System.out.println(perroConMayorPuntaje.getNombre());
         System.out.println("Raza:"+perroConMayorPuntaje.getRaza());
         System.out.println("Edad: "+perroConMayorPuntaje.getEdad());
         System.out.println("Puntaje: "+ perroConMayorPuntaje.getPuntos());
         System.out.println("Imagen: "+ perroConMayorPuntaje.getFoto());
         System.out.println("===========================================");
     }
     
     //Metodo para mostrar los datos del perro con menor puntaje
     private void consultarPerdedor() {
         //if para mostrar un mesaje en caso de que no haya perros en la contenedora
        if (misPerros.isEmpty()) {
        System.out.println("Aun no hay perros registrados.");
        System.out.println("===========================================");
        return;
        }

        // se crea variable de perro con menor puntaje inicializada en 0
        Perro perroConMenorPuntaje = misPerros.get(0); 
        //se busca en la contenedora el perro con mayor puntaje comparando la variable anterior
        for (int i = 1; i < misPerros.size(); i++) {
            Perro perroActual = misPerros.get(i);
            if (perroActual.getPuntos() < perroConMenorPuntaje.getPuntos()) {
            perroConMenorPuntaje = perroActual;
            }
        }
        //Se muestran los datos del perro encontrado
         System.out.println("============Perro Con Menor Puntaje=============");
         System.out.println(perroConMenorPuntaje.getNombre());
         System.out.println("Raza: "+perroConMenorPuntaje.getRaza());
         System.out.println("Edad: "+perroConMenorPuntaje.getEdad());
         System.out.println("Puntaje: "+ perroConMenorPuntaje.getPuntos());
         System.out.println("Imagen: "+ perroConMenorPuntaje.getFoto());
         System.out.println("===========================================");
     }
     
     //Metodo para mostrar los datos del perro con mayor edad
     private void consultarViejo() {
        // if para mostrar un mesaje en caso de que no haya perros en la contenedora
        if (misPerros.isEmpty()) {
            System.out.println("Aún no hay perros registrados.");
            System.out.println("===========================================");
            return;
        }

        // Se inicializa el perro más viejo como el primer perro de la lista
        Perro perroViejo = misPerros.get(0);

        // Se busca el perro más viejo
        for (int i = 1; i < misPerros.size(); i++) {
            Perro perroActual = misPerros.get(i);
            if (perroActual.getEdad() > perroViejo.getEdad()) {
                perroViejo = perroActual;
            }
        }

        // Se muestran los datos del perro encontrado
        System.out.println("===========Perro Con Mayor Edad=============");
        System.out.println(perroViejo.getNombre());
        System.out.println("Raza: " + perroViejo.getRaza());
        System.out.println("Edad: " + perroViejo.getEdad());
        System.out.println("Puntos: " + perroViejo.getPuntos());
        System.out.println("Imagen: " + perroViejo.getFoto());
        System.out.println("===========================================");
    }

     //Metodo para borrar a un perro del sistema
     private void borrarPerro() {
         //if para mostrar un mesaje en caso de que no haya perros en la contenedora
        if (misPerros.isEmpty()) {
            System.out.println("Aun no hay perros registrados.");
            System.out.println("===========================================");
            return;
        }

        // Solicitar al usuario que seleccione el nombre del perro que desea eliminar
        System.out.println("=====Ingrese el nombre del perro que desea eliminar:=====");
        String nombrePerroEliminar = lector.next();

        // Buscar el perro con el nombre proporcionado por el usuario
        Perro perroEliminar = null;
        for (Perro perro : misPerros) {
            if (perro.getNombre().equalsIgnoreCase(nombrePerroEliminar)) {
                perroEliminar = perro;
                break;
            }
        }

        // Verificar si se encontró el perro a eliminar
        if (perroEliminar == null) {
            System.out.println("NO SE ENCONTRO NINGUN PERRO CON ESE NOMBRE.");
            System.out.println("===========================================");
            return;
            
        }

        // Eliminar el perro de la lista
        misPerros.remove(perroEliminar);
        System.out.println("El perro ha sido eliminado correctamente.");
        System.out.println("===========================================");
    }
    
    public static void main(String[] args) throws NombreDuplicadoExcepcion {
        ExposicionCanina expo = new ExposicionCanina();
        expo.mostrarMenu();
    }
}