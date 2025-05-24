/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fichas_medicas.impl;

import com.fichas_medicas.domain.GrupoSanguineo;
import java.util.List;

/**
 *
 * @author Hector Drouet fecha: 12/11/2024 Hora: 10:02am

 */
public interface GrupoSanguineoDAO {
    boolean save(GrupoSanguineo obj);
    
    boolean update(GrupoSanguineo obj);
    
    boolean delete(Integer idGrupoSanguineo);
            
    GrupoSanguineo getOne(Integer idGrupooSanguineo);
    
    Integer getId(String name);
    
    List<GrupoSanguineo> getAll();
}
