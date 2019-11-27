<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
             <a href="${pageContext.request.contextPath}/">Главная</a>
            </br>

            <a href="${pageContext.request.contextPath}/site/inform">Страница пользователя</a>
            </br>
            <a href="${pageContext.request.contextPath}/site/logout">Logout</a>
            <hr/>
                <h5>Название новости:</h5> ${news.nameNews}
            </br>
            <hr/>
                <h5>Текст новости:</h5><p>${news.dataNews}
            </br>
                <p>Автор:${news.user.name}   Дата:${news.dateNews}
            </br>
            <hr/>
                <p> <H3> Обсуждение:</H3>

                <c:forEach var="entry" items="${mapMsgUsr}">
                   </br>
                   Автор: <c:out value="${entry.value.name}"></c:out>
                <!-- <p><h4><c:out value="${entry.key.text}"></c:out></h4></br> -->
                   Написал: <c:out value="${entry.key.date}"></c:out></br>

                   <hr/>

                <form name="sendMsgForm" method="POST" action="msgedit">
                    <div class="form-group">
                        <p><label for="exampleFormControlTextarea1">Сообщение:</label>
                        <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="msgText">${entry.key.text}</textarea>
                        <input type="hidden" name="editMsgId" value="${entry.key.id}">
                        <input type="hidden" name="discussNewsId" value="${entry.key.news.idNews}">
                            <button autofocus type="submit" name="msgupdate" class="btn btn-primary"> Редактировать сообщение </button>
                            <button autofocus type="submit" name="msgdelete" class="btn btn-primary"> Удалить сообщение </button>
                    </div>
                </form>

                </c:forEach>
        </div>
    </body>
</html>
