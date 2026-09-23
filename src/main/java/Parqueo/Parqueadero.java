package Parqueo;

import usuarios.Bicicleta;
import Pagos.Pago;
import Pagos.MetodoPago;

import java.util.ArrayList;

public class Parqueadero {

    private final int capacidad;
    private int cuposDisponibles;
    private final ArrayList<RegistroParqueo> registros;

    public Parqueadero() {
        capacidad = 20;
        cuposDisponibles = 20;
        registros = new ArrayList<>();
    }

    public RegistroParqueo registrarIngreso(Bicicleta bicicleta) {

        if (!verificarCupo()) {
            System.out.println("No hay cupos disponibles.");
            return null;
        }

        int id = registros.size() + 1;

        RegistroParqueo registro =
                new RegistroParqueo(id, bicicleta);

        registro.registrarIngreso();

        registros.add(registro);

        cuposDisponibles--;

        System.out.println("Ingreso registrado correctamente.");
        System.out.println("Serial: " + bicicleta.obtenerSerial());
        System.out.println("Cupos disponibles: " + cuposDisponibles);

        return registro;
    }

    public void registrarSalida(String cedula, RegistroParqueo registro,
                                MetodoPago metodo) {

        if (registro == null || !registro.estaActivo()) {
            System.out.println("No existe un registro activo.");
            return;
        }

        registro.registrarSalida();

        int minutos = registro.calcularMinutos();
        double valor = registro.calcularValor();

        Pago pago = new Pago(
                registro.obtenerIdRegistro(),
                valor,
                metodo
        );

        pago.realizarPago(valor);

        registro.establecerPago(pago);

        if (pago.verificarPago()) {
            liberarCupo();

            System.out.println("Salida registrada correctamente.");
            System.out.println("Cédula: " + cedula);
            System.out.println("Minutos: " + minutos);
            System.out.println("Valor a pagar: $" + valor);
            System.out.println("Método de pago: " + metodo);
        }
    }

    public boolean verificarCupo() {
        return cuposDisponibles > 0;
    }

    public void liberarCupo() {

        if (cuposDisponibles < capacidad) {
            cuposDisponibles++;
        }
    }

    public int consultarCuposDisponibles() {
        return cuposDisponibles;
    }

    public int obtenerCapacidad() {
        return capacidad;
    }
}
