/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.test;

import com.fichas_medicas.components.FechaComponente;
import com.fichas_medicas.dao.CrudCorreo;
import com.fichas_medicas.dao.CrudFichaMedica;
import com.fichas_medicas.domain.Correo;
import com.fichas_medicas.domain.FichaMedica;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author user
 */
public class Prueba {

    public static void main(String[] args) {
        // pdf();
        prueba5();
        // System.out.println(FechaComponente.FechaSql());
    }

    private static void prueba1() {
        CrudCorreo crud = new CrudCorreo();
        var id_persona = "3243242423";
        List<Correo> lista = crud.getPersonMail(id_persona);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getCorreo());
        }
    }

    private static void prueba2() {
        java.util.Date fechaActual = new java.util.Date();
        SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
        String fechaSQL = formatoSQL.format(fechaActual);
        Date fecha = Date.valueOf(fechaSQL);
    }

    private static void prueba3(String msg) {
        JDialog dialog = new JDialog((Frame) null, "Aviso", false);

        // Configuramos el contenido del diálogo (similar a un mensaje de JOptionPane)
        JLabel messageLabel = new JLabel(msg, SwingConstants.CENTER);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        dialog.getContentPane().add(messageLabel);

        // Ajustamos el tamaño y centramos el diálogo en pantalla
        dialog.setSize(350, 150);
        dialog.setLocationRelativeTo(null);

        // Mostramos el diálogo
        dialog.setVisible(true);

        // Creamos un Timer que se ejecuta después de 4000 milisegundos (4 segundos)
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Cierra el diálogo
            }
        });
        timer.setRepeats(false); // Se ejecuta una sola vez
        timer.start();
    }

    private static void prueba4() {
        CrudFichaMedica crud = new CrudFichaMedica();
        var id_persona = "4543534534";
        List<FichaMedica> lista = crud.getAllTabSummary(id_persona);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getFecha_registro() + " " + lista.get(i).getAnt_patologicos_fam());
        }
    }

    private static void prueba5() {
        LocalTime horaActual = LocalTime.now();

        // Formatear la hora en HH:mm:ss
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada = horaActual.format(formato);

        // Imprimir la hora
        System.out.println("Hora actual: " + horaFormateada);
    }

    private static void pdf() {
        GenerarPDF.test();
    }

}

class GenerarPDF {

    public static void test() {
        try {
            // 1. Crear el documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("parte_diario.pdf"));
            document.open();

            // 2. Obtener los valores de los campos (simulados aquí)
            String cedula = "1234567890";
            String fechaRegistro = "06/03/2025";
            String horaEntrada = "10:41:18";
            String nombres = "Juan";
            String apellidos = "Pérez";
            String area = "Consulta Externa";
            String horaSalida = "12:00:00";
            String permisos = "No";
            String diagnostico = "Gripe";
            String tratamiento = "Paracetamol";
            String observacion = "Paciente estable";

            // 3. Agregar los campos y valores al PDF
            document.add(new Paragraph("Cédula: " + cedula));
            document.add(new Paragraph("Fecha de Registro: " + fechaRegistro));
            document.add(new Paragraph("Hora de Entrada: " + horaEntrada));
            document.add(new Paragraph("Nombres: " + nombres));
            document.add(new Paragraph("Apellidos: " + apellidos));
            document.add(new Paragraph("Área: " + area));
            document.add(new Paragraph("Hora de Salida: " + horaSalida));
            document.add(new Paragraph("Permisos: " + permisos));
            document.add(new Paragraph("Diagnóstico: " + diagnostico));
            document.add(new Paragraph("Tratamiento: " + tratamiento));
            document.add(new Paragraph("Observación: " + observacion));

            // 4. Cerrar el documento
            document.close();

            System.out.println("PDF generado correctamente.");

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
