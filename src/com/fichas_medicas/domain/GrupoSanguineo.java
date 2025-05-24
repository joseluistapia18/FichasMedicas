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
 *
 * @author Hector Drouet fecha: 12/11/2024 Hora: 10:02am
 */
@Data
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class GrupoSanguineo {
    private Integer id_grupo_sanguineo;
    @NonNull
    private String nombre;
    @NonNull
    private String id_usuario;
    @NonNull
    private String estado;

}
