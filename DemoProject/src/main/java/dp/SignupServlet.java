package dp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SignupServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/demoproject";
            Connection con = DriverManager.getConnection(url, "root", "root");
            String id = request.getParameter("id");
            String name = request.getParameter("uname");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String usertype = request.getParameter("usertype");

            // Check if the ID exists in the employees table
            PreparedStatement checkEmployeeStmt = con.prepareStatement("SELECT id FROM employees WHERE id = ?");
            checkEmployeeStmt.setString(1, id);
            ResultSet rs = checkEmployeeStmt.executeQuery();

            if (!rs.next()) {
                // ID does not exist in employees table
                response.setContentType("text/html");
                out.print("<font color=red>Signup Failed, Employee ID does not exist!</font>");
                RequestDispatcher rd = request.getRequestDispatcher("signup.html");
                rd.include(request, response);
                return;
            }

            // ID exists, proceed with signup
            PreparedStatement ps = con.prepareStatement("INSERT INTO users (id, username, password, phone, usertype) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.setString(4, phone);
            if ("null".equals(usertype)) {
                ps.setNull(5, java.sql.Types.VARCHAR); // Store NULL in the usertype column
            } else {
                ps.setString(5, usertype);
            }

            int count = ps.executeUpdate();
            if (count > 0) {
                response.setContentType("text/html");
                out.print("<font color=green>Signup Successful</font>");
                RequestDispatcher rd = request.getRequestDispatcher("signup.html");
                rd.include(request, response);
            } else {
                response.setContentType("text/html");
                out.print("<font color=red>Signup Failed, Try Again!</font>");
                RequestDispatcher rd = request.getRequestDispatcher("signup.html");
                rd.include(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
