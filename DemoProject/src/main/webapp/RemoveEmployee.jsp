<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
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
    <title>Remove Employee</title>
    <link rel="stylesheet" href="RemoveEmployee.css">
</head>
<body>
    <div class="background">
        <div align="center">
            <h1>Remove Employee</h1>
        </div>

        <form action="RemoveEmployeeServlet" method="post">
            <table>
                <tr>
                    <td>Employee ID:</td>
                    <td><input type="text" name="employeeId" required></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Remove Employee"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
