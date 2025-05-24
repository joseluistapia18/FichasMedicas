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
 * @author USUARIO
 */
@Data
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Correo {

    private Integer id_correo;
    @NonNull
    private String correo;
    @NonNull
    private String id_persona;
    @NonNull
    private String id_usuario;
    @NonNull
    private String estado;

}
