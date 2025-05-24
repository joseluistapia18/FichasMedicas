/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fichas_medicas.impl;

import com.fichas_medicas.domain.EstadoCivil;
import java.util.List;

/**
 *
 * @author Gabriel Vincent Fecha:12/11/2024 Hora:11:08AM
 */
public interface EstadoCivilDAO {

    boolean save(EstadoCivil obj);

    boolean update(EstadoCivil obj);

    boolean delete(Integer idEstadoCivil);

    EstadoCivil getOne(Integer idEstadoCivil);

    Integer getId(String name);

    List<EstadoCivil> getAll();
}
