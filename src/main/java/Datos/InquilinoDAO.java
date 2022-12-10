package Datos;

import domain.Inquilino;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InquilinoDAO {    
    private static final String SQL_SELECT = "SELECT id_inquilino, nombre, apellido, edad, telefono"
            + " FROM inquilino";

    private static final String SQL_SELECT_BY_ID = "SELECT id_inquilino, nombre, apellido, edad, telefono"
            + " FROM inquilino WHERE id_inquilino = ?";

    private static final String SQL_INSERT = "INSERT INTO inquilino(nombre, apellido, edad, telefono) "
            + " VALUES(?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE inquilino "
            + " SET nombre=?, apellido=?, edad=?, telefono=? WHERE id_inquilino=?";

    private static final String SQL_DELETE = "DELETE FROM inquilino WHERE id_inquilino = ?";

    public List<Inquilino> listar() throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Inquilino inquilino = null;
        List<Inquilino> inquilinos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idInquilino = rs.getInt("id_inquilino");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                int edad = rs.getInt("edad");
                String telefono = rs.getString("telefono");

                inquilino = new Inquilino(idInquilino, nombre, apellido, edad, telefono);
                inquilinos.add(inquilino);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InquilinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return inquilinos;
    }

    public Inquilino encontrar(Inquilino inquilino) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, inquilino.getIdInquilino());
            rs = stmt.executeQuery();
            rs.absolute(1);//nos posicionamos en el primer registro devuelto

            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int edad = rs.getInt("edad");
            String telefono = rs.getString("telefono");
            
            inquilino.setNombre(nombre);
            inquilino.setApellido(apellido);
            inquilino.setEdad(edad);
            inquilino.setTelefono(telefono);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InquilinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return inquilino;
    }

    public int insertar(Inquilino inquilino) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, inquilino.getNombre());
            stmt.setString(2, inquilino.getApellido());
            stmt.setInt(3, inquilino.getEdad());
            stmt.setString(4, inquilino.getTelefono());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InquilinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int actualizar(Inquilino inquilino) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, inquilino.getNombre());
            stmt.setString(2, inquilino.getApellido());
            stmt.setInt(3, inquilino.getEdad());
            stmt.setString(4, inquilino.getTelefono());
            stmt.setInt(5, inquilino.getIdInquilino());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InquilinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int eliminar(Inquilino inquilino) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, inquilino.getIdInquilino());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InquilinoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}
   