/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fichas_medicas.impl;


import com.fichas_medicas.domain.Correo;
import java.util.List;

/**
 * Robert López 11:39 15/11/2024
 *
 * @author USUARIO
 */
public interface CorreoDAO {

    boolean save(Correo obj);

    boolean update(Correo obj);

    boolean delete(Integer id_correo);

    Correo getOne(Integer id_correo);

    Integer getId(String name);

    List<Correo> getAll();
}
