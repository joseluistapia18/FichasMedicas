package com.fichas_medicas.components;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author JOSE LUIS
 */
public class FechaComponente {

    public static String Fecha() {
        String gui, me;
        Calendar c1 = Calendar.getInstance();
        String dia = Integer.toString(c1.get(Calendar.DATE));
        String mes = Integer.toString(c1.get(Calendar.MONTH) + 1);
        String ano = Integer.toString(c1.get(Calendar.YEAR));
        if (c1.get(Calendar.MONTH) + 1 == 1) {
            mes = "Enero";
        }
        if (c1.get(Calendar.MONTH) + 1 == 2) {
            mes = "Febrero";
        }
        if (c1.get(Calendar.MONTH) + 1 == 3) {
            mes = "Marzo";
        }
        if (c1.get(Calendar.MONTH) + 1 == 4) {
            mes = "Abril";
        }
        if (c1.get(Calendar.MONTH) + 1 == 5) {
            mes = "Mayo";
        }
        if (c1.get(Calendar.MONTH) + 1 == 6) {
            mes = "Junio";
        }
        if (c1.get(Calendar.MONTH) + 1 == 7) {
            mes = "Julio";
        }
        if (c1.get(Calendar.MONTH) + 1 == 8) {
            mes = "Agosto";
        }
        if (c1.get(Calendar.MONTH) + 1 == 9) {
            mes = "Septiembre";
        }
        if (c1.get(Calendar.MONTH) + 1 == 10) {
            mes = "Octubre";
        }
        if (c1.get(Calendar.MONTH) + 1 == 11) {
            mes = "Noviembre";
        }
        if (c1.get(Calendar.MONTH) + 1 == 12) {
            mes = "Diciembre";
        }

        gui = (dia + "/" + mes + "/" + ano);
        return gui;
    }

    public static int Anio() {
        Calendar c1 = Calendar.getInstance();
        return c1.get(Calendar.YEAR);
    }

    public static int Mes() {
        Calendar c1 = Calendar.getInstance();
        return c1.get(Calendar.MONTH) + 1;
    }

    public static int Dia() {
        Calendar c1 = Calendar.getInstance();
        return c1.get(Calendar.DATE);
    }

    public static Date FechaSql() {
        String dias = "" + Dia(), mes = "" + Mes();
        if (dias.length() == 1) {
            dias = "0" + dias;
        }
        if (mes.length() == 1) {
            mes = "0" + mes;
        }
        return java.sql.Date.valueOf("" + Anio() + "-" + mes + "-" + dias);
    }

