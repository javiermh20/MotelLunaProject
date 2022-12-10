package web;

import Datos.ViviendaDAO;
import domain.Vivienda;
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

@WebServlet("/ServletControladroVivienda")
public class ServletControladroVivienda extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                {
                    try {
                        this.editarVivienda(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladroVivienda.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "eliminar":
                {
                    try {
                        this.eliminarVivienda(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladroVivienda.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladroVivienda.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControladroVivienda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        List<Vivienda> viviendas = new ViviendaDAO().listar();
        System.out.println("viviendas = " + viviendas);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("viviendas", viviendas);
        sesion.setAttribute("totalViviendas", viviendas.size());
        //request.getRequestDispatcher("viviendas.jsp").forward(request, response);
        response.sendRedirect("vivienda.jsp");
    }

    private void editarVivienda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos el idVivienda
        int idVivienda = Integer.parseInt(request.getParameter("idVivienda"));
        Vivienda vivienda = new ViviendaDAO().encontrar(new Vivienda(idVivienda));
        request.setAttribute("vivienda", vivienda);
        String jspEditar = "/WEB-INF/paginas/vivienda/editarVivienda.jsp";
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
                        this.insertarVivienda(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladroVivienda.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                case "modificar":
                {
                    try {
                        this.modificarVivienda(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladroVivienda.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                {
                    try {
                        this.accionDefault(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladroVivienda.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } else {
            try {
                this.accionDefault(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControladroVivienda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void insertarVivienda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario agregarVivienda
        int idDepartamento = 0; 
        String departamentoString = request.getParameter("idDepartamento");
        if(departamentoString != null && !"".equals(departamentoString)){
            idDepartamento= Integer.parseInt(departamentoString);
        }
        int idInquilino = 0; 
        String inquilinoString = request.getParameter("idInquilino");
        if(inquilinoString != null && !"".equals(inquilinoString)){
            idInquilino= Integer.parseInt(inquilinoString);
        }

        //Creamos el objeto de vivienda (modelo)
        Vivienda vivienda = new Vivienda(idDepartamento, idInquilino);

        //Insertamos el nuevo objeto en la base de datos
        int registrosModificados = new ViviendaDAO().insertar(vivienda);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

    private void modificarVivienda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarVivienda
        int idVivienda = Integer.parseInt(request.getParameter("idVivienda"));
        int idDepartamento = 0; 
        String departamentoString = request.getParameter("idDepartamento");
        if(departamentoString != null && !"".equals(departamentoString)){
            idDepartamento= Integer.parseInt(departamentoString);
        }
        int idInquilino = 0; 
        String inquilinoString = request.getParameter("idInquilino");
        if(inquilinoString != null && !"".equals(inquilinoString)){
            idInquilino= Integer.parseInt(inquilinoString);
        }
        //Creamos el objeto de vivienda (modelo)
        Vivienda vivienda = new Vivienda(idVivienda, idDepartamento, idInquilino);

        //Modificar el  objeto en la base de datos
        int registrosModificados = new ViviendaDAO().actualizar(vivienda);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }
    
        private void eliminarVivienda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario editarVivienda
        int idVivienda = Integer.parseInt(request.getParameter("idVivienda"));
     

        //Creamos el objeto de vivienda (modelo)
        Vivienda vivienda = new Vivienda(idVivienda);

        //Eliminamos el  objeto en la base de datos
        int registrosModificados = new ViviendaDAO().eliminar(vivienda);
        System.out.println("registrosModificados = " + registrosModificados);

        //Redirigimos hacia accion por default
        this.accionDefault(request, response);
    }

}
