package Main;

import Parqueo.Parqueadero;
import Parqueo.RegistroParqueo;
import usuarios.Bicicleta;
import usuarios.Propietario;
import Pagos.MetodoPago;

public class Main {

    public static void main(String[] args) {

        Parqueadero parqueadero = new Parqueadero();

        Propietario propietario =
                new Propietario("108420157", "Jose");

        Bicicleta bicicleta =
                new Bicicleta("ASD123", "Negro");

        propietario.registrarBicicleta(bicicleta);

        RegistroParqueo registro =
                parqueadero.registrarIngreso(bicicleta);

        System.out.println();

        System.out.println("¿La identidad es correcta?");
        System.out.println(
                propietario.verificarIdentidad("108420157")
        );

        System.out.println();

        // Para probar la salida
        parqueadero.registrarSalida(
                propietario.obtenerCedula(),
                registro,
                MetodoPago.TARJETA
        );

        System.out.println();

        System.out.println(
                "Cupos disponibles: "
                        + parqueadero.consultarCuposDisponibles()
        );
    }
}