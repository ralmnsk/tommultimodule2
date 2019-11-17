<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head><title>Registration</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            ${message}
            </br>
            <a href="${pageContext.request.contextPath}/">Главная</a>
            </br>
            <a href="${pageContext.request.contextPath}/site/login">Вход</a>
            </br>
            <a href="${pageContext.request.contextPath}/goregistrate">Возврат к регистации</a>
        </div>
    </body>
</html>
