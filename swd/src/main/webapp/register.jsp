<%--
  Created by IntelliJ IDEA.
  User: PHONGDVSE61654
  Date: 7/21/2018
  Time: 6:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="/createUser" method="post">
    Username: <input type="text" name="username" id="txtUser"></br>
    Password: <input type="password" name="password" id="txtPassword"></br>
    Re-type Password: <input type="password" name="password" id="txtReTypePassword"></br>
    <input type="submit" value="Register">


</form>


</body>
</html>
