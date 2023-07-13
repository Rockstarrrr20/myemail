
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class veri extends HttpServlet {

    Connection co;
    PreparedStatement ps,ps1;
    ResultSet rs;
    PrintWriter pw;

    public void init() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            co = DriverManager.getConnection("jdbc:mysql://localhost:3306/email", "root", null);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            ServletContext sc = getServletContext();
            sc.setAttribute("con", co);
            HttpSession hs=req.getSession();
            String s1 = req.getParameter("user");
            String s2 = req.getParameter("pa");
            String s3 = req.getParameter("su");
            hs.setAttribute("user", s1);
            hs.setAttribute("con", co);
            
            pw=res.getWriter();
            if (s3.equals("Signup")) {
                try {
                    ps= co.prepareStatement("select * from login where user=?");
                    ps.setString(1, s1);
                    rs= ps.executeQuery();
                    int c=0;
                    while(rs.next())
                    {
                        c=0;
                        c=c+1;
                    }
                    if(c==0)
                    {
                    ps1 = co.prepareStatement("insert into login(user,pass) values(?,?)");
                    ps1.setString(1, s1);
                    ps1.setString(2, s2);
                    ps1.executeUpdate();
                    ps1.close();
                    res.sendRedirect("home");
                    }
                    else
                    {
                      pw.print("user already found");
                    }
                    ps.close();
                } catch (Exception ee) {
                    System.out.print(ee);
                }

            }
            else
            {
                ps= co.prepareStatement("select * from login where user=? and pass=?");
                    ps.setString(1, s1);
                    ps.setString(2, s2);
                    rs= ps.executeQuery();
                    int c=0;
                    while(rs.next())
                    {
                        c=0;
                        c=c+1;
                    }
                    if(c==0)
                    {
                      pw=res.getWriter();
                      pw.print("not valid user or password");
                    } 
                    else
                    {
                       res.sendRedirect("home");
                      
                    }
            }
        } catch (Exception se) 
        {
            System.out.print(se);
        }
    }
}
