package Servlets;

import Beans.LoginBean;
import static Beans.LoginBean.getLoginBeanIns;
import bos.LikeU;
import bos.Usuario;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LikesServlet extends HttpServlet {
    private static final long serialVersionUID = 4179545353414298791L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        String message = request.getParameter("message");
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        
        LoginBean lb = getLoginBeanIns();
        Usuario u = lb.buscarUsuario();
        if(u == null){
            pw.println("El usuario es null");
        }
        else{
            for(int i = 0; i < u.getLikes().size(); i++){
                LikeU l = u.getLikes().get(i);
             
                 pw.println(l.getName() + "-" + l.getCategory());
            }
        }
    }
    
}
