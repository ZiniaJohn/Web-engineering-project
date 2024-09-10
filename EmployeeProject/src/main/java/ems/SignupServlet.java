package ems;

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

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://127.0.0.1/employeemanagementsystem";
			Connection con = DriverManager.getConnection(url, "root", "root");
			String name= request.getParameter("uname");
			String password= request.getParameter("password");
			String phone= request.getParameter("phone");
			
			PreparedStatement ps=con.prepareStatement("insert into login values(?, ?, ?)");
			ps. setString(1, name);
			ps. setString(2, password);
			ps. setString(3, phone);
			
			int count = ps.executeUpdate();
			if(count>0)
			{
				response.setContentType("Text/html");
				out.print("<font color= green>Signup Successfull ");
				
				RequestDispatcher rd=request.getRequestDispatcher("Signup.html");
				rd.include(request, response);
			}
			else
			{
				response.setContentType("Text/html");
				out.print("<font color=red>Not Signup, Try Again! ");
				
				RequestDispatcher rd=request.getRequestDispatcher("Signup.html");
				rd.include(request, response);
				
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
