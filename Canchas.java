package com.mycompany.canchapro3.clases;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Canchas {
    private String codigo;
    private String nombre;
    private String tipo; // Ej: Fútbol 5, Fútbol 7
    private String capacidad;
    private String estado; // DISPONIBLE, MANTENIMIENTO, INACTIVA
    private String horaApertura;
    private String horaCierre;

    public Canchas(String codigo, String nombre, String tipo, String capacidad, String estado, String horaApertura, String horaCierre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.estado = estado;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public String aTextoCSV() {
        return codigo + "," + nombre + "," + tipo + "," + capacidad + "," + estado + "," + horaApertura + "," + horaCierre;
    }

    public void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("canchas.csv", true))) {
            pw.println(this.aTextoCSV());
        } catch (IOException e) {
            System.out.println("Error al guardar la cancha: " + e.getMessage());
        }
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getEstado() { return estado; }
}