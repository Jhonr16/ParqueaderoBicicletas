package main;

import Parqueo.Parqueadero;
import Pagos.MetodoPago;
import usuarios.Bicicleta;
import usuarios.Propietario;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Parqueadero parqueadero = new Parqueadero();

        int opcion;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("     PARQUEADERO DE BICICLETAS");
            System.out.println("======================================");
            System.out.println("1. Registrar ingreso");
            System.out.println("2. Registrar salida");
            System.out.println("3. Ver cupos disponibles");
            System.out.println("4. Salir");
            System.out.println("======================================");
            System.out.print("Seleccione una opcion: ");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:

                    System.out.println();
                    System.out.println("----- REGISTRAR INGRESO -----");

                    System.out.print("Ingrese la cedula del propietario: ");
                    String cedula = teclado.nextLine();

                    System.out.print("Ingrese el nombre del propietario: ");
                    String nombre = teclado.nextLine();

                    System.out.print("Ingrese el serial de la bicicleta: ");
                    String serial = teclado.nextLine();

                    System.out.print("Ingrese el color de la bicicleta: ");
                    String color = teclado.nextLine();

                    if (parqueadero.verificarCupo()) {

                        Propietario propietario =
                                new Propietario(cedula, nombre);

                        Bicicleta bicicleta =
                                new Bicicleta(serial, color);

                        propietario.registrarBicicleta(bicicleta);

                        parqueadero.registrarIngreso(
                                bicicleta,
                                propietario
                        );

                    } else {

                        System.out.println();
                        System.out.println(
                                "No hay cupos disponibles."
                        );
                    }

                    break;

                case 2:

                    System.out.println();
                    System.out.println("----- REGISTRAR SALIDA -----");

                    System.out.print(
                            "Ingrese la cedula del propietario: "
                    );

                    String cedulaSalida = teclado.nextLine();

                    if (!parqueadero.verificarPropietario(cedulaSalida)) {

                        System.out.println();
                        System.out.println(
                                "No se encontro una bicicleta "
                                        + "registrada con esa cedula."
                        );

                        break;
                    }

                    System.out.println();
                    System.out.println("Seleccione el metodo de pago:");
                    System.out.println("1. Efectivo");
                    System.out.println("2. Tarjeta");
                    System.out.println("3. Transferencia");
                    System.out.print("Seleccione una opcion: ");

                    int opcionPago = teclado.nextInt();
                    teclado.nextLine();

                    MetodoPago metodo;

                    if (opcionPago == 1) {

                        metodo = MetodoPago.EFECTIVO;

                    } else if (opcionPago == 2) {

                        metodo = MetodoPago.TARJETA;

                    } else if (opcionPago == 3) {

                        metodo = MetodoPago.TRANSFERENCIA;

                    } else {

                        System.out.println();
                        System.out.println(
                                "Metodo de pago no valido."
                        );

                        break;
                    }

                    parqueadero.registrarSalida(
                            cedulaSalida,
                            metodo
                    );

                    break;

                case 3:

                    System.out.println();
                    System.out.println("----- CUPOS DEL PARQUEADERO -----");

                    System.out.println(
                            "Cupos disponibles: "
                                    + parqueadero.consultarCuposDisponibles()
                    );

                    System.out.println(
                            "Capacidad total: "
                                    + parqueadero.obtenerCapacidad()
                    );

                    break;

                case 4:

                    System.out.println();
                    System.out.println(
                            "Gracias por utilizar el parqueadero."
                    );

                    break;

                default:

                    System.out.println();
                    System.out.println(
                            "Opcion no valida. Intente nuevamente."
                    );
            }

        } while (opcion != 4);

        teclado.close();
    }
}