

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;


public class deli extends HttpServlet 
{
    Connection co;
    PreparedStatement ps,ps1;
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
                ps=co.prepareStatement("select * from inbox where id=?");
                ps.setString(1,i);
                rs=ps.executeQuery();
                while(rs.next())
                {
                  ps1=co.prepareStatement("insert into trash(too,frm,sub,mess) values(?,?,?,?)");
                  ps1.setString(1,rs.getString(2));
                  ps1.setString(2,rs.getString(3));
                  ps1.setString(3,rs.getString(4));
                  ps1.setString(4, rs.getString(5));
                  ps1.executeUpdate();
                  ps1.close();
                }
                ps.close();
                ps1=co.prepareStatement("delete from inbox where id=?");
                ps1.setString(1,i);
                ps1.executeUpdate();
                ps1.close();
           }
           res.sendRedirect("in");
        }catch(Exception w)
        {
            System.out.print(w);
        }
    }

}
