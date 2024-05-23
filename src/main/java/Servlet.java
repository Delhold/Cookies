import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

//SIEMPRE EL PATH
@WebServlet("/Servlet")
public class Servlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //CREAR UNA VARIABLE DE NUEVO USUARIO
        //PARA SABER SI ES UN USUARIO NUVEO
        //O ANTIGUO
        Boolean nuevoUsuario=true;
        //VAMOS A TENER EL ARREGLO DE LAS COOKIES
        Cookie[] cookies = request.getCookies();
        //VAMOS A VALIDAR SI EXISTE O NO LA COOKIE
        if (cookies != null) {
            //VA A RECORRER TODAS LAS COOKIES DE INICIO A FIN
            for (Cookie cookie : cookies) {
                //EN ESTA CONDICION SI ES UN VISITANTE RECURRENTE VA A RETORNAR UN FALSE
                if (cookie.getName().equals("visitanteRecurrente") && cookie.getValue().equals("si")) {
                    nuevoUsuario=false;
                    break;

                }
            }
        }
        String mensaje=null;
        if (nuevoUsuario) {
            Cookie visitanteCookie = new Cookie("visitanteRecurrente", "si");
            response.addCookie(visitanteCookie);
            mensaje="Gracias or ingresar al sitio por primera vez";
        }else {
            mensaje="Gracias por visitar mi sitio nuevamente";
        }
        response.setContentType("text/html;charset=UTF-8");
        out.print("<!DOCTYPE html>");
        out.print("<html>");
        out.print("<head>");
        out.printf("<meta charset=\"utf-8\">");
        out.printf("<title> Hola Mundo </title>");
        out.print("</head>");
        out.print("<body>");
        out.print("<h1>"+nuevoUsuario+"</h1>");
        out.print("<h1>"+cookies+"</h1>");
        out.print("<h1>"+mensaje+"</h1>");
        out.print("</body>");
        out.print("</html>");

    }
}
//REALIZAR UN CONTEO PARA LAS COOKIES Y MOSTRAR EN PANTALLA
