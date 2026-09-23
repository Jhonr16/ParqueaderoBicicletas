package usuarios;

public class Bicicleta {

    private String serial;
    private String color;

    public Bicicleta(String serial, String color) {
        this.serial = serial;
        this.color = color;
    }

    public String obtenerSerial() {
        return serial;
    }

    public void asignarSerial(String serial) {
        this.serial = serial;
    }

    public String obtenerColor() {
        return color;
    }

    public void asignarColor(String color) {
        this.color = color;
    }

    public void registrarIngreso() {
        System.out.println("La bicicleta ha ingresado al parqueadero");
    }
}
