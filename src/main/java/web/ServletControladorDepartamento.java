package web;

import Datos.DepartamentoDAO;
import domain.Departamento;
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

@WebServlet("/ServletControladorDepartamento")
public class ServletControladorDepartamento extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                {
                    try {
                        this.editarDepartamento(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorDepartamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "eliminar":
                {
                    try {
                        this.eliminarDepartamento(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorDepartamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorDepartamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControladorDepartamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        List<Departamento> departamentos = new DepartamentoDAO().listar();
        System.out.println("departamentos = " + departamentos);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("departamentos", departamentos);
        sesion.setAttribute("totalDepartamentos", departamentos.size());
        //request.getRequestDispatcher("departamentos.jsp").forward(request, response);
        response.sendRedirect("departamento.jsp");
    }

    private void editarDepartamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos el idDepartamento
        int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
        Departamento departamento = new DepartamentoDAO().encontrar(new Departamento(idDepartamento));
        request.setAttribute("departamento", departamento);
        String jspEditar = "/WEB-INF/paginas/departamento/editarDepartamento.jsp";
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
                        this.insertarDepartamento(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorDepartamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "modificar":
                {
                    try {
                        this.modificarDepartamento(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorDepartamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorDepartamento.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControladorDepartamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void insertarDepartamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario agregarDepartamento
        int numeroDepartamento = 0; 
        String numeroString = request.getParameter("numeroDepartamento");
        if(numeroString != null && !"".equals(numeroString)){
            numeroDepartamento= Integer.parseInt(numeroString);
        }
        String habitaciones = request.getParameter("habitaciones");

        //Creamos el objeto de departamento (modelo)
        Departamento departamento = new Departamento(numeroDepartamento, habitaciones);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new DepartamentoDAO().insertar(departamento);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void modificarDepartamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarDepartamento
        int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
        int numeroDepartamento = 0; 
        String numeroString = request.getParameter("numeroDepartamento");
        if(numeroString != null && !"".equals(numeroString)){
            numeroDepartamento= Integer.parseInt(numeroString);
        }
        String habitaciones = request.getParameter("habitaciones");

        //Creamos el objeto de departamento (modelo)
        Departamento departamento = new Departamento(idDepartamento, numeroDepartamento, habitaciones);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new DepartamentoDAO().actualizar(departamento);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
        private void eliminarDepartamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarDepartamento
        int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
     

        //Creamos el objeto de departamento (modelo)
        Departamento departamento = new Departamento(idDepartamento);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new DepartamentoDAO().eliminar(departamento);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}
