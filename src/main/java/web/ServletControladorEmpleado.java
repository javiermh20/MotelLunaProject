package web;

import Datos.EmpleadoDAO;
import domain.Empleado;
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

@WebServlet("/ServletControladorEmpleado")
public class ServletControladorEmpleado extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                {
                    try {
                        this.editarEmpleado(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "eliminar":
                {
                    try {
                        this.eliminarEmpleado(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        List<Empleado> empleados = new EmpleadoDAO().listar();
        System.out.println("empleados = " + empleados);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("empleados", empleados);
        sesion.setAttribute("totalEmpleados", empleados.size());
        //request.getRequestDispatcher("empleados.jsp").forward(request, response);
        response.sendRedirect("empleado.jsp");
    }

    private void editarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos el idEmpleado
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        Empleado empleado = new EmpleadoDAO().encontrar(new Empleado(idEmpleado));
        request.setAttribute("empleado", empleado);
        String jspEditar = "/WEB-INF/paginas/empleado/editarEmpleado.jsp";
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
                        this.insertarEmpleado(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "modificar":
                {
                    try {
                        this.modificarEmpleado(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControladorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void insertarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario agregarEmpleado
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = 0; 
        String edadString = request.getParameter("edad");
        if(edadString != null && !"".equals(edadString)){
            edad= Integer.parseInt(edadString);
        }
        String telefono = request.getParameter("telefono");

        //Creamos el objeto de empleado (modelo)
        Empleado empleado = new Empleado(nombre, apellido, edad, telefono);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new EmpleadoDAO().insertar(empleado);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void modificarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarInquilino
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        int edad = 0; 
        String edadString = request.getParameter("edad");
        if(edadString != null && !"".equals(edadString)){
            edad= Integer.parseInt(edadString);
        }
        String telefono = request.getParameter("telefono");

        //Creamos el objeto de empleado (modelo)
        Empleado empleado = new Empleado(idEmpleado, nombre, apellido, edad, telefono);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new EmpleadoDAO().actualizar(empleado);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
        private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarInquilino
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
     

        //Creamos el objeto de empleado (modelo)
        Empleado empleado = new Empleado(idEmpleado);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new EmpleadoDAO().eliminar(empleado);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}
