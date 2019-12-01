<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

        <div class="container">
            <h2>Обсуждение новости:</br>

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
                <!--<p><h4><c:out value="${entry.key.text}"></c:out></h4>-->
                   Написал: <fmt:formatDate type="both" value="${entry.key.date}"/>

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

