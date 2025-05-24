/* Héctor Drouet
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.dao;

import com.fichas_medicas.domain.GrupoSanguineo;
import com.fichas_medicas.impl.GrupoSanguineoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudGrupoSanguineo implements GrupoSanguineoDAO {

    private String base = "fichas_medicas_desarrollo";
    private Conexion conexion;

    public CrudGrupoSanguineo() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean save(GrupoSanguineo obj) {
        var query = "INSERT INTO grupo_sanguineo(nombre_grupo_sanguineo, id_usuario, estado) VALUES (?, ?, ?)";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getNombre());     // Asigna el nombre del área
            st.setString(2, obj.getId_usuario());         // Asigna el ID del usuario
            st.setString(3, obj.getEstado());         // Asigna el estado ('A' o 'I')
            int rowsAffected = st.executeUpdate();     // Ejecuta la inserción
            return rowsAffected > 0;                   // Retorna true si se insertaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudGrupoSanguineo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(GrupoSanguineo obj) {
        var query = "UPDATE grupo_sanguineo SET nombre_grupo_sanguineo = ?, id_usuario = ?, estado = ? WHERE id_grupo_sanguineo = ?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getNombre());     // Asigna el nombre del área
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudGrupoSanguineo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Integer idGrupoSanguineo) {
        var query = "UPDATE grupo_sanguineo SET  estado = ? WHERE id_grupo_sanguineo = ?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, "I");          // Asigna el estado ('A' o 'I')
            st.setInt(2, idGrupoSanguineo);
            // Asigna el ID del área para actualizar
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudGrupoSanguineo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public GrupoSanguineo getOne(Integer idGrupooSanguineo) {
        GrupoSanguineo obj = null;
        var query = "SELECT * FROM grupo_sanguineo WHERE id_grupo_sanguineo = ? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setInt(1, idGrupooSanguineo);                      // Asigna el idArea al parámetro de la consulta
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {                       // Si se encuentra un resultado
                    obj = new GrupoSanguineo(
                            rs.getInt("id_grupo_sanguineo"),
                            rs.getString("id_grupo_sanguineo"),
                            rs.getString("id_usuario"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudGrupoSanguineo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public Integer getId(String name) {
        var query = "SELECT id_grupo_sanguineo FROM grupo_sanguineo WHERE nombre_grupo_sanguineo = ? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, name);                     // Asigna el nombre del área a buscar
            ResultSet rs = st.executeQuery();          // Ejecuta la consulta
            if (rs.next()) {                           // Verifica si hay resultados
                return rs.getInt("id_area");           // Retorna el id_area encontrado
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudGrupoSanguineo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<GrupoSanguineo> getAll() {
        List<GrupoSanguineo> datos = new ArrayList<>();
        var query = "select * from grupo_sanguineo where estado='A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                GrupoSanguineo gruposanguineo = new GrupoSanguineo(rs.getInt("id_grupo_sanguineo"), rs.getString("nombre_grupo_sanguineo"),
                        rs.getString("id_usuario"), rs.getString("estado"));
                datos.add(gruposanguineo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudGrupoSanguineo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datos;
    }
    
    public String cadenaGrupoSanguineo(Integer id) {
        var res = "";
        List<GrupoSanguineo> lista = getAll();
        for (int i = 0; i < lista.size(); i++) {
            if (id == lista.get(i).getId_grupo_sanguineo()) {
                res = lista.get(i).getNombre();
                break;
            }
        }
        return res;
    }
}
