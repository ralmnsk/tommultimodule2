<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
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
                <input type="hidden" name="discussNewsId" value="${discussNewsId}">
                <c:forEach var="entry" items="${mapMsgUsr}">
                   </br>
                   Автор: <c:out value="${entry.value.name}"></c:out>
                   <p><h4><c:out value="${entry.key.text}"></c:out></h4></br>

                   Написал: <c:out value="${entry.key.date}"></c:out></br>
                   <hr/>
                </c:forEach>



            <form name="sendMsgForm" method="POST" action="msg">
                <div class="form-group">
                    <p><label for="exampleFormControlTextarea1">Сообщение:</label>
                    <input type="hidden" name="discussNewsId" value="${discussNewsId}">
                    <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="msgText">Ваше сообщение</textarea>
                    <button autofocus type="submit" class="btn btn-primary"> Отправить сообщение </button>
                </div>
           </form>
        </div>

