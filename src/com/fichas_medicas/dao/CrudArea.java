/*
        @author Jose Luis Tapia Fecha:09-11-2024 hora:21:22pm
 */
package com.fichas_medicas.dao;

import com.fichas_medicas.domain.Area;
import com.fichas_medicas.impl.AreaDAO;
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
 * @author Jose Luis Tapia Fecha: 10/11/2024 hora: 17:50pm
 *
 */
public class CrudArea implements AreaDAO {

    private String base = "fichas_medicas_desarrollo";
    private Conexion conexion;

    public CrudArea() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean save(Area obj) {
        var query = "INSERT INTO area (nombre_area, id_usuario, estado) VALUES (?, ?, ?)";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getNombre_area());     // Asigna el nombre del área
            st.setString(2, obj.getId_usuario());         // Asigna el ID del usuario
            st.setString(3, obj.getEstado());         // Asigna el estado ('A' o 'I')
            int rowsAffected = st.executeUpdate();     // Ejecuta la inserción
            return rowsAffected > 0;                   // Retorna true si se insertaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Area obj) {
        var query = "UPDATE area SET nombre_area = ?, id_usuario = ?, estado = ? WHERE id_area = ?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getNombre_area());     // Asigna el nombre del área
            st.setString(2, obj.getId_usuario());         // Asigna el ID del usuario
            st.setString(3, obj.getEstado());          // Asigna el estado ('A' o 'I')
            st.setInt(4, obj.getId_area());            // Asigna el ID del área para actualizar
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Integer idArea) {
        var query = "UPDATE area SET  estado = ? WHERE id_area = ?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, "I");          // Asigna el estado ('A' o 'I')
            st.setInt(2, idArea);
            // Asigna el ID del área para actualizar
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Area getOne(Integer idArea) {
        Area area = null;
        var query = "SELECT * FROM area WHERE id_area = ? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setInt(1, idArea);                      // Asigna el idArea al parámetro de la consulta
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {                       // Si se encuentra un resultado
                    area = new Area(
                            rs.getInt("id_area"),
                            rs.getString("nombre_area"),
                            rs.getString("id_usuario"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return area;
    }

    @Override
    public Integer getId(String name) {
        var query = "SELECT id_area FROM area WHERE nombre_area = ? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, name);                     // Asigna el nombre del área a buscar
            ResultSet rs = st.executeQuery();          // Ejecuta la consulta
            if (rs.next()) {                           // Verifica si hay resultados
                return rs.getInt("id_area");           // Retorna el id_area encontrado
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Area> getAll() {
        List<Area> datos = new ArrayList<>();
        var query = "select * from area where estado='A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Area area = new Area(rs.getInt("id_area"), rs.getString("nombre_area"),
                        rs.getString("id_usuario"), rs.getString("estado"));
                datos.add(area);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datos;
    }

    public String cadenaArea(Integer id) {
        var res = "";
        List<Area> lista = getAll();
        for (int i = 0; i < lista.size(); i++) {
            if (id == lista.get(i).getId_area()) {
                res = lista.get(i).getNombre_area();
                break;
            }
        }
        return res;
    }
}
