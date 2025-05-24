/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fichas_medicas.impl;


import com.fichas_medicas.domain.Roles;
import java.util.List;

/** Pablo Garcia 12/11/24  12:33
 *
 * @author user
 */
public interface RolesDAO {
   boolean save(Roles obj);
   
   boolean update(Roles obj);
   
   boolean delete(Integer id_rol);
           
   Roles getOne(Integer id_rol);
   
   Integer getId(String name);
   
   List<Roles> getAll();
   
}
