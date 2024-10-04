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
    <title>Add Employee</title>
    <link rel="stylesheet" href="AddEmployee.css">
</head>
<body>
    <div class="background">
        <div align="center">
            <h1>Add New Employee</h1>
        </div>

        <form action="AddEmployeeServlet" method="post">
            <table>
                 <tr>
                    <td>ID:</td>
                    <td><input type="text" name="id" required></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="username" required></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" required></td>
                </tr>
                <tr>
                    <td>Phone:</td>
                    <td><input type="text" name="phone"  placeholder="i.e (11-digit) 03xxxxxxxxx" required></td>
                </tr>
                <tr>
                    <td>User Type:</td>
                    <td>
                        <select name="usertype" required>
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
                    <td colspan="2"><input type="submit" value="Add Employee"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
