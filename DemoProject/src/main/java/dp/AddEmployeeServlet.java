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

@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddEmployeeServlet() {
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

            @SuppressWarnings("unused")
			String id = request.getParameter("id");
            String name = request.getParameter("username");
            String password = request.getParameter("password");
            String phone = request.getParameter("phone");
            String usertype = request.getParameter("usertype");
            String department = request.getParameter("department");
            String position = request.getParameter("position");
            String salaryStr = request.getParameter("salary");
            double salary = salaryStr.isEmpty() ? 0.0 : Double.parseDouble(salaryStr);

            PreparedStatement ps = con.prepareStatement("INSERT INTO employees (id, username, password, phone, usertype, department, position, salary) VALUES (?,?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, password);
            ps.setString(4, phone);
            ps.setString(5, usertype);
            ps.setString(6, department);
            ps.setString(7, position);
            ps.setDouble(8, salary);

            int count = ps.executeUpdate();
            if (count > 0) {
                response.setContentType("text/html");
                out.print("<font color=green>Employee Added Successfully</font>");
                RequestDispatcher rd = request.getRequestDispatcher("AddEmployee.jsp");
                rd.include(request, response);
            } else {
                response.setContentType("text/html");
                out.print("<font color=red>Failed to Add Employee, Try Again!</font>");
                RequestDispatcher rd = request.getRequestDispatcher("AddEmployee.jsp");
                rd.include(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
