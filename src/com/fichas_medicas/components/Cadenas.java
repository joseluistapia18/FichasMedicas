/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario hola
 */
public class Cadenas {
    
     public static String validateString(String input) {
  // Verificar si la cadena es nula o vacía
        if (input == null || input.isEmpty()) {
            return null; // Devolver null para indicar que la cadena no es válida
        }

        // Eliminar espacios en blanco al inicio y al final de la cadena
        input = input.trim();

        // Verificar que la cadena tenga al menos 4 caracteres después de eliminar los espacios
        if (input.length() < 6) {
            return null; // Devolver null para indicar que la cadena no es válida
        }

        return input; // Devolver la cadena válida
    }
    
     public static String validateEmail(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        // Eliminar espacios al inicio
        String trimmedInput = input.stripLeading();

        // Expresión regular para validar correo electrónico
        String emailRegex = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(trimmedInput);

        if (matcher.matches()) {
            return "Correo válido: " + trimmedInput;
        } else {
            return null;
        }
    }
    
}
