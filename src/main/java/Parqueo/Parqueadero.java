package Parqueo;

import Pagos.MetodoPago;
import Pagos.Pago;
import usuarios.Bicicleta;
import usuarios.Propietario;

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

    public void registrarIngreso(
            Bicicleta bicicleta,
            Propietario propietario) {

        if (!verificarCupo()) {

            System.out.println("No hay cupos disponibles.");

            return;
        }

        int id = registros.size() + 1;

        RegistroParqueo registro =
                new RegistroParqueo(
                        id,
                        bicicleta,
                        propietario
                );

        registro.registrarIngreso();

        registros.add(registro);

        cuposDisponibles--;

        System.out.println();
        System.out.println("Ingreso registrado correctamente.");
        System.out.println("Propietario: "
                + propietario.obtenerNombre());
        System.out.println("Cedula: "
                + propietario.obtenerCedula());
        System.out.println("Bicicleta: "
                + bicicleta.obtenerSerial());
        System.out.println("Color: "
                + bicicleta.obtenerColor());

        System.out.println(
                "Cupos disponibles: "
                        + cuposDisponibles
        );

    }

    public boolean verificarPropietario(String cedula) {

        for (RegistroParqueo registro : registros) {

            if (registro.estaActivo()) {

                Propietario propietario =
                        registro.obtenerPropietario();

                if (propietario.verificarIdentidad(cedula)) {

                    return true;
                }
            }
        }

        return false;
    }

    public void registrarSalida(
            String cedula,
            MetodoPago metodo) {

        for (RegistroParqueo registro : registros) {

            if (registro.estaActivo()) {

                Propietario propietario =
                        registro.obtenerPropietario();

                if (propietario.verificarIdentidad(cedula)) {

                    registro.registrarSalida();

                    int minutos =
                            registro.calcularMinutos();

                    double valor =
                            registro.calcularValor();

                    Pago pago =
                            new Pago(
                                    registro.obtenerIdRegistro(),
                                    valor,
                                    metodo
                            );

                    pago.realizarPago(valor);

                    registro.establecerPago(pago);

                    if (pago.verificarPago()) {

                        liberarCupo();

                        System.out.println();
                        System.out.println(
                                "----- SALIDA REGISTRADA -----"
                        );

                        System.out.println(
                                "Propietario: "
                                        + propietario.obtenerNombre()
                        );

                        System.out.println(
                                "Bicicleta: "
                                        + registro
                                        .obtenerBicicleta()
                                        .obtenerSerial()
                        );

                        System.out.println(
                                "Tiempo: "
                                        + minutos
                                        + " minutos"
                        );

                        System.out.println(
                                "Valor: $"
                                        + valor
                        );

                        System.out.println(
                                "Metodo de pago: "
                                        + metodo
                        );

                        System.out.println(
                                "Cupo liberado."
                        );

                        System.out.println(
                                "Cupos disponibles: "
                                        + cuposDisponibles
                        );

                    }

                    return;
                }
            }
        }

        System.out.println(
                "No se encontro el propietario."
        );
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
