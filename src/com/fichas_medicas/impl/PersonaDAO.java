/*
  José Luis Romero Rodriguez PRUEBA Hora 23:44 26/11/2023

 */
package com.fichas_medicas.impl;

import com.fichas_medicas.domain.Persona;
import java.util.List;

/*Jose Luis Romero Rodriguez 12/11/2024 12:15PM.
*/

public interface PersonaDAO {

    String save(Persona obj);

    boolean update(Persona obj);

    boolean delete(String cedula);

    Persona getOne(String cedula);

    List<Persona> getAll();
}
