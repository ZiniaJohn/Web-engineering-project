package dp;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;

@WebServlet("/EmployeeRecordServlet")
public class EmployeeRecordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EmployeeRecordServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String userId = (String) session.getAttribute("userId");
        System.out.println("Retrieved userId from session: " + userId);  // Debugging statement

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/demoproject", "root", "root");
            String sql = "SELECT * FROM employees WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                request.setAttribute("id", rs.getInt("id"));
                request.setAttribute("username", rs.getString("username"));
                request.setAttribute("phone", rs.getString("phone"));
                request.setAttribute("department", rs.getString("department"));
                request.setAttribute("position", rs.getString("position"));
                request.setAttribute("salary", rs.getString("salary"));
                System.out.println("Record found for userId: " + userId);  // Debugging statement
            } else {
                request.setAttribute("error", "No record found for the user.");
                System.out.println("No record found for userId: " + userId);  // Debugging statement
            }
            RequestDispatcher rd = request.getRequestDispatcher("EmployeeRecord.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
