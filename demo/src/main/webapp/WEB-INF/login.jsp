<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Login</title>
    <meta charset="utf-8">
     <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h2>Пожалуйста, войдите:</h2>
            <form name="formLogin" method="POST" action="/login">

            Login:<br/>
            <input type="text" name="login" value=""/>
            <br/>Password:<br/>
            <input type="password" name="password" value=""/>
            <br/>

            <input type="submit" value="Log in"/>
            </form><hr/>

        ${errorLoginPassMessage}
        </br>
        <a href="${pageContext.request.contextPath}/goregistrate">Регистация</a>
        </br>
        <a href="${pageContext.request.contextPath}/">Главная</a>
    </div>
</body>
</html>
