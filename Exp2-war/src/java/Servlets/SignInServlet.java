package Servlets;

import Beans.LoginBean;
import Servicios.ServicioLogin;
import facebook4j.Facebook;
import facebook4j.FacebookFactory;
import facebook4j.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInServlet extends HttpServlet {
    private static final long serialVersionUID = -7453606094644144082L;
    
    LoginBean login = new LoginBean();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Facebook facebook = login.login();
        
        try
        {
            User me = facebook.getMe();
            
            System.out.println(me.getFirstName());
        }
        catch (Exception e)
        {
            
        }
        
        
        request.getSession().setAttribute("facebook", facebook);
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/callback");
        response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
    }
}
