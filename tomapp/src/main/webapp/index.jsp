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
        <c:if test = "${empty user}">
            <a href="/tomapp/login">Вход</a>
            </br>
            <a href="/tomapp/goregistrate">Регистрация</a>
        </c:if>


        <c:if test = "${(user.role == 'usr')or(user.role == 'admin')}">
            </br>
            <a href="/tomapp/site/inform">Страница пользователя</a>
            </br>
            <a href="/tomapp/site/logout">Logout</a>
        </c:if>
        <br>
        <a href="/tomapp/news">Новости</a>
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

        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
            <li class="page-item disabled">
              <a class="page-link" href="#" tabindex="-1">Previous</a>
            </li>
            <li class="page-item"><a class="page-link" href="/tomapp/news?page=1">1</a></li>
            <li class="page-item"><a class="page-link" href="/tomapp/news?page=2">2</a></li>
            <li class="page-item"><a class="page-link" href="/tomapp/news?page=3">3</a></li>
            <li class="page-item">
              <a class="page-link" href="#">Next</a>
            </li>
          </ul>
        </nav>

    </body>
</html>
