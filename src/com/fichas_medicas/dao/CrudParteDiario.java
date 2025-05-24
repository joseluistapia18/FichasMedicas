package com.fichas_medicas.dao;

import com.fichas_medicas.domain.ParteDiario;
import com.fichas_medicas.impl.ParteDiarioDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrudParteDiario implements ParteDiarioDAO {

    private String base = "fichas_medicas_desarrollo";
    private Conexion conexion;

    public CrudParteDiario() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean save(ParteDiario obj) {
        var sql = "INSERT INTO parte_diario(fecha_registro, hora_entrada, id_persona, diagnostico, tratamiento, observacion, permisos, hora_salida, usuario, estado)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(sql)) {
            st.setDate(1, obj.getFecha_registro());
            st.setTime(2, obj.getHora_entrada());
            st.setString(3, obj.getId_persona());
            st.setString(4, obj.getDiagnostico());
            st.setString(5, obj.getTratamiento());
            st.setString(6, obj.getObservacion());
            st.setString(7, obj.getPermisos());
            st.setTime(8, obj.getHora_salida());
            st.setString(9, obj.getUsuario());
            st.setString(10, obj.getEstado());
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CrudParteDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(ParteDiario obj) {
        var query = "UPDATE parte_diario SET fecha_registro = ?, hora_entrada = ?, id_persona = ?, diagnostico = ?, tratamiento = ?, observacion = ?, permisos = ?, hora_salida = ?, usuario = ?, estado = ? WHERE id_partediario = ?";

        try (Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setDate(1, obj.getFecha_registro());
            st.setTime(2, obj.getHora_entrada());
            st.setString(3, obj.getId_persona());
            st.setString(4, obj.getDiagnostico());
            st.setString(5, obj.getTratamiento());
            st.setString(6, obj.getObservacion());
            st.setString(7, obj.getPermisos());
            st.setTime(8, obj.getHora_salida());
            st.setString(9, obj.getUsuario());
            st.setString(10, obj.getEstado());
            st.setInt(11, obj.getId_partediario());

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CrudParteDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Integer idParte) {
        var query = "UPDATE parte_diario SET estado = ? WHERE id_parte_diario = ?";

        try (Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, "I"); // Marca el estado como inactivo
            st.setInt(2, idParte);

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CrudParteDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ParteDiario getOne(Integer idParte) {
         ParteDiario obj = null;
        var query = "SELECT * FROM parte_diario WHERE id_partediario = ? AND estado = 'A'";

        try (Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setInt(1, idParte);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    obj = new ParteDiario(
                            rs.getInt("id_partediario"),
                            rs.getDate("fecha_registro"),
                            rs.getTime("hora_entrada"),
                            rs.getString("id_persona"),
                            rs.getString("diagnostico"),
                            rs.getString("tratamiento"),
                            rs.getString("observacion"),
                            rs.getString("permisos"),
                            rs.getTime("hora_salida"),
                            rs.getString("usuario"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudParteDiario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return obj;
    }

    @Override
    public Integer getId(String name) {
        var query = "SELECT id_partediario FROM parte_diario WHERE usuario = ? AND estado = 'A'";

        try (Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, name);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_parte_diario");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudParteDiario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public List<ParteDiario> getAll() {
       List<ParteDiario> datos = new ArrayList<>();
        var query = "SELECT * FROM parte_diario WHERE estado = 'A'";

        try (Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                var obj = new ParteDiario(
                        rs.getInt("id_partediario"),
                        rs.getDate("fecha_registro"),
                        rs.getTime("hora_entrada"),
                        rs.getString("id_persona"),
                        rs.getString("diagnostico"),
                        rs.getString("tratamiento"),
                        rs.getString("observacion"),
                        rs.getString("permisos"),
                        rs.getTime("hora_salida"),
                        rs.getString("usuario"),
                        rs.getString("estado")
                );
                datos.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudParteDiario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datos;
    }
}
