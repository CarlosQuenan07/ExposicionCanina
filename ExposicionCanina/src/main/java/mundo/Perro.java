package mundo;

/**
 * @author
 * Sebastian Felipe Moreno 
 * Carlos Andres Quenan
 */
public class Perro {
    //Atributos
    private String nombre;
    private String raza;
    private int edad;
    private int puntos;
    private String foto;
    //constructores
    public Perro(String nombre, String raza, int edad, int puntos, String foto) {
    this.nombre = nombre;
    this.raza = raza;
    this.edad = edad;
    this.puntos = puntos;
    this.foto = foto;
    }
    //getters
    public String getNombre() {
        return nombre;
    }
    public String getRaza() {
        return raza;
    }
    public int getEdad (){
        return edad;
    }
    public int getPuntos (){
        return puntos;
    }
    public String getFoto() {
        return foto;
    }
    //setters
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }
    public void setRaza(String nuevaRaza) {
        this.raza = nuevaRaza;
    }
    public void setEdad(int nuevaEdad) {
        this.edad = nuevaEdad;
    }
    public void setPuntos(int nuevosPuntos) {
        this.puntos = nuevosPuntos;
    }
    public void setFoto(String nuevaFoto) {
        this.foto = nuevaFoto;
    }
}