    public static String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss");
        return formateador.format(ahora);
    }

    public static int comparacionFecha(String fecha1, String fecha2) {
        int val = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse(fecha1);
            Date date2 = sdf.parse(fecha2);
            val = date1.compareTo(date2);
            //  fecha 1 mayor a 2 = -1   // fecha 2 es mayor a fecha 1 = 1 // fecha 1 es igual es fecha 2 = 0
        } catch (ParseException ex) {
            Logger.getLogger(FechaComponente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;
    }

    public static String dividirFecha(String fecha) {
        String cad[] = fecha.split(" ");

        return cad[5] + "-" + intMes(cad[1]) + "-" + cad[2];

    }

    public static int intMes(String mes) {
        mes = mes.toUpperCase();
        int gui = 0;

        if ("JAN".equals(mes)) {
            gui = 01;
        }
        if ("FEB".equals(mes)) {
            gui = 02;
        }
        if ("MAR".equals(mes)) {
            gui = 03;
        }
        if ("APR".equals(mes)) {
            gui = 04;
        }
        if ("MAY".equals(mes)) {
            gui = 05;
        }
        if ("JUN".equals(mes)) {
            gui = 06;
        }
        if ("JUL".equals(mes)) {
            gui = 07;
        }
        if ("AUG".equals(mes)) {
            gui = 8;
        }

        if ("SEP".equals(mes)) {
            gui = 9;
        }
        if ("OCT".equals(mes)) {
            gui = 10;
        }
        if ("NOV".equals(mes)) {
            gui = 11;
        }
        if ("DEC".equals(mes)) {
            gui = 12;
        }

        return gui;
    }

    public static int getIntMesESP(String mes) {
        mes = mes.toUpperCase();
        int gui = 0;

        if ("ENERO".equals(mes)) {
            gui = 01;
        }
        if ("FEBRERO".equals(mes)) {
            gui = 02;
        }
        if ("MARZO".equals(mes)) {
            gui = 03;
        }
        if ("ABRIL".equals(mes)) {
            gui = 04;
        }
        if ("MAYO".equals(mes)) {
            gui = 05;
        }
        if ("JUNIO".equals(mes)) {
            gui = 06;
        }
        if ("JULIO".equals(mes)) {
            gui = 07;
        }
        if ("AGOSTO".equals(mes)) {
            gui = 8;
        }

        if ("SEPTIEMBRE".equals(mes)) {
            gui = 9;
        }
        if ("OCTUBRE".equals(mes)) {
            gui = 10;
        }
        if ("NOVIEMBRE".equals(mes)) {
            gui = 11;
        }
        if ("DICIEMBRE".equals(mes)) {
            gui = 12;
        }

        return gui;
    }

    public static String getStringMes(int mes) {

        String gui = null;

        if (mes == 1) {
            gui = "Enero".toUpperCase();
        }
        if (mes == 2) {
            gui = "Febrero".toUpperCase();
        }
        if (mes == 3) {
            gui = "Marzo".toUpperCase();
        }
        if (mes == 4) {
            gui = "Abril".toUpperCase();
        }
        if (mes == 5) {
            gui = "Mayo".toUpperCase();
        }
        if (mes == 6) {
            gui = "Junio".toUpperCase();
        }
        if (mes == 7) {
            gui = "Julio".toUpperCase();
        }
        if (mes == 8) {
            gui = "Agosto".toUpperCase();
        }
        if (mes == 9) {
            gui = "Septiembre".toUpperCase();
        }
        if (mes == 10) {
            gui = "Octubre".toUpperCase();
        }
        if (mes == 11) {
            gui = "Noviembre".toUpperCase();
        }
        if (mes == 12) {
            gui = "Diciembre".toUpperCase();
        }

        return gui;
    }

    public static String getStringFecha(Date fecha) {
        if (fecha != null) {
            String cad[] = fecha.toString().split("-");
            return cad[2] + "/" + getStringMes(Integer.parseInt(cad[1])) + "/" + cad[0];
        } else {
            return "";
        }

    }

    public static int comparacionFecha(Date fecha1, Date fecha2) {
        int val = 0;
        try {
            val = fecha1.compareTo(fecha2);
        } catch (Exception e) {

        }
        //  fecha 1 mayor a 2 = -1   // fecha 2 es mayor a fecha 1 = 1 // fecha 1 es igual es fecha 2 = 0
        return val;
    }

    public static Date getDateFecha(String fecha) {
        java.sql.Date f = null;
        if (fecha != null || !"".equals(fecha)) {
            String cad[] = fecha.split("/");
            try {
                f = java.sql.Date.valueOf(cad[2] + "-" + getIntMesESP(cad[1]) + "-" + cad[0]);
            } catch (Exception ex) {
                System.out.println("Error " + ex);
                Logger.getLogger(FechaComponente.class.getName()).log(Level.SEVERE, null, ex);
            }
            return f;
        } else {
            return null;
        }

    }
public static Date getDateFecha1(String fecha) {
    java.sql.Date f = null;

    // Validar que la fecha no sea nula ni vacía
    if (fecha != null && !fecha.isEmpty()) {
        String[] cad = fecha.split("-");
        try {
            // Construir la fecha en formato yyyy-MM-dd
            f = java.sql.Date.valueOf(cad[2] + "-" + getIntMesESP(cad[1]) + "-" + cad[0]);
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(FechaComponente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    return f;
}

 public static String devuelvePathMsql(String cadena) {
        String c1 = "";
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == '\\') {
                c1 = c1 + "\\\\";
            } else {
                c1 = c1 + cadena.charAt(i);
            }
        }
        return c1;
    }
 
     private static String getSystemTime() {
        LocalTime horaActual = LocalTime.now();
        // Formatear la hora en HH:mm:ss
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada = horaActual.format(formato);
        // Imprimir la hora
        return horaFormateada;
      //  System.out.println("Hora actual: " + horaFormateada);
    }

}
