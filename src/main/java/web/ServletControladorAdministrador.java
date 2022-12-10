package web;

import Datos.AdministradorDAO;
import domain.Administrador;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletControladorAdministrador")
public class ServletControladorAdministrador extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                {
                    try {
                        this.editarAdministrador(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "eliminar":
                {
                    try {
                        this.eliminarAdministrador(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControladorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        List<Administrador> administradores = new AdministradorDAO().listar();
        System.out.println("administradores = " + administradores);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("administradores", administradores);
        sesion.setAttribute("totalAdministradores", administradores.size());
        //request.getRequestDispatcher("administradores.jsp").forward(request, response);
        response.sendRedirect("administrador.jsp");
    }

    private void editarAdministrador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos el idAdministrador
        int idAdministrado = Integer.parseInt(request.getParameter("idAdministrador"));
        Administrador administrador = new AdministradorDAO().encontrar(new Administrador(idAdministrado));
        request.setAttribute("administrador", administrador);
        String jspEditar = "/WEB-INF/paginas/administrador/editarAdministrador.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                {
                    try {
                        this.insertarAdministrador(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "modificar":
                {
                    try {
                        this.modificarAdministrador(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControladorAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void insertarAdministrador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario agregarAdministrador
        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        String pass = request.getParameter("pass");

        //Creamos el objeto de administrador (modelo)
        Administrador administrador = new Administrador(nombre, usuario, pass);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new AdministradorDAO().insertar(administrador);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void modificarAdministrador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarAdministrador
        int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));
        String nombre = request.getParameter("nombre");
        String usuario = request.getParameter("usuario");
        String pass = request.getParameter("pass");

        //Creamos el objeto de administrador (modelo)
        Administrador administrador = new Administrador(idAdministrador, nombre, usuario, pass);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new AdministradorDAO().actualizar(administrador);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
        private void eliminarAdministrador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarInquilino
        int idAdministrado = Integer.parseInt(request.getParameter("idAdministrador"));
     

        //Creamos el objeto de administrador (modelo)
        Administrador administrador = new Administrador(idAdministrado);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new AdministradorDAO().eliminar(administrador);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}
