<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
    <head><title>INFORMATION</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h2>Страница пользователя ${name}</h2>

            <c:choose>
                <c:when test = "${fn:length(discussionList) > 0}">
                    <c:out value="Мои Дискуссии:"/>
                    </br>
                    </br>
                </c:when>
                <c:otherwise>
                     <c:out value="Для обозрения доступных дискусси пройдите в  Мои коментарии"/>
                     <br/>
                     <br/>
                </c:otherwise>
            </c:choose>

            <c:forEach var="item" items="${discussionList}">
                <a href="${pageContext.request.contextPath}/site/discuss?discussNewsId=${item.news.idNews}">${item.news.nameNews}</a>
                <hr/>
            </c:forEach>

        </div>
    </body>
</html>
