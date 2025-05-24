/*
  José Luis Romero Rodriguez PRUEBA Hora 23:44 26/11/2023

 */
package com.fichas_medicas.impl;


import com.fichas_medicas.domain.ParteDiario;
import java.util.List;

/**
 *
 * @author Jose Luis Tapia fecha:19/11/2024 hora: 13:46pm
 */
public interface ParteDiarioDAO {

    boolean save(ParteDiario obj);

    boolean update(ParteDiario obj);

    boolean delete(Integer idParte);

    ParteDiario getOne(Integer idParte);

    Integer getId(String name);

    List<ParteDiario> getAll();
}
