package com.mycompany.canchapro3.clases;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Reservas {
    private String codigoReserva;
    private String codigoCliente;
    private String codigoCancha;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private double total;
    private String estado; // PENDIENTE, CONFIRMADA, EN_CURSO, FINALIZADA, CANCELADA

    public Reservas(String codigoReserva, String codigoCliente, String codigoCancha, String fecha, String horaInicio, String horaFin, double total, String estado) {
        this.codigoReserva = codigoReserva;
        this.codigoCliente = codigoCliente;
        this.codigoCancha = codigoCancha;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.total = total;
        this.estado = estado;
    }

    public String aTextoCSV() {
        return codigoReserva + "," + codigoCliente + "," + codigoCancha + "," + fecha + "," + horaInicio + "," + horaFin + "," + total + "," + estado;
    }

    public void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("reservas.csv", true))) {
            pw.println(this.aTextoCSV());
        } catch (IOException e) {
            System.out.println("Error al guardar la reserva: " + e.getMessage());
        }
    }

    public String getCodigoReserva() { return codigoReserva; }
    public String getFecha() { return fecha; }
    public String getHoraInicio() { return horaInicio; }
    public String getHoraFin() { return horaFin; }
}