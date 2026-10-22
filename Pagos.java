package com.mycompany.canchapro3.clases;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Pagos {
    private String codigoPago;
    private String codigoReserva;
    private String fechaHora;
    private double monto;
    private String metodoPago;
    private String observacion;

    public Pagos(String codigoPago, String codigoReserva, String fechaHora, double monto, String metodoPago, String observacion) {
        this.codigoPago = codigoPago;
        this.codigoReserva = codigoReserva;
        this.fechaHora = fechaHora;
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.observacion = observacion;
    }

    public String aTextoCSV() {
        return codigoPago + "," + codigoReserva + "," + fechaHora + "," + monto + "," + metodoPago + "," + observacion;
    }

    public void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("pagos.csv", true))) {
            pw.println(this.aTextoCSV());
        } catch (IOException e) {
            System.out.println("Error al guardar el pago: " + e.getMessage());
        }
    }

    public String getCodigoPago() { return codigoPago; }
    public double getMonto() { return monto; }
}