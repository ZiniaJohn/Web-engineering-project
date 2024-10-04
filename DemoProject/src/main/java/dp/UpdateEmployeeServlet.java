package dp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("username");
        String phone = request.getParameter("phone");
        String usertype = request.getParameter("usertype");
        String department = request.getParameter("department");
        String position = request.getParameter("position");
        double salary = Double.parseDouble(request.getParameter("salary"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/demoproject";
            Connection con = DriverManager.getConnection(url, "root", "root");

            String query = "UPDATE employees SET username=?, phone=?, usertype=?, department=?, position=?, salary=? WHERE id=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, usertype);
            pstmt.setString(4, department);
            pstmt.setString(5, position);
            pstmt.setDouble(6, salary);
            pstmt.setString(7, id);

            int rowCount = pstmt.executeUpdate();
            if (rowCount > 0) {
                response.sendRedirect("AdminHome.jsp");
            } else {
                response.sendRedirect("UpdateEmployee.jsp");
            }

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
