<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head><title>Registration</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h2>Пожалуйста зарегистрируйтесь:</h2>
            <form name="loginForm" method="POST" action="registration">

            Логин:<br/>
            <input type="text" name="login" value=""/>
            <br/>Пароль:<br/>
            <input type="password" name="password" value=""/>
            <br/>

            <input type="submit" value="Регистрация"/>
            </form>
            <hr/>
            </br>
            ${errorLoginPassMessage}
            <a href="/tomapp/">Главная</a>
        </div>
    </body>
</html>
