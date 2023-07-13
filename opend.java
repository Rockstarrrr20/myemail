import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;


public class opend extends HttpServlet
{
    Connection co;
    PreparedStatement ps;
    ResultSet rs;
    PrintWriter pw;
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException 
    {
        try
        {
            HttpSession hs=req.getSession();
            String s1=req.getParameter("inid");
            co=(Connection)hs.getAttribute("con");
            pw=res.getWriter();
            ps=co.prepareStatement("select * from draft where id=?");
            ps.setString(1,s1);
            rs=ps.executeQuery();
            
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
"                    <p align=\"centre\"><a href=\"Trash\">Trash</a></p>\n" +
"                </td>\n" );
pw.print("<td width=\"60%\" height=\"300\">\n");
               while(rs.next())
             {
                 
                pw.print("<p align=centre>to       "+rs.getString(2)+"</p>\n");
                pw.print("<p align=centre>subject: "+rs.getString(4)+"</p>");
                pw.print("<p align=centre>"+rs.getString(5)+"</p>");
             }

pw.print("</td>");
pw.print("<td width=\"20%\" height=\"300\">\n" +
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
        }catch(Exception ee)
           {
               System.out.print(ee);
           }
                   
        }
        
}
