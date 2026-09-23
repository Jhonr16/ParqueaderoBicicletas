package usuarios;

public class Propietario {

    private String cedula;
    private String nombre;

    public Propietario(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public String obtenerCedula() {
        return cedula;
    }

    public void asignarCedula(String cedula) {
        this.cedula = cedula;
    }

    public String obtenerNombre() {
        return nombre;
    }

    public void asignarNombre(String nombre) {
        this.nombre = nombre;
    }

    public void registrarBicicleta(Bicicleta bicicleta) {
        System.out.println("Bicicleta registrada al propietario.");
    }

    public boolean verificarIdentidad(String cedula) {
        return this.cedula.equals(cedula);
    }
}