

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class home extends HttpServlet {

    
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException 
    {
            HttpSession hs=req.getSession();
            String s1=(String)hs.getAttribute("user");
            PrintWriter pw=res.getWriter();
             pw.print("<html>\n" +
"    <body>\n" +
"        <table width=\"100%\" heigth=\"100%\" bgcolor=\"\" border = 1>\n" +
"            <tr>    \n" +
"                <td colspan=3 height=\"100\">\n" +
"                    hello\n" +s1+
                     "<p align=\"right\"><a href=\"cpass.html\">settting</a> | <a href=\"index.html\">logout</a></p>"
                     + "</td"   +
"            </tr>\n" +
"            <tr >\n" +
"                <td width=\"20%\" height=\"300\">\n" +
"                    <p align=\"centre\"><a href=\"in\">inbox</a></p>\n" +
"                    <p align=\"centre\"><a href=\"compose\">compose</a></p>\n" +
"                    <p align=\"centre\"><a href=\"sent\">Sent</a></p>\n" +
"                    <p align=\"centre\"><a href=\"draft\">draft</a></p>\n" +
"                    <p align=\"centre\"><a href=\"trash\">Trash</a></p>\n" +
"                </td>\n" +
"                <td width=\"60%\" height=\"300\">\n" +
"                    \n" +
"                </td>\n" +
"                <td width=\"20%\" height=\"300\">\n" +
"                    \n" +
"                </td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td colspan=3 height =100>\n" +
"                    \n" +
"                </td>\n" +
"            </tr>\n" +
"        </table>\n" +
"    </body>\n" +
"</html>");
            
            
    }

   
}
