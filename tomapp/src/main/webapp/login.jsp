<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>

</head>
<body>
    <h2>Пожалуйста, войдите:</h2>
    <form name="loginForm" method="POST" action="login">

    Login:<br/>
    <input type="text" name="login" value=""/>
    <br/>Password:<br/>
    <input type="password" name="password" value=""/>
    <br/>

    <input type="submit" value="Log in"/>
    </form><hr/>
    </br>
    <a href="/tomapp/goregistrate">Регистация</a>
    ${errorLoginPassMessage}
</body>
</html>
