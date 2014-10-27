package Servlets;

import Beans.LoginBean;
import static Beans.LoginBean.getLoginBeanIns;
import Servicios.ServicioMail;
import bos.Bono;
import bos.LikeU;
import bos.Tienda;
import bos.Usuario;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MailServlet extends HttpServlet {
    private static final long serialVersionUID = 4179545353414298791L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter pw = response.getWriter();
        System.out.println("Llego a MailServlet");
        
        String correo = request.getParameter("email");
        
        String valor = request.getParameter("valor");
        double valorD = Double.parseDouble(valor);
        
        String mensaje = request.getParameter("mensaje");
        String tiendaN = request.getParameter("tienda");
        String codigo = UUID.randomUUID().toString();
        String idUsuario = request.getParameter("idUsu");
           
        Usuario usu = LoginBean.getInstance().getUser();
        System.out.println(usu.getName());
        Tienda tienda = new Tienda(tiendaN);
        
        //Se crea el Bono
        Bono bono = new Bono(codigo, valorD, usu, tienda );
        usu.agregarBono(bono);
        
        //Se manda el correo
        
        String mensajeBono = "Usted ha recibido un nuevo bono de parte de " + usu.getName() + " con valor de " + valorD + " y codigo "+codigo+ " para la tienda " + tiendaN;
        System.out.println("Mensaje Bono: "+ mensajeBono);
        ServicioMail mail = new ServicioMail();
        mail.mandarCorreo(correo, mensajeBono );
        
        
        
        
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter pw = response.getWriter();
//        String message = request.getParameter("message");
//        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
//        
//        LoginBean lb = getLoginBeanIns();
//        System.out.println("LoginBean: " + lb);
//        Usuario u = lb.buscarUsuario();
//        System.out.println("Usuario en LikeServlet: " + u.getName());
//        
//        if(u == null){
//            pw.println("El usuario es null");
//        }
//        else{
//            pw.println("Bien!!");
//            pw.println("");
//            pw.println("Likes: ");
//            for(int i = 0; i < u.getLikes().size(); i++){
//                
//                LikeU l = u.getLikes().get(i);
//                System.out.println("Nombre Like Mio: "+l.getName());
//                
//                pw.println(l.getName() + "-" + l.getCategory());
//            }
//            pw.println("");
//                pw.println("Amigos: ");
//            for(int j=0; j< u.getAmigosEnApp().size(); j++){
//                Friend f = u.getAmigosEnApp().get(j);
//                
//                pw.println(f.getName()+"-"+f.getEmail()+"-"+f.getUsername());
//               
//                System.out.println("Likes Amigo: "+f.getInterestedIn().size());
//                for(int k = 0; k < f.getInterestedIn().size();k++){
//                    
//                       pw.println("-"+f.getInterestedIn().get(k));
//                       
//                }
//             }
//        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        procesar(request, response);
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter pw = response.getWriter();
//        String message = request.getParameter("message");
//        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
//        
//        LoginBean lb = getLoginBeanIns();
//        System.out.println("LoginBean: " + lb);
//        Usuario u = lb.buscarUsuario();
//        System.out.println("Usuario en LikeServlet: " + u.getName());
//        
//        if(u == null){
//            pw.println("El usuario es null");
//        }
//        else{
//            pw.println("Bien!!");
//            pw.println("");
//            pw.println("Likes: ");
//            for(int i = 0; i < u.getLikes().size(); i++){
//                
//                LikeU l = u.getLikes().get(i);
//                System.out.println("Nombre Like Mio: "+l.getName());
//                
//                pw.println(l.getName() + "-" + l.getCategory());
//            }
//            pw.println("");
//                pw.println("Amigos: ");
//            for(int j=0; j< u.getAmigosEnApp().size(); j++){
//                Friend f = u.getAmigosEnApp().get(j);
//                
//                pw.println(f.getName()+"-"+f.getEmail()+"-"+f.getUsername());
//               
//                System.out.println("Likes Amigo: "+f.getInterestedIn().size());
//                for(int k = 0; k < f.getInterestedIn().size();k++){
//                    
//                       pw.println("-"+f.getInterestedIn().get(k));
//                       
//                }
//             }
//        }
    }
    
    private void procesar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //request.setCharacterEncoding("UTF-8");
        PrintWriter res = response.getWriter();
        String message = request.getParameter("message");
        Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        
        LoginBean lb = getLoginBeanIns();
        System.out.println("LoginBean: " + lb);
        Usuario u = lb.buscarUsuario();
        System.out.println("Usuario en LikeServlet: " + u.getName());
        
        if(u == null){
            res.println("El usuario es null");
        }
        else{
            res.println("<!DOCTYPE html>");
            res.println("<html lang=\"en\">");
            res.println("");
            res.println("<head>");
            res.println("");
            res.println("    <meta charset=\"utf-8\">");
            res.println("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
            res.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
            res.println("    <meta name=\"description\" content=\"\">");
            res.println("    <meta name=\"author\" content=\"\">");
            res.println("");
            res.println("    <title>Tu Mejor Opci&oacute;n</title>");
            res.println("");
            res.println("    <!-- Bootstrap Core CSS - Uses Bootswatch Flatly Theme: http://bootswatch.com/flatly/ -->");
            res.println("    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
            res.println("");
            res.println("    <!-- Custom CSS -->");
            res.println("    <link href=\"css/freelancer.css\" rel=\"stylesheet\">");
            res.println("");
            res.println("    <!-- Custom Fonts -->");
            res.println("    <link href=\"font-awesome-4.1.0/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\">");
            res.println("    <link href=\"http://fonts.googleapis.com/css?family=Montserrat:400,700\" rel=\"stylesheet\" type=\"text/css\">");
            res.println("    <link href=\"http://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic\" rel=\"stylesheet\" type=\"text/css\">");
            res.println("");
            res.println("    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->");
            res.println("    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->");
            res.println("    <!--[if lt IE 9]>");
            res.println("        <script src=\"https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js\"></script>");
            res.println("        <script src=\"https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js\"></script>");
            res.println("    <![endif]-->");
            res.println("");
            res.println("</head>");
            res.println("");
            res.println("<body id=\"page-top\" class=\"index\">");
            res.println("");
            res.println("    <!-- Navigation -->");
            res.println("    <nav class=\"navbar navbar-default navbar-fixed-top\">");
            res.println("        <div class=\"container\">");
            res.println("            <!-- Brand and toggle get grouped for better mobile display -->");
            res.println("            <div class=\"navbar-header page-scroll\">");
            res.println("                <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">");
            res.println("                    <span class=\"sr-only\">Toggle navigation</span>");
            res.println("                    <span class=\"icon-bar\"></span>");
            res.println("                    <span class=\"icon-bar\"></span>");
            res.println("                    <span class=\"icon-bar\"></span>");
            res.println("                </button>");
            res.println("                <a class=\"navbar-brand\" href=\"index.jsp\">Tu Mejor Opci&oacute;n</a> <h5 style=\"color:white\" >" + u.getName());
            res.println("            </div>");
            res.println("");
            res.println("            <!-- Collect the nav links, forms, and other content for toggling -->");
            res.println("            <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">");
            res.println("                <ul class=\"nav navbar-nav navbar-right\">");
            res.println("                    <li class=\"hidden\">");
            res.println("                        <a href=\"#page-top\"></a>");
            res.println("                    </li>");
//            res.println("                    <li class=\"page-scroll\">");
//            res.println("                        <a href=\"#portfolio\">Portfolio</a>");
//            res.println("                    </li>");
//            res.println("                    <li class=\"page-scroll\">");
//            res.println("                        <a href=\"#about\">About</a>");
//            res.println("                    </li>");
//            res.println("                    <li class=\"page-scroll\">");
//            res.println("                        <a href=\"#contact\">Contact</a>");
//            res.println("                    </li>");
            res.println("                </ul>");
            res.println("            </div>");
            res.println("            <!-- /.navbar-collapse -->");
            res.println("        </div>");
            res.println("        <!-- /.container-fluid -->");
            res.println("    </nav>");
            res.println("");
            
            res.println("    <header style=\"background-color:white\">");
            res.println("        <div class=\"container\">");
            res.println("            <div class=\"row\">");
            res.println("                <div class=\"col-lg-12 text-center\" style=\"color:#2c3e50\">");
            res.println("                    <h2>Tiendas</h2>");
            res.println("                    <hr class=\"star-primary\">");
            res.println("                </div>");
            res.println("            </div>");
            res.println("            <div class=\"row\">");
            res.println("                <div class=\"col-lg-8 col-lg-offset-2\">");
            res.println("                    <!-- To configure the contact form email address, go to mail/contact_me.php and update the email address in the PHP file on line 19. -->");
            res.println("                    <!-- The form should work on most web servers, but if the form is not working you may need to configure your web server differently. -->");
            
            for(int i = 0; i < u.getLikes().size(); i++){
                
                LikeU l = u.getLikes().get(i);
                System.out.println("Nombre Like Mio: "+l.getName());
                
                //res.println("                    <a href=\"#\" class=\"btn btn-lg\">");
                //res.println("                        <i class=\"fa\" style=\"color:#18bc9c\"></i>" + l.getName());
                //res.println("                    </a>");
                
                res.println("<div class=\"panel-group\" id=\"accordion\">");
                res.println("  <div class=\"panel panel-default\">");
                res.println("    <div class=\"panel-heading\">");
                res.println("      <h4 class=\"panel-title\">");
                res.println("        <a data-toggle=\"collapse\" data-parent=\"#accordion\" href=\"#collapse"+l.getName().replaceAll(" ", "").replaceAll("[-+.^:,%&]","")+"\">");
                res.println(l.getName());
                res.println("        </a>");
                res.println("      </h4>");
                res.println("    </div>");
                res.println("    <div id=\"collapse"+l.getName().replaceAll(" ", "").replaceAll("[-+.^:,%&]","")+"\" class=\"panel-collapse collapse\">");
                res.println("      <div class=\"panel-body\" style=\"color:#2c3e50\">");
                res.println("        <h5>Enviar bono</h5>");
                
                res.println("<form class=\"form-horizontal\" role=\"form\" action=\"\" method=\"post\">");
                res.println("  <div class=\"form-group\">");
                res.println("    <label for=\"inputEmail\" class=\"col-sm-2 control-label\">Email*</label>");
                res.println("    <div class=\"col-sm-10\">");
                //res.println("      <span class=\"input-group-addon\">@</span>");
                res.println("      <input required type=\"hidden\" name=\"tienda\" id=\"tienda\" value=\""+l.getName()+"\">");
                res.println("      <input required type=\"email\" class=\"form-control\" name=\"email\" id=\"inputEmail\" placeholder=\"Email\">");
                res.println("    </div>");
                res.println("  </div>");
                res.println("  <div class=\"form-group\">");
                res.println("    <label for=\"valor\" class=\"col-sm-2 control-label\">Valor*</label>");
                res.println("    <div class=\"col-sm-10\">");
                //res.println("      <span class=\"input-group-addon\">$</span>");
                res.println("      <input required type=\"number\" class=\"form-control\" name=\"valor\" id=\"valor\" placeholder=\"Valor\">");
                res.println("    </div>");
                res.println("  </div>");
                res.println("  <div class=\"form-group\">");
                res.println("    <label for=\"mensaje\" class=\"col-sm-2 control-label\">Mensaje</label>");
                res.println("    <div class=\"col-sm-10\">");
                //res.println("      <span class=\"input-group-addon\">$</span>");
                res.println("      <textarea class=\"form-control\" name=\"mensaje\" id=\"mensaje\" placeholder=\"Message goes jir\"></textarea>");
                res.println("    </div>");
                res.println("  </div>");
                res.println("  <div class=\"form-group\">");
                res.println("    <div class=\"col-sm-offset-2 col-sm-10\">");
                res.println("      <button type=\"submit\" class=\"btn btn-success\">Enviar</button>");
                res.println("    </div>");
                res.println("  </div>");
                res.println("</form>");

                
                res.println("      </div>");
                res.println("    </div>");
                res.println("  </div>");
                res.println("</div>");
                //res.println(l.getName() + "-" + l.getCategory());
            }
            
//            res.println("                    <form name=\"sentMessage\" id=\"contactForm\" novalidate>");
//            res.println("                        <div class=\"row control-group\">");
//            res.println("                            <div class=\"form-group col-xs-12 floating-label-form-group controls\">");
//            res.println("                                <label>Name</label>");
//            res.println("                                <input type=\"text\" class=\"form-control\" placeholder=\"Name\" id=\"name\" required data-validation-required-message=\"Please enter your name.\">");
//            res.println("                                <p class=\"help-block text-danger\"></p>");
//            res.println("                            </div>");
//            res.println("                        </div>");
//            res.println("                        <div class=\"row control-group\">");
//            res.println("                            <div class=\"form-group col-xs-12 floating-label-form-group controls\">");
//            res.println("                                <label>Email Address</label>");
//            res.println("                                <input type=\"email\" class=\"form-control\" placeholder=\"Email Address\" id=\"email\" required data-validation-required-message=\"Please enter your email address.\">");
//            res.println("                                <p class=\"help-block text-danger\"></p>");
//            res.println("                            </div>");
//            res.println("                        </div>");
//            res.println("                        <div class=\"row control-group\">");
//            res.println("                            <div class=\"form-group col-xs-12 floating-label-form-group controls\">");
//            res.println("                                <label>Phone Number</label>");
//            res.println("                                <input type=\"tel\" class=\"form-control\" placeholder=\"Phone Number\" id=\"phone\" required data-validation-required-message=\"Please enter your phone number.\">");
//            res.println("                                <p class=\"help-block text-danger\"></p>");
//            res.println("                            </div>");
//            res.println("                        </div>");
//            res.println("                        <div class=\"row control-group\">");
//            res.println("                            <div class=\"form-group col-xs-12 floating-label-form-group controls\">");
//            res.println("                                <label>Message</label>");
//            res.println("                                <textarea rows=\"5\" class=\"form-control\" placeholder=\"Message\" id=\"message\" required data-validation-required-message=\"Please enter a message.\"></textarea>");
//            res.println("                                <p class=\"help-block text-danger\"></p>");
//            res.println("                            </div>");
//            res.println("                        </div>");
//            res.println("                        <br>");
//            res.println("                        <div id=\"success\"></div>");
//            res.println("                        <div class=\"row\">");
//            res.println("                            <div class=\"form-group col-xs-12\">");
//            res.println("                                <button type=\"submit\" class=\"btn btn-success btn-lg\">Send</button>");
//            res.println("                            </div>");
//            res.println("                        </div>");
//            res.println("                    </form>");
            res.println("                </div>");
            res.println("            </div>");
            res.println("        </div>");
            res.println("    </header>");
            
            res.println("    <footer class=\"text-center\">");
            res.println("        <div class=\"footer-below\">");
            res.println("            <div class=\"container\">");
            res.println("                <div class=\"row\">");
            res.println("                    <div class=\"col-lg-12\">");
            res.println("                        Copyright &copy; Tu Mejor Opci&oacute;n 2014");
            res.println("                    </div>");
            res.println("                </div>");
            res.println("            </div>");
            res.println("        </div>");
            res.println("    </footer>");
            
            res.println("    <!-- jQuery Version 1.11.0 -->");
            res.println("    <script src=\"js/jquery-1.11.0.js\"></script>");
            res.println("");
            res.println("    <!-- Bootstrap Core JavaScript -->");
            res.println("    <script src=\"js/bootstrap.min.js\"></script>");
            res.println("");
            res.println("    <!-- Plugin JavaScript -->");
            res.println("    <script src=\"http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js\"></script>");
            res.println("    <script src=\"js/classie.js\"></script>");
            res.println("    <script src=\"js/cbpAnimatedHeader.js\"></script>");
            res.println("");
            res.println("    <!-- Contact Form JavaScript -->");
            res.println("    <script src=\"js/jqBootstrapValidation.js\"></script>");
            res.println("    <script src=\"js/contact_me.js\"></script>");
            res.println("");
            res.println("    <!-- Custom Theme JavaScript -->");
            res.println("    <script src=\"js/freelancer.js\"></script>");
            res.println("");
            res.println("</body>");
            res.println("");
            res.println("</html>");
        }
    }
}
