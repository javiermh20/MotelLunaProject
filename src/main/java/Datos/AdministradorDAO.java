package Datos;

import domain.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministradorDAO {
    private static final String SQL_SELECT = "SELECT id_administrador, nombre, usuario, contrasena"
            + " FROM administrador";

    private static final String SQL_SELECT_BY_ID = "SELECT id_administrador, nombre, usuario, contrasena"
            + " FROM administrador WHERE id_administrador = ?";

    private static final String SQL_INSERT = "INSERT INTO administrador(nombre, usuario, contrasena) "
            + " VALUES(?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE administrador "
            + " SET nombre=?, usuario=?, contrasena=? WHERE id_administrador=?";

    private static final String SQL_DELETE = "DELETE FROM administrador WHERE id_administrador = ?";

    public List<Administrador> listar() throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Administrador administrador = null;
        List<Administrador> administradores = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idAdministrador = rs.getInt("id_administrador");
                String nombre = rs.getString("nombre");
                String usuario = rs.getString("usuario");
                String pass = rs.getString("contrasena");

                administrador = new Administrador(idAdministrador, nombre, usuario, pass);
                administradores.add(administrador);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return administradores;
    }

    public Administrador encontrar(Administrador administrador) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, administrador.getIdAdministrador());
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos en el primer registro devuelto

            String nombre = rs.getString("nombre");
            String usuario = rs.getString("usuario");
            String pass = rs.getString("contrasena");
            
            administrador.setNombre(nombre);
            administrador.setUsuario(usuario);
            administrador.setPass(pass);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return administrador;
    }

    public int insertar(Administrador administrador) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, administrador.getNombre());
            stmt.setString(2, administrador.getUsuario());
            stmt.setString(3, administrador.getPass());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Administrador administrador) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, administrador.getNombre());
            stmt.setString(2, administrador.getUsuario());
            stmt.setString(3, administrador.getPass());
            stmt.setInt(4, administrador.getIdAdministrador());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Administrador administrador) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, administrador.getIdAdministrador());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}
