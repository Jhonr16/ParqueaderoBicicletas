package Parqueo;

import usuarios.Bicicleta;
import Pagos.Pago;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Objects;

public class RegistroParqueo {

    private final int idRegistro;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;
    private boolean activo;
    private final Bicicleta bicicleta;
    private Pago pago;

    public RegistroParqueo(int idRegistro, Bicicleta bicicleta) {
        this.idRegistro = idRegistro;
        this.bicicleta = bicicleta;
        this.activo = false;
    }

    public void registrarIngreso() {
        fechaIngreso = LocalDateTime.now();
        activo = true;
    }

    public void registrarSalida() {
        fechaSalida = LocalDateTime.now();
        activo = false;
    }

    public int calcularMinutos() {

        if (fechaIngreso == null) {
            return 0;
        }

        LocalDateTime salida;

        salida = Objects.requireNonNullElseGet(fechaSalida, LocalDateTime::now);

        long minutos = Duration.between(fechaIngreso, salida).toMinutes();

        return (int) minutos;
    }

    public double calcularValor() {
        return calcularMinutos() * 10;
    }

    public boolean estaActivo() {
        return activo;
    }

    public int obtenerIdRegistro() {
        return idRegistro;
    }

    public Bicicleta obtenerBicicleta() {
        return bicicleta;
    }

    public LocalDateTime obtenerFechaIngreso() {
        return fechaIngreso;
    }

    public LocalDateTime obtenerFechaSalida() {
        return fechaSalida;
    }

    public Pago obtenerPago() {
        return pago;
    }

    public void establecerPago(Pago pago) {
        this.pago = pago;
    }
}