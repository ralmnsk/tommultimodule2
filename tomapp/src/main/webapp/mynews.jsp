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
                     <a href="${pageContext.request.contextPath}/">Главная</a>
                    </br>
                    <a href="${pageContext.request.contextPath}/topsecret">Страница администратора</a>
                    </br>
                    <a href="${pageContext.request.contextPath}/topsecret2">Страница администратора 2</a>
                    </br>
                    <a href="${pageContext.request.contextPath}/site/logout">Logout</a>
                    <hr/>
                    <a href="${pageContext.request.contextPath}/site/addnews">Добавить новость</a>
                    </br>
                    <a href="${pageContext.request.contextPath}/site/mynews">Мои новости</a>
                    </br>
                    <a href="${pageContext.request.contextPath}/site/contact">Мой контакт</a>
                    </br>
                    <a href="${pageContext.request.contextPath}/site/comment">Мои коментарии</a>
                    <hr/>
        <table>
                    <c:forEach var="entry" items="${map}">
                        <tr><td><p><h4>${entry.key.nameNews}</h4></br>
                                        ${entry.key.dataNews}<p>
                                                        </br>
                                Автор: ${entry.value.name}

                                                        </br>
                                Дата: ${entry.key.dateNews}</br>
                                <form name="editNewsForm" method="POST" action="edit">
                                <div class="form=group">
                                <input type="hidden" name="editNewsId" value="${entry.key.idNews}">
                                <input class="btn btn-primary" type="submit" value="Редактировать"/>
                                </div>
                                </form>

                                </p>
                                    <hr/>
                    </c:forEach>
                </table>
        </div>

        <nav aria-label="Page navigation example">
                  <ul class="pagination justify-content-center">

                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/mynews?move=previous">Previous</a></li>
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/mynews?move=next">Next</a></li>
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/mynews?maxResults=5">5</a></li>
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/mynews?maxResults=15">15</a></li>
                    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/mynews?maxResults=50">50</a></li>
                    <li class="page-item"><a class="page-link">Page: ${currentPage}</a></li>
                    <li class="page-item"><a class="page-link">Total: ${pagesCount}</a></li>

                  </ul>
        </nav>
    </body>
</html>
