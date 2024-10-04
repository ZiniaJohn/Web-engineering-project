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
    <title>Update Information</title>
    <link rel="stylesheet" href="UpdateEmployee.css">
</head>
<body>
    <div class="background">
        <div align="center">
            <h1>Update Information</h1>
        </div>
        <form action="EmployeeUpdateServlet" method="post">
            <table>
                <tr>
                    <td>Select Field to Update:</td>
                    <td>
                        <select name="updateField" required>
                            <option value="phone">Phone</option>
                            <option value="username">Name</option>
                            <option value="password">Password</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>New Value:</td>
                    <td><input type="text" name="newValue" required></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Update"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
