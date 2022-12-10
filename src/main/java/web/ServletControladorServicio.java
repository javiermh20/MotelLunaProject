package web;

import Datos.ServicioDAO;
import domain.Servicio;
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

@WebServlet("/ServletControladorServicio")
public class ServletControladorServicio extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                {
                    try {
                        this.editarServicio(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "eliminar":
                {
                    try {
                        this.eliminarServicio(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        List<Servicio> servicios = new ServicioDAO().listar();
        System.out.println("servicios = " + servicios);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("servicios", servicios);
        sesion.setAttribute("totalServicios", servicios.size());
        //request.getRequestDispatcher("servicios.jsp").forward(request, response);
        response.sendRedirect("servicio.jsp");
    }

    private void editarServicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos el idServicio
        int idServicio = Integer.parseInt(request.getParameter("idServicio"));
        Servicio servicio = new ServicioDAO().encontrar(new Servicio(idServicio));
        request.setAttribute("servicio", servicio);
        String jspEditar = "/WEB-INF/paginas/servicio/editarServicio.jsp";
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
                        this.insertarServicio(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "modificar":
                {
                    try {
                        this.modificarServicio(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControladorServicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void insertarServicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario agregarServicio
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        int idDepartamentos = Integer.parseInt(request.getParameter("idDepartamentos"));
        /* int idEmpleado = 0; 
        String empleadoString = request.getParameter("idEmpleado");
        if(empleadoString != null && !"".equals(empleadoString)){
            idEmpleado= Integer.parseInt(empleadoString);
        }
        int idDepartamento = 0; 
        String departamentoString = request.getParameter("idDepartamentos");
        if(departamentoString != null && !"".equals(departamentoString)){
            idDepartamento= Integer.parseInt(departamentoString);
        } */
        String servicio1 = request.getParameter("servicio");
        String estado = request.getParameter("status");


        //Creamos el objeto de servicio (modelo)
        Servicio servicio = new Servicio(idEmpleado, idDepartamentos, servicio1, estado);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new ServicioDAO().insertar(servicio);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void modificarServicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarServicio
        int idServicio = Integer.parseInt(request.getParameter("idServicio"));
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        int idDepartamentos = Integer.parseInt(request.getParameter("idDepartamentos"));
        /*int idEmpleado = 0; 
        String empleadoString = request.getParameter("idEmpleado");
        if(empleadoString != null && !"".equals(empleadoString)){
            idEmpleado= Integer.parseInt(empleadoString);
        }
        int idDepartamento = 0; 
        String departamentoString = request.getParameter("idDepartamentos");
        if(departamentoString != null && !"".equals(departamentoString)){
            idDepartamento= Integer.parseInt(departamentoString);
        } */
        String servicio1 = request.getParameter("servicio");
        String estado = request.getParameter("status");

        //Creamos el objeto de servicio (modelo)
        Servicio servicio = new Servicio(idServicio, idEmpleado, idDepartamentos, servicio1, estado);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new ServicioDAO().actualizar(servicio);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
        private void eliminarServicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarServicio
        int idServicio = Integer.parseInt(request.getParameter("idServicio"));
     

        //Creamos el objeto de Servicip (modelo)
        Servicio servicio = new Servicio(idServicio);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new ServicioDAO().eliminar(servicio);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}

