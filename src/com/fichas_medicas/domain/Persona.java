/**//*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Date;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/*Jose Luis Romero Rodriguez 12/11/2024 12:15PM...
 */
@Data
@NoArgsConstructor
@ToString
@RequiredArgsConstructor

public class Persona {

    private String cedula;
    @NonNull
    private String nombre;
    @NonNull
    private String apellidos;
    @NonNull
    private Date fecha_nacimiento;
    @NonNull
    private String lugar_nacimiento;  //cambio
    @NonNull
    private Integer n_hijos;
    @NonNull
    private String direccion;
    @NonNull
    private String telefono;
    @NonNull
    private String telefono_emergencia;
    @NonNull
    private Integer id_grupo_sanguineo;
    @NonNull
    private Integer id_estado_civil;
    @NonNull
    private Integer id_area;
    @NonNull
    private String id_usuario;
    @NonNull
    private String foto;
    @NonNull
    private Date fecha_registro;  // cambio
    @NonNull
    private String estado;

    public Persona(String cedula, String nombre, String apellidos, Date fecha_nacimiento, String lugar_nacimiento, Integer n_hijos, String direccion, String telefono, String telefono_emergencia, Integer id_grupo_sanguineo, Integer id_estado_civil, Integer id_area, String id_usuario, String foto, Date fecha_registro, String estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
        this.lugar_nacimiento = lugar_nacimiento;
        this.n_hijos = n_hijos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.telefono_emergencia = telefono_emergencia;
        this.id_grupo_sanguineo = id_grupo_sanguineo;
        this.id_estado_civil = id_estado_civil;
        this.id_area = id_area;
        this.id_usuario = id_usuario;
        this.foto = foto;
        this.fecha_registro = fecha_registro;
        this.estado = estado;
    }

    
}
