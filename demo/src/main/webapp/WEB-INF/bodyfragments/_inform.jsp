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
            <nav aria-label="Page navigation example">
                      <ul autofocus class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/comment?move=previous">Previous</a></li>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/comment?move=next">Next</a></li>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/comment?maxResults=5">5</a></li>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/comment?maxResults=15">15</a></li>
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/comment?maxResults=50">50</a></li>
                        <li class="page-item"><a class="page-link">Page: ${currentPage}</a></li>
                        <li class="page-item"><a class="page-link">Total: ${pagesCount}</a></li>
                      </ul>
            </nav>
    </body>
</html>
