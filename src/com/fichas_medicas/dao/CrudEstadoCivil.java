/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.dao;

import com.fichas_medicas.domain.Area;
import com.fichas_medicas.domain.EstadoCivil;
import com.fichas_medicas.impl.EstadoCivilDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class CrudEstadoCivil implements EstadoCivilDAO {

    private String base = "fichas_medicas_desarrollo";
    private Conexion conexion;

    public CrudEstadoCivil() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean save(EstadoCivil obj) {
        var query = "INSERT INTO estado_civil (nombre_estado_civil, id_usuario, estado) VALUES (?, ?, ?)";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getNombreEstadoCivil());
            st.setString(2, obj.getId_usuario());         // Asigna el ID del usuario
            st.setString(3, obj.getEstado());         // Asigna el estado ('A' o 'I')
            int rowsAffected = st.executeUpdate();     // Ejecuta la inserción
            return rowsAffected > 0;                   // Retorna true si se insertaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudEstadoCivil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(EstadoCivil obj) {
        var query = "UPDATE estado_civil SET nombre_estado_civil = ? WHERE id_estado_civil = ?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getNombreEstadoCivil());     // Asigna el nombre del área
            st.setInt(4, obj.getIdEstadoCivil());            // Asigna el ID del área para actualizar
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudEstadoCivil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Integer idEstadoCivil) {
        var query = "UPDATE estado_civil SET  estado = ? WHERE id_estado_civil = ?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, "I");          // Asigna el estado ('A' o 'I')
            st.setInt(2, idEstadoCivil);
            // Asigna el ID del área para actualizar
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudEstadoCivil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public EstadoCivil getOne(Integer idEstadoCivil) {
        EstadoCivil obj = null;
        var query = "SELECT * FROM estado_civil WHERE id_estado_civil = ? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setInt(1, idEstadoCivil);                      // Asigna el idArea al parámetro de la consulta
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {                       // Si se encuentra un resultado
                    obj = new EstadoCivil(
                            rs.getInt("id_estado_civil"),
                            rs.getString("nombre_estado_civil"),
                            rs.getString("id_usuario"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudEstadoCivil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public Integer getId(String name) {
        var query = "SELECT id_estado_civil FROM estado_civil WHERE nombre_estado_civil = ? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, name);                     // Asigna el nombre del área a buscar
            ResultSet rs = st.executeQuery();          // Ejecuta la consulta
            if (rs.next()) {                           // Verifica si hay resultados
                return rs.getInt("id_estado_civil");           // Retorna el id_area encontrado
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudEstadoCivil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<EstadoCivil> getAll() {
        List<EstadoCivil> datos = new ArrayList<>();
        var query = "select * from estado_civil where estado='A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                var obj = new EstadoCivil(
                        rs.getInt("id_estado_civil"),
                        rs.getString("nombre_estado_civil"),
                        rs.getString("id_usuario"),
                        rs.getString("estado")
                );
                datos.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datos;
    }

    public String cadenaEstadoCivil(Integer id) {
        var res = "";
        List<EstadoCivil> lista = getAll();
        for (int i = 0; i < lista.size(); i++) {
            if (id == lista.get(i).getIdEstadoCivil()) {
                res = lista.get(i).getNombreEstadoCivil();
                break;
            }
        }
        return res;
    }

}
