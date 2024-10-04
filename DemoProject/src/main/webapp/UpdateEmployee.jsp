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
    <title>Update Employee</title>
    <link rel="stylesheet" href="UpdateEmployee.css">
</head>
<body>
    <div class="background">
        <div align="center">
            <h1>Update Employee</h1>
        </div>

        <form action="UpdateEmployeeServlet" method="post">
            <table>
                <tr>
                    <td>Employee ID:</td>
                    <td><input type="text" name="id" required></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="username"></td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td><input type="text" name="phone"></td>
                </tr>
                <tr>
                    <td>User Type:</td>
                    <td>
                        <select name="usertype">
                            <option value="user">User</option>
                            <option value="admin">Admin</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Department:</td>
                    <td><input type="text" name="department"></td>
                </tr>
                <tr>
                    <td>Position:</td>
                    <td><input type="text" name="position"></td>
                </tr>
                <tr>
                    <td>Salary:</td>
                    <td><input type="number" name="salary" step="0.01"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Update Employee"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
