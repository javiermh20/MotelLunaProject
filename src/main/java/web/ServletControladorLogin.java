package web;

import Datos.AdministradorDAO;
import Datos.InquilinoDAO;
import domain.Administrador;
import domain.Inquilino;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControladorLogin")
public class ServletControladorLogin extends HttpServlet{
    
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        System.out.println("Prueba Login");
        List<Administrador> admin = new AdministradorDAO().listar();
        System.out.println("administradores = " + admin.size());
        HttpSession sesion = request.getSession();
        sesion.setAttribute("admin", admin);
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("index.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "validar":
                {
                    try {
                        this.validar(request, response);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(ServletControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

            }
        } else {
            try {
                this.login(request, response);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ServletControladorLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void validar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        //recuperamos los valores del formulario agregarCliente
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("pass");
        List<Administrador> admin = new AdministradorDAO().listar();
        Boolean flag = false;
        for(Administrador x : admin){
            if(x.getUsuario().equals(usuario)  &&  password.equals(x.getPass())){{
                flag = true;
                }
            }
        }
        if(flag){
            this.sendInquilino(request, response);
        }else{
            this.login(request, response);
        }
        //Redirigimos hacia accion por default
    }
    
    private void sendInquilino(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        List<Inquilino> inquilinos = new InquilinoDAO().listar();
        System.out.println("inquilinos = " + inquilinos);
        HttpSession sesion = request.getSession();
        sesion.setAttribute("inquilinos", inquilinos);
        sesion.setAttribute("cuenta", inquilinos.size());
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("inicio.jsp");
    }
}
