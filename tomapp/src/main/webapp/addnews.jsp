<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head><title>ADD NEWS</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h2>Добавление новости ${user.name}</h2>

            </br>
             <a href="/tomapp/">Главная</a>

            </br>
                                    <c:if test = "${(user.role == 'admin')}">
                                        <a href="${pageContext.request.contextPath}/topsecret">Страница администратора</a>
                                        </br>
                                        <a href="${pageContext.request.contextPath}/topsecret2">Страница администратора 2</a>
                                        </br>
                                    </c:if>

            <a href="/tomapp/site/logout">Logout</a>
            <hr/>
            <a href="/tomapp/site/inform">Страница пользователя</a>
            </br>
            <a href="${pageContext.request.contextPath}/site/mynews">Мои новости</a>
            </br>
            <a href="${pageContext.request.contextPath}/site/contact">Мой контакт</a>
            </br>
            <a href="${pageContext.request.contextPath}/site/comment">Мои коментарии</a>
            <hr/>
        </div>

            </br>
            <form name="createNewsForm" method="POST" action="createnews">
                <div class="form-group">
                    <label for="exampleInputText1">Название новости</label>
                    <input type="text" class="form-control" name="nameNews">

                    <label for="exampleFormControlTextarea1"> Введите текст новости:</label>
                    <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="dataNews"></textarea>

                    <button type="submit" class="btn btn-primary"> Создать новость </button>
                </div>
            </form>
    </body>
</html>
