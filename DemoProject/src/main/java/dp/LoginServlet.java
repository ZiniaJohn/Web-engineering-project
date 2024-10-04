package dp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@SuppressWarnings("unused")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/demoproject";
            Connection con = DriverManager.getConnection(url, "root", "root");
            String i = request.getParameter("txtId");
            String n = request.getParameter("txtName");
            String p = request.getParameter("txtPwd");

            PreparedStatement ps = con.prepareStatement("select username, usertype from users where id=? and username=? and password=?");
            ps.setString(1, i);
            ps.setString(2, n);
            ps.setString(3, p);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String userType = rs.getString("usertype");
                HttpSession session = request.getSession();
                session.setAttribute("userId", i);
                session.setAttribute("username", n);
                session.setAttribute("type", userType);
                
                System.out.println("Login successful. UserId: " + i + ", Username: " + n + ", UserType: " + userType);  // Debugging statement
                
                if (userType == null) { 
                    response.sendRedirect("./Login.html?message=Please login again."); 
                } else if ("user".equals(userType)) { 
                    response.sendRedirect("./UserHome.jsp"); 
                } else if ("admin".equals(userType)) { 
                    response.sendRedirect("./AdminHome.jsp");
                }
            } else {
                out.println("<font color=red size=10>Not A User! Login failed!!<br>");
                out.println("<a href=Signup.html>Signup and Try Again</a>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
