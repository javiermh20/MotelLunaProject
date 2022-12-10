package Datos;

import domain.Vivienda;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViviendaDAO {

    private static final String SQL_SELECT = "SELECT id_vivienda, id_departamento, id_inquilino "
            + " FROM vivienda";

    private static final String SQL_SELECT_BY_ID = "SELECT id_vivienda, id_departamento, id_inquilino "
            + " FROM vivienda WHERE id_vivienda = ?";

    private static final String SQL_INSERT = "INSERT INTO vivienda(id_departamento, id_inquilino) "
            + " VALUES(?, ?)";

    private static final String SQL_UPDATE = "UPDATE vivienda "
            + " SET id_departamento=?, id_inquilino=? WHERE id_vivienda=?";

    private static final String SQL_DELETE = "DELETE FROM vivienda WHERE id_vivienda = ?";

    public List<Vivienda> listar() throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vivienda vivienda = null;
        List<Vivienda> viviendas = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idVivienda = rs.getInt("id_vivienda");
                int idDepartamento = rs.getInt("id_departamento");
                int idInquilino = rs.getInt("id_inquilino");

                vivienda = new Vivienda(idVivienda, idDepartamento, idInquilino);
                viviendas.add(vivienda);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViviendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return viviendas;
    }

    public Vivienda encontrar(Vivienda vivienda) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, vivienda.getIdVivienda());
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos en el primer registro devuelto

            int idDepartamento = rs.getInt("id_departamento");
            int idInquilino = rs.getInt("id_inquilino");

            vivienda.setIdDepartamento(idDepartamento);
            vivienda.setIdInquilino(idInquilino);
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViviendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return vivienda;
    }

    public int insertar(Vivienda vivienda) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, vivienda.getIdDepartamento());
            stmt.setInt(2, vivienda.getIdInquilino());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViviendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Vivienda vivienda) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, vivienda.getIdDepartamento());
            stmt.setInt(2, vivienda.getIdInquilino());
            stmt.setInt(3, vivienda.getIdVivienda());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViviendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Vivienda vivienda) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, vivienda.getIdVivienda());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViviendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

}