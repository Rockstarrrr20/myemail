import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.http.HttpSession;


public class del extends HttpServlet 
{
    Connection co;
    PreparedStatement ps;
    ResultSet rs;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException 
        {
        HttpSession hs=req.getSession();
        String s1=(String)hs.getAttribute("user");
        String s2=req.getParameter("to");
        String s3=req.getParameter("sub");
        String s4=req.getParameter("me");
        String s5=req.getParameter("but");
        PrintWriter pw=res.getWriter();
        try
        {
           co=(Connection)hs.getAttribute("con"); 
        }catch(Exception ee)
                {
                System.out.print(ee);
                }
        if(s5.equals("send"))
        {
        try
        {
            
            ps=co.prepareStatement("select * from login where user=?");
            ps.setString(1,s2);
            rs=ps.executeQuery();
            int c=0;
            while(rs.next())
            {
                c=c+1;
            }
            ps.close();
            if(c==0)
            {
                String a="not send"+s3;
                ps=co.prepareStatement("insert into draft(too,frm,sub,mess) values(?,?,?,?)");
                ps.setString(1,s2);
                ps.setString(2,s1);
                ps.setString(3,a);
                ps.setString(4,s4);
                ps.executeUpdate();
                ps.close(); 
              res.sendRedirect("home");
            } 
            else
            {
                //for user sent
                ps=co.prepareStatement("insert into sent(too,frm,sub,mess) values(?,?,?,?)");
                ps.setString(1,s2);
                ps.setString(2,s1);
                ps.setString(3,s3);
                ps.setString(4,s4);
                ps.executeUpdate();
                ps.close();
                //for reciever inbox
                ps=co.prepareStatement("insert into inbox(too,frm,sub,mess) values(?,?,?,?)");
                ps.setString(1,s2);
                ps.setString(2,s1);
                ps.setString(3,s3);
                ps.setString(4,s4);
                ps.executeUpdate();
                ps.close();
                res.sendRedirect("home");
            }
        }
        catch(Exception e)
        {   
            System.out.print(e);
        }
        }
        else
        {
            try
            {
          ps=co.prepareStatement("insert into draft(too,frm,sub,mess) values(?,?,?,?)");
                ps.setString(1,s2);
                ps.setString(2,s1);
                ps.setString(3,s3);
                ps.setString(4,s4);
                ps.executeUpdate();
                ps.close(); 
                res.sendRedirect("home");
            }catch(Exception eee)
            {   
                System.out.print(eee);
            }
        }
        }
   

}
