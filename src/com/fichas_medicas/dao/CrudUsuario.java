/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.dao;

import com.fichas_medicas.domain.Usuario;
import com.fichas_medicas.impl.UsuarioDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author user Laura Pulido 11-11-2024 Hora:17:36pm
 * Actualizacion Jose Luis Tapia  12/11/2024   hora: 13:40pm
 */
public class CrudUsuario implements UsuarioDAO {

    private String base = "fichas_medicas_desarrollo";
    private Conexion conexion;

    public CrudUsuario() {
        this.conexion = new Conexion();
    }

    @Override
    public String save(Usuario obj) {
        String msg = null;
        var sql = "INSERT INTO usuario(username, password, nombre, apellido, correo, id_rol, id_usuario_registro, estado)"
                + "values(?,?,?,?,?,?,?,?)";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(sql)) {
            st.setString(1, obj.getUsuario());
            st.setString(2, obj.getPassword());
            st.setString(3, obj.getNombre());
            st.setString(4, obj.getApellido());
            st.setString(5, obj.getCorreo());
            st.setInt(6, obj.getId_rol());
            st.setString(7, obj.getId_usuario_registro());
            st.setString(8, obj.getEstado());
            st.executeUpdate();
            msg = "Datos guardados...";
        } catch (SQLException ex) {
            msg = "" + ex;
            // Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }

    @Override
    public String update(Usuario obj) {
        String msg = null;
        var query = "UPDATE usuario SET password = ?, nombre = ?, apellido =?, correo  =?, id_rol = ? WHERE username = ?";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getPassword());
            st.setString(2, obj.getNombre());
            st.setString(3, obj.getApellido());
            st.setString(4, obj.getCorreo());
            st.setInt(5, obj.getId_rol());
            st.setString(6, obj.getUsuario());
            st.executeUpdate();
            msg = "Datos guardados...";
        } catch (SQLException ex) {
            msg = "" + ex;
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }

    @Override
    public String delete(String usuario) {
        String msg = null;
        var query = "UPDATE usuario SET  estado = ? WHERE username = ?";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, "I");          // Asigna el estado ('A' o 'I')
            st.setString(2, usuario);
            // Asigna el ID del área para actualizar
            st.executeUpdate();     // Ejecuta la actualización
            st.executeUpdate();
            msg = "Usuario Eliminado...";
        } catch (SQLException ex) {
            msg = "" + ex;
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }

    @Override
    public Usuario getOne(String usuario) {
        Usuario obj = null;
        var query = "SELECT * FROM usuario WHERE username = ? AND estado = 'A'";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, usuario);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    obj = new Usuario(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getInt("id_rol"),
                            rs.getString("id_usuario_registro"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj; 
    }



    @Override
    public List<Usuario> getAll() {
              List<Usuario> datos = new ArrayList<>();
        var query = "select * from usuario where estado='A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                var obj = new Usuario(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("id_rol"),
                        rs.getString("id_usuario_regIstro"),
                        rs.getString("estado")
                );
                datos.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datos;  
    }

    @Override
    public Usuario getLoging(String usuario, String password) {
        Usuario obj = null;
        var query = "SELECT * FROM usuario WHERE username = ? AND password= ? AND estado = 'A'";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, usuario);
            st.setString(2, password);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    obj = new Usuario(
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getInt("id_rol"),
                            rs.getString("id_usuario_registro"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

}
