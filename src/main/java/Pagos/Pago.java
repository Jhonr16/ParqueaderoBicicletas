package Pagos;

import Interfaces.IPago;
import java.time.LocalDateTime;

public class Pago implements IPago {

    private final int idPago;
    private double valor;
    private LocalDateTime fechaPago;
    private MetodoPago metodo;

    public Pago(int idPago, double valor, MetodoPago metodo) {
        this.idPago = idPago;
        this.valor = valor;
        this.metodo = metodo;
    }

    @Override
    public boolean realizarPago(double valor) {

        if (valor > 0) {
            this.valor = valor;
            this.fechaPago = LocalDateTime.now();

            System.out.println("Pago realizado correctamente.");
            return true;
        }

        System.out.println("El valor del pago no es valido.");
        return false;
    }

    public double calcularValor(int minutos) {
        return minutos * 10;
    }

    @Override
    public boolean verificarPago() {
        return valor > 0 && fechaPago != null;
    }

    public int obtenerIdPago() {
        return idPago;
    }

    public double obtenerValor() {
        return valor;
    }

    public LocalDateTime obtenerFechaPago() {
        return fechaPago;
    }

    public MetodoPago obtenerMetodo() {
        return metodo;
    }

    public void asignarMetodo(MetodoPago metodo) {
        this.metodo = metodo;
    }
}