/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.dao;

import com.fichas_medicas.domain.Examen;
import java.sql.Statement;
import com.fichas_medicas.impl.ExamenDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;

/**
 *
 * @author user
 */
public class CrudExamen implements ExamenDAO {

    private String base = "fichas_medicas_desarrollo";
    private Conexion conexion;
    private int id_examen = 0;

    public CrudExamen() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean save(Examen obj) {
        boolean msg = false;
        String query = "INSERT INTO examen (id_persona, fecha_registro, frecuencia_cardiaca, sistolica, diastolica, "
                + "saturacion, peso_kg, estatura_cm, temperatura, imc, estado_actual, habitos, estado, id_ficha_medica,condiciones_fisicas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, obj.getIdPersona());
            st.setDate(2, obj.getFechaRegistro());
            st.setInt(3, obj.getFrecuenciaCardiaca());
            st.setInt(4, obj.getSistolica());
            st.setInt(5, obj.getDiastolica());
            st.setInt(6, obj.getSaturacion());
            st.setDouble(7, obj.getPesoKg());
            st.setDouble(8, obj.getEstaturaCm());
            st.setDouble(9, obj.getTemperatura());
            st.setDouble(10, obj.getImc());
            st.setString(11, obj.getEstadoActual());
            st.setString(12, obj.getHabitos());
            st.setString(13, obj.getEstado());
            st.setInt(14, obj.getId_ficha_medica());
            st.setString(15, obj.getCondiciones_fisicas());
            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idExamen = rs.getInt(1);
                        setId_examen(idExamen);
                        obj.setIdExamen(idExamen); // Guarda el ID generado en el objeto
                        msg = true;
                        System.out.println("Examen creado con ID: " + idExamen);
                    }
                }
            } else {
                msg = false;
            }

        } catch (SQLException ex) {
            msg = false;
            Logger.getLogger(CrudExamen.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msg;
    }

    @Override
    public boolean update(Examen obj) {
        String query = "UPDATE examen SET  frecuencia_cardiaca = ?, sistolica = ?, diastolica = ?, "
                + "saturacion = ?, peso_kg = ?, estatura_cm = ?, temperatura = ?, imc = ?, "
                + "estado_actual = ?, habitos = ?,condiciones_fisicas = ? "
                + "WHERE id_examen = ?";

        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setInt(1, obj.getFrecuenciaCardiaca());
            st.setInt(2, obj.getSistolica());
            st.setInt(3, obj.getDiastolica());
            st.setInt(4, obj.getSaturacion());
            st.setDouble(5, obj.getPesoKg());
            st.setDouble(6, obj.getEstaturaCm());
            st.setDouble(7, obj.getTemperatura());
            st.setDouble(8, obj.getImc());
            st.setString(9, obj.getEstadoActual());
            st.setString(10, obj.getHabitos());
            st.setString(11, obj.getCondiciones_fisicas());
            st.setInt(12, obj.getIdExamen()); // ← Este es clave para saber qué examen actualizar

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CrudExamen.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    @Override
    public boolean delete(Integer id_examen) {
        var query = "UPDATE examen SET  estado = ? WHERE id_examen = ?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, "I");          // Asigna el estado ('A' o 'I')
            st.setInt(2, id_examen);
            // Asigna el ID del área para actualizar
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudFichaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public Examen getOne(Integer id_examen) {
        String query = "SELECT * FROM examen WHERE id_examen = ? and estado='A'";
        Examen examen = null;

        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setInt(1, id_examen);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    examen = new Examen(
                            rs.getInt("id_examen"),
                            rs.getString("id_persona"),
                            rs.getInt("id_ficha_medica"),
                            rs.getDate("fecha_registro"),
                            rs.getInt("frecuencia_cardiaca"),
                            rs.getInt("sistolica"),
                            rs.getInt("diastolica"),
                            rs.getInt("saturacion"),
                            rs.getDouble("peso_kg"),
                            rs.getDouble("estatura_cm"),
                            rs.getDouble("temperatura"),
                            rs.getDouble("imc"),
                            rs.getString("estado_actual"),
                            rs.getString("habitos"),
                            rs.getString("condiciones_fisicas"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudExamen.class.getName()).log(Level.SEVERE, null, ex);
        }

        return examen;
    }

    @Override
    public Integer getId(Date fecha, String idPersona) {
        var query = "SELECT id_examen FROM examen WHERE fecha_registro = ? AND id_persona=? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setDate(1, fecha);                     // Asigna el nombre del área a buscar
            st.setString(2, idPersona);
            ResultSet rs = st.executeQuery();          // Ejecuta la consulta
            if (rs.next()) {                           // Verifica si hay resultados
                return rs.getInt("id_ficha_medica");           // Retorna el id_area encontrado
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<Examen> getAll() {
        String query = "SELECT * FROM examen WHERE estado = 'A'";
        List<Examen> lista = new ArrayList<>();

        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Examen examen = new Examen(
                        rs.getInt("id_examen"),
                        rs.getString("id_persona"),
                        rs.getInt("id_ficha_medica"),
                        rs.getDate("fecha_registro"),
                        rs.getInt("frecuencia_cardiaca"),
                        rs.getInt("sistolica"),
                        rs.getInt("diastolica"),
                        rs.getInt("saturacion"),
                        rs.getDouble("peso_kg"),
                        rs.getDouble("estatura_cm"),
                        rs.getDouble("temperatura"),
                        rs.getDouble("imc"),
                        rs.getString("estado_actual"),
                        rs.getString("habitos"),
                        rs.getString("condiciones_fisicas"),
                        rs.getString("estado")
                );
                lista.add(examen);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudExamen.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public int getId_examen() {
        return id_examen;
    }

    public void setId_examen(int id_examen) {
        this.id_examen = id_examen;
    }
    
    
     public Examen getOneByIdFicha(Integer id_ficha) {
        String query = "SELECT * FROM examen WHERE id_ficha_medica = ? and estado='A'";
        Examen examen = null;

        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setInt(1, id_ficha);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    examen = new Examen(
                            rs.getInt("id_examen"),
                            rs.getString("id_persona"),
                            rs.getInt("id_ficha_medica"),
                            rs.getDate("fecha_registro"),
                            rs.getInt("frecuencia_cardiaca"),
                            rs.getInt("sistolica"),
                            rs.getInt("diastolica"),
                            rs.getInt("saturacion"),
                            rs.getDouble("peso_kg"),
                            rs.getDouble("estatura_cm"),
                            rs.getDouble("temperatura"),
                            rs.getDouble("imc"),
                            rs.getString("estado_actual"),
                            rs.getString("habitos"),
                            rs.getString("condiciones_fisicas"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudExamen.class.getName()).log(Level.SEVERE, null, ex);
        }

        return examen;
    }

}
