<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<% 
    String type = (String) session.getAttribute("type");
    if (type == null) {
        response.sendRedirect("Login.jsp");
    } else if (!"admin".equals(type)) {
        response.sendRedirect("Login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>View Employees</title>
    <link rel="stylesheet" href="ViewEmployee.css">
</head>
<body>
    <div class="background">
        <div align="center">
            <h1>Employee List</h1>
        </div>

        <table border="1">
            <tr>
                <th>Employee ID</th>
                <th>Name</th>
                <th>Phone</th>
                <th>User Type</th>
                <th>Department</th>
                <th>Position</th>
                <th>Salary</th>
            </tr>
            <% 
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://127.0.0.1/demoproject";
                    Connection con = DriverManager.getConnection(url, "root", "root");

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

                    while (rs.next()) {
            %>
            <tr>
                <td><%= rs.getString("id") %></td>
                <td><%= rs.getString("username") %></td>
                <td><%= rs.getString("phone") %></td>
                <td><%= rs.getString("usertype") %></td>
                <td><%= rs.getString("department") %></td>
                <td><%= rs.getString("position") %></td>
                <td><%= rs.getString("salary") %></td>
            </tr>
            <% 
                    }
                    con.close();
                } catch (Exception e) {
                    out.println(e);
                }
            %>
        </table>
    </div>
</body>
</html>
