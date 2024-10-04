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
    <title>User Home</title>
    <link rel="stylesheet" href="UserHome.css">
</head>
<body>
 <div class="background">
    <h1>Welcome, User!</h1>
     <form action="LogoutServlet" method="post">
        <button type="button" onclick="location.href='EmployeeRecord.jsp'">Show My Record</button>
        <button type="button" onclick="location.href='EmployeeUpdate.jsp'">Update My Record</button>
        <button type="submit">Logout</button>
    </form>
   </div>
</body>
</html>
