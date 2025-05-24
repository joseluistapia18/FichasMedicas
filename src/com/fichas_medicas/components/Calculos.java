/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.components;

/**
 *
 * @author user
 */
public class Calculos {

    public static Double getImc(Double peso, Double estatura) {
        return peso / (estatura * estatura);
    }

}
