
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <link rel="stylesheet" href="LoginPage.css">
</head>

<body>
<div class="background">
  <div align="center">
  <h1>Login</h1>
  </div>

<form action="LoginServlet" method="post">
<table>
<tr><td>Enter ID:</td><td><input type="text" name="txtId"required></td></tr>
<tr><td>Enter name:</td><td><input type="text" name="txtName" required></td></tr>
<tr><td>Enter password:</td><td><input type="password" name="txtPwd" required></td></tr>
<tr><td><input type="submit" value="Login"></td><td><input type="button" value="SignUp" onclick="location.href='Signup.html'"></td></tr>

 
</table>
</form>
</div>
</body>
</html>