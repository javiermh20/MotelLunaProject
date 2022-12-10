package Datos;

import domain.Departamento;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DepartamentoDAO {
    private static final String SQL_SELECT = "SELECT id_departamento, numero_departamento, habitaciones"
            + " FROM departamento";

    private static final String SQL_SELECT_BY_ID = "SELECT id_departamento, numero_departamento, habitaciones"
            + " FROM departamento WHERE id_departamento = ?";

    private static final String SQL_INSERT = "INSERT INTO departamento(numero_departamento, habitaciones) "
            + " VALUES(?, ?)";

    private static final String SQL_UPDATE = "UPDATE departamento "
            + " SET numero_departamento=?, habitaciones=? WHERE id_departamento=?";

    private static final String SQL_DELETE = "DELETE FROM departamento WHERE id_departamento = ?";

    
    public List<Departamento> listar() throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Departamento departamento = null;
        List<Departamento> departamentos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idDepartamento = rs.getInt("id_departamento");
                int numero = rs.getInt("numero_departamento");
                String habitaciones = rs.getString("habitaciones");

                departamento = new Departamento(idDepartamento, numero, habitaciones);
                departamentos.add(departamento);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return departamentos;
    }

    public Departamento encontrar(Departamento departamento) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, departamento.getIdDepartamento());
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos en el primer registro devuelto

            int numero = rs.getInt("numero_departamento");
            String habitaciones = rs.getString("habitaciones");

            departamento.setNumeroDepartamento(numero);
            departamento.setHabitaciones(habitaciones);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return departamento;
    }

    public int insertar(Departamento departamento) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, departamento.getNumeroDepartamento());
            stmt.setString(2, departamento.getHabitaciones());


            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Departamento departamento) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, departamento.getNumeroDepartamento());
            stmt.setString(2, departamento.getHabitaciones());
            stmt.setInt(3, departamento.getIdDepartamento());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Departamento departamento) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, departamento.getIdDepartamento());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DepartamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

}