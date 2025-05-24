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

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RequiredArgsConstructor

/** Pablo Garcia 12/11/2024 12:06
 *
 * @author userasdasdasd
 */
public class Roles {

    private Integer id_rol;
    @NonNull
    private String nombre;
    @NonNull
    private String id_usuario;
    @NonNull
    private String estado;
  
   
    }
    
    
