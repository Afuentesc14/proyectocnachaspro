package com.mycompany.canchapro3;

import com.mycompany.canchapro3.clases.Canchas;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;

public class Motordispon {

    public static boolean hayTraslape(String inicioNuevoStr, String finNuevoStr, 
                                     String inicioExistenteStr, String finExistenteStr) {
        
        LocalTime inicioNuevo = LocalTime.parse(inicioNuevoStr);
        LocalTime finNuevo = LocalTime.parse(finNuevoStr);
        LocalTime inicioExistente = LocalTime.parse(inicioExistenteStr);
        LocalTime finExistente = LocalTime.parse(finExistenteStr);

        return (inicioNuevo.isBefore(finExistente)) && (finNuevo.isAfter(inicioExistente));
    }

    public static Canchas[] buscarCanchasDisponibles(String fechaSolicitada, String horaInicioStr, String horaFinStr, String tipoSolicitado) {
        Canchas[] todasLasCanchas = leerCanchasCSV();
        String[][] todasLasReservas = leerReservasCSV();

        int contadorDisponibles = 0;
        for (int i = 0; i < todasLasCanchas.length; i++) {
            Canchas cancha = todasLasCanchas[i];
            if (cancha == null) continue;

            if (!cancha.getEstado().equalsIgnoreCase("DISPONIBLE")) {
                continue;
            }

            boolean estaOcupada = false;

            for (int j = 0; j < todasLasReservas.length; j++) {
                String[] reserva = todasLasReservas[j];
                if (reserva == null) continue;

                String codigoCanchaReserva = reserva[2];
                String fechaReserva = reserva[3];
                String horaInicioReserva = reserva[4];
                String horaFinReserva = reserva[5];
                String estadoReserva = reserva[7];

                if (codigoCanchaReserva.equalsIgnoreCase(cancha.getCodigo()) && 
                    fechaReserva.equals(fechaSolicitada) && 
                    !estadoReserva.equalsIgnoreCase("CANCELADA")) {
                    
                    if (hayTraslape(horaInicioStr, horaFinStr, horaInicioReserva, horaFinReserva)) {
                        estaOcupada = true;
                        break; 
                    }
                }
            }

            if (!estaOcupada) {
                contadorDisponibles++;
            }
        }

        Canchas[] canchasDisponibles = new Canchas[contadorDisponibles];
        int indexDisp = 0;

        for (int i = 0; i < todasLasCanchas.length; i++) {
            Canchas cancha = todasLasCanchas[i];
            if (cancha == null) continue;

            if (!cancha.getEstado().equalsIgnoreCase("DISPONIBLE")) {
                continue;
            }

            boolean estaOcupada = false;
            for (int j = 0; j < todasLasReservas.length; j++) {
                String[] reserva = todasLasReservas[j];
                if (reserva == null) continue;

                String codigoCanchaReserva = reserva[2];
                String fechaReserva = reserva[3];
                String horaInicioReserva = reserva[4];
                String horaFinReserva = reserva[5];
                String estadoReserva = reserva[7];

                if (codigoCanchaReserva.equalsIgnoreCase(cancha.getCodigo()) && 
                    fechaReserva.equals(fechaSolicitada) && 
                    !estadoReserva.equalsIgnoreCase("CANCELADA")) {
                    
                    if (hayTraslape(horaInicioStr, horaFinStr, horaInicioReserva, horaFinReserva)) {
                        estaOcupada = true;
                        break; 
                    }
                }
            }

            if (!estaOcupada) {
                canchasDisponibles[indexDisp++] = cancha;
            }
        }

        return canchasDisponibles;
    }

    private static Canchas[] leerCanchasCSV() {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("canchas.csv"))) {
            while (br.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            return new Canchas[0];
        }

        Canchas[] listaTemp = new Canchas[count];
        int index = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("canchas.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 7) {
                    listaTemp[index++] = new Canchas(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
                }
            }
        } catch (IOException e) {
        }

        int validCount = 0;
        for (int i = 0; i < listaTemp.length; i++) {
            if (listaTemp[i] != null) validCount++;
        }

        Canchas[] listaFinal = new Canchas[validCount];
        int fIndex = 0;
        for (int i = 0; i < listaTemp.length; i++) {
            if (listaTemp[i] != null) {
                listaFinal[fIndex++] = listaTemp[i];
            }
        }
        return listaFinal;
    }

    private static String[][] leerReservasCSV() {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("reservas.csv"))) {
            while (br.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            return new String[0][0];
        }

        String[][] listaTemp = new String[count][];
        int index = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("reservas.csv"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 8) {
                    listaTemp[index++] = datos;
                }
            }
        } catch (IOException e) {
        }

        int validCount = 0;
        for (int i = 0; i < listaTemp.length; i++) {
            if (listaTemp[i] != null) validCount++;
        }

        String[][] listaFinal = new String[validCount][];
        int fIndex = 0;
        for (int i = 0; i < listaTemp.length; i++) {
            if (listaTemp[i] != null) {
                listaFinal[fIndex++] = listaTemp[i];
            }
        }
        return listaFinal;
    }
}