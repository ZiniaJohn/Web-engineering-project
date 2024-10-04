package dp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RemoveEmployeeServlet")
public class RemoveEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RemoveEmployeeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/demoproject";
            Connection con = DriverManager.getConnection(url, "root", "root");

            String employeeId = request.getParameter("employeeId");

            PreparedStatement ps = con.prepareStatement("DELETE FROM employees WHERE id = ?");
            ps.setString(1, employeeId);

            int count = ps.executeUpdate();
            if (count > 0) {
            	 response.setContentType("text/html");
                 out.print("<font color=green>Employee removed Successfully</font>");
                 RequestDispatcher rd = request.getRequestDispatcher("RemoveEmployee.jsp");
                 rd.include(request, response);
            } else {
                response.setContentType("text/html");
                out.print("<font color=red>Failed to remove Employee, Try Again!</font>");
                RequestDispatcher rd = request.getRequestDispatcher("RemoveEmployee.jsp");
                rd.include(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
