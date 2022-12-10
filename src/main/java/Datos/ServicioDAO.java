package Datos;

import domain.Servicio;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioDAO {
    private static final String SQL_SELECT = "SELECT id_servicio, id_empleado, id_departamentos, servicio, status "
            + " FROM servicio";

    private static final String SQL_SELECT_BY_ID = "SELECT id_servicio, id_empleado, id_departamentos, servicio, status "
            + " FROM servicio WHERE id_servicio = ?";

    private static final String SQL_INSERT = "INSERT INTO cliente(id_empleado, id_departamentos, servicio, status) "
            + " VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE servicio "
            + " SET id_empleado=?, id_departamentos=?, servicio=?, status=? WHERE id_servicio=?";

    private static final String SQL_DELETE = "DELETE FROM servicio WHERE id_servicio = ?";

    public List<Servicio> listar() throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Servicio servicio = null;
        List<Servicio> servicios = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idServicio = rs.getInt("id_servicio");
                int idEmpleado = rs.getInt("id_empleado");
                int idDepartamento = rs.getInt("id_departamentos");
                String servicioa = rs.getString("servicio");
                String status = rs.getString("status");

                servicio = new Servicio(idServicio, idEmpleado, idDepartamento, servicioa, status);
                servicios.add(servicio);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return servicios;
    }

    public Servicio encontrar(Servicio servicio) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, servicio.getIdServicio());
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos en el primer registro devuelto

            int idEmpleado = rs.getInt("id_empleado");
            int idDepartamentos = rs.getInt("id_departamentos");
            String servicioa = rs.getString("servicio");
            String status = rs.getString("status");
            
            servicio.setIdEmpleado(idEmpleado);
            servicio.setIdDepartamentos(idDepartamentos);
            servicio.setServicio(servicioa);
            servicio.setStatus(status);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return servicio;
    }

    public int insertar(Servicio servicio) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, servicio.getIdEmpleado());
            stmt.setInt(2, servicio.getIdDepartamentos());
            stmt.setString(3, servicio.getServicio());
            stmt.setString(4, servicio.getStatus());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Servicio servicio) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, servicio.getIdEmpleado());
            stmt.setInt(2, servicio.getIdDepartamentos());
            stmt.setString(3, servicio.getServicio());
            stmt.setString(4, servicio.getStatus());
            stmt.setInt(5, servicio.getIdServicio());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Servicio servicio) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, servicio.getIdServicio());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

}