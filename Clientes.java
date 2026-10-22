package com.mycompany.canchapro3.clases;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Clientes {
    private String codigo;
    private String nombre;
    private String telefono;
    private String correo;
    private String fechaRegistro;

    public Clientes(String codigo, String nombre, String telefono, String correo, String fechaRegistro) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaRegistro = fechaRegistro;
    }

    public String aTextoCSV() {
        return codigo + "," + nombre + "," + telefono + "," + correo + "," + fechaRegistro;
    }

    public void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("clientes.csv", true))) {
            pw.println(this.aTextoCSV());
        } catch (IOException e) {
            System.out.println("Error al guardar el cliente: " + e.getMessage());
        }
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
}