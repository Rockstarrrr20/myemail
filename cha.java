

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;


public class cha extends HttpServlet 
{
    Connection co;
    PreparedStatement ps;
    ResultSet rs;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException 
        {
        HttpSession hs=req.getSession();
        PrintWriter pw=res.getWriter();
        String s1=(String)hs.getAttribute("user");
        String s2=req.getParameter("op");
        String s3=req.getParameter("pa");
        String s4=req.getParameter("con");
        if(s3.equals(s4))
        {
        try
        {
            co=(Connection)hs.getAttribute("con");
        
       
            ps=co.prepareStatement("select * from login where user=? and pass=?");
            ps.setString(1,s1);
            ps.setString(2,s2);
            rs=ps.executeQuery();
            int c=0;
            while(rs.next())
            {
                c=c+1;
            }
            ps.close();
            if(c==0)
            {
              pw.print("password wrong");
            }
            else
            {
                ps=co.prepareStatement("update login set pass=? where user=?");
                ps.setString(1,s3);
                ps.setString(2,s1);
                ps.executeUpdate();
                ps.close();
            }
          }
          catch(Exception e)
          {
              System.out.print(e);
          }
        }
        else
        {
            pw.print("password not match with confirm");
        }
        }
        
   

}
