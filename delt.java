

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;


public class delt extends HttpServlet 
{
    Connection co;
    PreparedStatement ps1;
    ResultSet rs;
   
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException 
    {
        try
        {
            HttpSession hs=req.getSession();
            co=(Connection)hs.getAttribute("con");
            String che[]=req.getParameterValues("ch");
            for(String i:che)
           {
                ps1=co.prepareStatement("delete from trash where id=?");
                ps1.setString(1,i);
                ps1.executeUpdate();
                ps1.close();
           }
           res.sendRedirect("trash");
        }catch(Exception w)
        {
            System.out.print(w);
        }
    }

}
