
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class trash extends HttpServlet 
{
    Connection co;
    PreparedStatement ps;
    ResultSet rs;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException 
    {
            HttpSession hs=req.getSession();
            String s1=(String)hs.getAttribute("user");
            co=(Connection)hs.getAttribute("con");
           PrintWriter pw=res.getWriter();
           try
           {
            ps=co.prepareStatement("select * from trash where frm=? or too=? ");
            ps.setString(1, s1);
            ps.setString(2, s1);
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
"                    <p align=\"centre\"><a href=\"trash\">Trash</a></p>\n" +
"                </td>\n" );
pw.print("<td width=\"60%\" height=\"300\">\n<form action=delt>");
               while(rs.next())
             {
               pw.print("<input type=checkbox name=ch value="+rs.getString(1)+">"+rs.getString(2)+"|"+"<a href=opent?inid="+rs.getString(1)+">"+rs.getString(4)+"</a>");
               pw.print("<hr>");
             }
pw.print("<input type=submit value=delete name=but></form>");
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
