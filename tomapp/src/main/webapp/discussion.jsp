<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html>
    <head><title>Discussion</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            p{margin-left: 20px; margin-right: 20px;}
        </style>
    </head>
    <body>
        <div class="container">
            <h2>Обсуждение новости:</br>
             </h2>
            </br>
            </br>
             <a href="/tomapp/">Главная</a>
            </br>
            <a href="/tomapp/topsecret">Страница администратора</a>
            </br>
            <a href="/tomapp/topsecret2">Страница администратора 2</a>
            </br>
            <a href="/tomapp/site/inform">Страница пользователя</a>
            </br>
            <a href="/tomapp/site/logout">Logout</a>
            <hr/>
                <p>${news.nameNews}
            </br>
            <hr/>
                <p>${news.dataNews}
            </br>
                <p>Автор:${news.user.name}   Дата:${news.dateNews}
            </br>
            <hr/>
                <p> <H3> Обсуждение:</H3>

                <c:forEach var="entry" items="${mapMsgUsr}">
                   </br>
                   Автор: <c:out value="${entry.value.name}"></c:out>
                   <p><h4><c:out value="${entry.key.text}"></c:out></h4></br>

                   Написал: <c:out value="${entry.key.date}"></c:out></br>
                   <hr/>
                </c:forEach>


        </div>
            <form name="sendMsgForm" method="POST" action="msg">
                <div class="form-group">
                    <p><label for="exampleFormControlTextarea1">Сообщение:</label>
                    <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="msgText">Какое-то сообщение от пользователя</textarea>
                    <button autofocus type="submit" class="btn btn-primary"> Отправить сообщение </button>
                </div>
            </form>
    </body>
</html>
