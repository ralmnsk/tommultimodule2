<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
        <h2>Новости пользователя ${user.name}</h2>
        </br>
                     <a href="/tomapp/">Главная</a>
                    </br>
                    <a href="/tomapp/topsecret">Страница администратора</a>
                    </br>
                    <a href="/tomapp/topsecret2">Страница администратора 2</a>
                    </br>
                    <a href="/tomapp/site/logout">Logout</a>
                    <hr/>
                    <a href="/tomapp/site/addnews">Добавить новость</a>
                    </br>
                    <a href="/tomapp/site/mynews">Мои новости</a>
        <hr/>   <table>
                    <c:forEach var="entry" items="${map}">
                        <tr><td><p><h4>${entry.key.nameNews}</h4></br>
                                        ${entry.key.dataNews}<p>
                                                        </br>
                                Автор: ${entry.value.name}

                                                        </br>
                                Дата: ${entry.key.dateNews}</br>

                                </p>
                                    <hr/>
                    </c:forEach>
                </table>
        </div>
    </body>
</html>
