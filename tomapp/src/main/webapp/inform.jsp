<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head><title>INFORMATION</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h2>Страница пользователя ${user.name}</h2>

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

            <c:forEach var="entry" items="${discussionNewsMap}">
                <p><h4>${entry.key.id}</h4></br>
                ${entry.value.nameNews}<p>
                </br>
                <hr/>
            </c:forEach>

        </div>
    </body>
</html>
