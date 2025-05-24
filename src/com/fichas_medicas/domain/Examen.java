/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 *
 * @author user
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class Examen {

    private Integer idExamen;
    @NonNull
    private String idPersona;
    @NonNull
    private Integer id_ficha_medica;
    @NonNull
    private Date fechaRegistro;
    @NonNull
    private Integer frecuenciaCardiaca;
    @NonNull
    private Integer sistolica;
    @NonNull
    private Integer diastolica;
    @NonNull
    private Integer saturacion;
    @NonNull
    private Double pesoKg;
    @NonNull
    private Double estaturaCm;
    @NonNull
    private Double temperatura;
    @NonNull
    private Double imc;
    @NonNull
    private String estadoActual;
    @NonNull
    private String habitos;
    @NonNull
    private String condiciones_fisicas;
    @NonNull
    private String estado;

}
