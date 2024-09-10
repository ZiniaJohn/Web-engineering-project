package ems;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
@SuppressWarnings("unused")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out= response.getWriter();
			response.setContentType("text/html");
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://127.0.0.1/employeemanagementsystem";
			Connection con = DriverManager.getConnection(url, "root", "root");
			String n=request.getParameter("txtName");
			String p=request.getParameter("txtPwd");
			
			PreparedStatement ps=con.prepareStatement("select username from login where username=? and password=?");
			ps.setString(1,n);
			ps.setString(2,p);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				RequestDispatcher rd= request.getRequestDispatcher("Welcome.html");
				rd.forward(request, response);
			}
			else
			{
				out.println("<font color=red size=10>Login failed!!<br>");
				out.println("<a href=Login.html>Try Again</a>");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
