/*
 José Luis Romero Rodriguez PRUEBA Hora 23:44 26/11/2023
 */
package com.fichas_medicas.domain;

import java.sql.Date;
import java.sql.Time;
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
public class ParteDiario {

  private Integer id_partediario;
    @NonNull
    private Date fecha_registro;
    @NonNull
    private Time hora_entrada;
    @NonNull
    private String id_persona;//cedula from persona
    @NonNull
    private String diagnostico;
    @NonNull
    private String tratamiento;
    @NonNull
    private String observacion;
    @NonNull
    private String permisos;
    @NonNull
    private Time hora_salida;
    @NonNull
    private String usuario;
    @NonNull
    private String estado;


}
