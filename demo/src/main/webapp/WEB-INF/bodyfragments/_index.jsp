<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container">
        <h2>Главная станица</h2>

            <table>
                <c:forEach var="entry" items="${map}">
                    <tr><td><p><h4>${entry.key.nameNews}</h4>
                        </br>
                            ${entry.key.dataNews}<p>
                        </br>
                            Автор: ${entry.value.name}
                        </br>
                    Дата: <fmt:formatDate type="both" value="${entry.key.dateNews}"/></br>

                    <c:if test = "${(user.role == 'ROLE_USER')or(user.role == 'ROLE_ADMIN')}">
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

            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/news?move=previous">Previous</a></li>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/news?move=next">Next</a></li>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/news?maxResults=5">5</a></li>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/news?maxResults=15">15</a></li>
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/news?maxResults=50">50</a></li>
            <li class="page-item"><a class="page-link">Page: ${currentPage}</a></li>
            <li class="page-item"><a class="page-link">Total: ${pagesCount}</a></li>

          </ul>
        </nav>