/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.domain;

import java.sql.Date;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.NonNull;
//import lombok.ToString;

/**
 * Eduardo Zapata 15/11/24 14:47 * @author user
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString

public class FichaMedica {
    private int id_fichaMedica;
    
    private Date fecha_registro;
    
    private String id_persona;
    
    private String ant_patologicos_per;
    
    private String ant_patologicos_fam;
     
    private String id_usuario;
    
    private String estado;
    
    

    public int getId_fichaMedica() {
        return id_fichaMedica;
    }

    public void setId_fichaMedica(int id_fichaMedica) {
        this.id_fichaMedica = id_fichaMedica;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getAnt_patologicos_per() {
        return ant_patologicos_per;
    }

    public void setAnt_patologicos_per(String ant_patologicos_per) {
        this.ant_patologicos_per = ant_patologicos_per;
    }

    public String getAnt_patologicos_fam() {
        return ant_patologicos_fam;
    }

    public void setAnt_patologicos_fam(String ant_patologicos_fam) {
        this.ant_patologicos_fam = ant_patologicos_fam;
    }


    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    



    public FichaMedica(int id_fichaMedica, Date fecha_registro, String id_persona, String ant_patologicos_per, String ant_patologicos_fam, String id_usuario, String estado) {
        this.id_fichaMedica = id_fichaMedica;
        this.fecha_registro = fecha_registro;
        this.id_persona = id_persona;
        this.ant_patologicos_per = ant_patologicos_per;
        this.ant_patologicos_fam = ant_patologicos_fam;
        this.id_usuario = id_usuario;
        this.estado = estado;
    }

    public FichaMedica(Date fecha_registro, String id_persona, String ant_patologicos_per, String ant_patologicos_fam, String id_usuario, String estado) {
        this.fecha_registro = fecha_registro;
        this.id_persona = id_persona;
        this.ant_patologicos_per = ant_patologicos_per;
        this.ant_patologicos_fam = ant_patologicos_fam;
        this.id_usuario = id_usuario;
        this.estado = estado;
    }


    
    

}
