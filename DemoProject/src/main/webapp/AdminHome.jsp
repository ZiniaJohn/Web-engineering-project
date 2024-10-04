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
    <title>Admin Home</title>
    <link rel="stylesheet" href="AdminHome.css">
</head>
<body>
 <div class="background">
    <h1>Welcome, Admin!</h1>
    <form action="LogoutServlet" method="post">
        <button type="button" onclick="location.href='AddEmployee.jsp'">Add Employee</button>
        <button type="button" onclick="location.href='RemoveEmployee.jsp'">Remove Employee</button>
        <button type="button" onclick="location.href='ViewEmployee.jsp'">View Employee</button>
        <button type="button" onclick="location.href='UpdateEmployee.jsp'">Update Employee</button>
        <button type="submit">Logout</button>
    </form>
   </div>
</body>
</html>
