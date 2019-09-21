<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
        <h2>Главная станица</h2>
        main page (index page)
        </br>
        <a href="/tomapp/login">Вход</a>
        </br>
        <a href="/tomapp/goregistrate">Регистрация</a>
        </br>
        <a href="/tomapp/news">Новости</a>
        <hr/>   <table>
                    <c:forEach var="news" items="${newsList}">
                        <tr><td><p><h4>${news.nameNews}</h4></br>
                                        ${news.dataNews}<p>
                                Дата: ${news.dateNews}</br>

                                </p>
                                    <hr/>
                    </c:forEach>
                </table>
        </div>
    </body>
</html>
