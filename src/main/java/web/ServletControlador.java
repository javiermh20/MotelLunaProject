package web;

import Datos.InquilinoDAO;
import domain.Inquilino;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                {
                    try {
                        this.editarInquilino(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "eliminar":
                {
                    try {
                        this.eliminarInquilino(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        List<Inquilino> inquilinos = new InquilinoDAO().listar();
        System.out.println("inquilinos = " + inquilinos);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("inquilinos", inquilinos);
        sesion.setAttribute("totalInquilinos", inquilinos.size());
        //request.getRequestDispatcher("inquilinos.jsp").forward(request, response);
        response.sendRedirect("inquilino.jsp");
    }

    private void editarInquilino(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos el idInquilino
        int idInquilino = Integer.parseInt(request.getParameter("idInquilino"));
        Inquilino inquilino = new InquilinoDAO().encontrar(new Inquilino(idInquilino));
        request.setAttribute("inquilino", inquilino);
        String jspEditar = "/WEB-INF/paginas/inquilino/editarInquilino.jsp";
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
                        this.insertarInquilino(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "modificar":
                {
                    try {
                        this.modificarInquilino(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void insertarInquilino(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario agregarInquilino
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = 0; 
        String edadString = request.getParameter("edad");
        if(edadString != null && !"".equals(edadString)){
            edad= Integer.parseInt(edadString);
        }
        String telefono = request.getParameter("telefono");

        //Creamos el objeto de inquilino (modelo)
        Inquilino inquilino = new Inquilino(nombre, apellido, edad, telefono);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new InquilinoDAO().insertar(inquilino);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void modificarInquilino(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarInquilino
        int idInquilino = Integer.parseInt(request.getParameter("idInquilino"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = 0; 
        String edadString = request.getParameter("edad");
        if(edadString != null && !"".equals(edadString)){
            edad= Integer.parseInt(edadString);
        }
        String telefono = request.getParameter("telefono");

        //Creamos el objeto de inquilino (modelo)
        Inquilino inquilino = new Inquilino(idInquilino, nombre, apellido, edad, telefono);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new InquilinoDAO().actualizar(inquilino);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
        private void eliminarInquilino(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarInquilino
        int idInquilino = Integer.parseInt(request.getParameter("idInquilino"));
     

        //Creamos el objeto de inquilino (modelo)
        Inquilino inquilino = new Inquilino(idInquilino);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new InquilinoDAO().eliminar(inquilino);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}