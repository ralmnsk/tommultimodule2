<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
                                Дата: <fmt:formatDate type = "both" value = "${entry.key.dateNews}" /></br>
            <c:if test = "${(user.role == 'usr')or(user.role == 'admin')}">
                <form name="sendToDiscuss" method="POST" action="site/discuss">
                    <div class="form=group">
                        <input type="hidden" name="discussNewsId" value="${entry.key.idNews}">
                        <input class="btn btn-primary" type="submit" value="Перейти к обсуждению"/>
                    </div>
                </form>
            </c:if>


                                </p>
        <hr/>
                    </c:forEach>
                </table>
        </div>



        <nav aria-label="Page navigation example">
          <ul autofocus class="pagination justify-content-center">

            <li class="page-item"><a class="page-link" href="/tomapp/news?move=previous">Previous</a></li>
            <li class="page-item"><a class="page-link" href="/tomapp/news?move=next">Next</a></li>
            <li class="page-item"><a class="page-link" href="/tomapp/news?maxResults=5">5</a></li>
            <li class="page-item"><a class="page-link" href="/tomapp/news?maxResults=15">15</a></li>
            <li class="page-item"><a class="page-link" href="/tomapp/news?maxResults=50">50</a></li>
            <li class="page-item"><a class="page-link">Page: ${currentPage}</a></li>
            <li class="page-item"><a class="page-link">Total: ${pagesCount}</a></li>

          </ul>
        </nav>


    </body>
</html>
