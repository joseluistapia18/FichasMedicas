/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.components;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Usuario Prueba
 */
public class Dialogo {

    public static void Mensaje(String msg,int tiempo) {
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
        Timer timer = new Timer((tiempo*1000), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Cierra el diálogo
            }
        });
        timer.setRepeats(false); // Se ejecuta una sola vez
        timer.start();
    }
}
