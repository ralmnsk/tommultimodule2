<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>
        Welcome
        </title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h2>Welcome</h2>
            Здраствуйте,
            <c:if test="${user.role =='usr'}">
                <c:out value="пользователь"/>
            </c:if>
            <c:if test="${user.role =='admin'}">
                        <c:out value="администратор"/>
            </c:if>

            ${user.name}
            </br>
            <a href="/tomapp/">Главная</a>
            </br>
            <a href="/tomapp/site/inform">Страница пользователя</a>
            </br>
            <a href="/tomapp/site/logout">Logout</a>
        </div>
    </body>
</html>