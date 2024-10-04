<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="javax.servlet.*" %>
<% 
    String type = (String) session.getAttribute("type");
    if (type == null) {
        response.sendRedirect("Login.jsp");
    } else if (!"user".equals(type)) {
        response.sendRedirect("Login.jsp");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>My Record</title>
    <link rel="stylesheet" href="ViewEmployee.css">
</head>
<body>
    <div class="background">
        <div align="center">
            <h1>My Record</h1>
        </div>
        <%
            HttpSession sess = request.getSession(false);
            if (sess == null || sess.getAttribute("username") == null) {
                response.sendRedirect("Login.jsp");
                return;
            }

            if (request.getAttribute("error") != null) {
                out.println("<p>" + request.getAttribute("error") + "</p>");
            } else {
                Integer id = (Integer) request.getAttribute("id");
                String username = (String) request.getAttribute("username");
                String phone = (String) request.getAttribute("phone");
                String department = (String) request.getAttribute("department");
                String position = (String) request.getAttribute("position");
                String salary = (String) request.getAttribute("salary");

                if (id != null && username != null && phone != null && department != null && position != null && salary != null) {
        %>
        <table border="1">
            <tr>
                <th>My ID</th>
                <th>Name</th>
                <th>Phone</th>
                <th>Department</th>
                <th>Position</th>
                <th>Salary</th>
            </tr>
            <tr>
                <td><%= id %></td>
                <td><%= username %></td>
                <td><%= phone %></td>
                <td><%= department %></td>
                <td><%= position %></td>
                <td><%= salary %></td>
            </tr>
        </table>
        <% 
                } else {
                    out.println("<p>No valid data to display.</p>");
                }
            }
        %>
    </div>
</body>
</html>
