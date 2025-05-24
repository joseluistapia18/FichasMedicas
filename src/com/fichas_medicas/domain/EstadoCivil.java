/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Representa el estado civil de una persona.
 *
 * @author Gabriel Vincent Fecha:12/11/2024 Hora:11:08AM
 */
@Data
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class EstadoCivil {

    private Integer idEstadoCivil;  // ID del estado civil
    @NonNull
    private String nombreEstadoCivil;  // Nombre del estado civil
    @NonNull
    private String id_usuario;  // ID del usuario relacionado
    @NonNull
    private String estado;  // Estado civil (soltero, casado, etc.)
}
