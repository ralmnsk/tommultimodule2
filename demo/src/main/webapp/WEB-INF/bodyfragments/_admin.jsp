<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container">
            <h2>Страница администратора ${name}</h2>


<!--NEWS LIST-->
            <c:if test = "${pageFlag=='usersNews'}">
                <table>
                    <c:forEach var="entry" items="${map}">
                        <tr><td><p><h4>${entry.key.nameNews}</h4></br>
                                        ${entry.key.dataNews}<p>
                                                                 </br>
                                Автор: ${entry.value.name}
                                                                 </br>
                                Дата: <fmt:formatDate type="both" value="${entry.key.dateNews}"/></br>
                        <c:if test = "${(role == 'ROLE_ADMIN')}">
                            <form name="editNewsForm" method="POST" action="edit">
                                <div class="form=group">
                                    <input type="hidden" name="editNewsId" value="${entry.key.idNews}">
                                    <input class="btn btn-primary" type="submit" value="Редактировать"/>
                                </div>
                            </form>
                        </c:if>
                        </p>
                <hr/>
                    </c:forEach>
                </table>

                <nav aria-label="Page navigation example">
                          <ul autofocus class="pagination justify-content-center">

                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/news?move=previous">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/news?move=next">Next</a></li>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/news?maxResults=5">5</a></li>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/news?maxResults=15">15</a></li>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/news?maxResults=50">50</a></li>
                            <li class="page-item"><a class="page-link">Page: ${currentPage}</a></li>
                            <li class="page-item"><a class="page-link">Total: ${pagesCount}</a></li>

                          </ul>
                </nav>

            </c:if>
<!--EDIT NEWS-->
            <c:if test = "${pageFlag=='newsEdit'}">
                <form name="updateNewsForm" method="POST" action="updatenews">
                    <div class="form-group">
                        <label for="exampleInputText1">Название новости</label>
                        <input type="text" class="form-control" name="nameNews" value="${news.nameNews}">
                        <label for="exampleFormControlTextarea1"> Редактирование текста новости:</label>
                        <input type="hidden" name="editNewsId" value="${news.idNews}">
                        <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="dataNews">${news.dataNews}</textarea>
                        <button type="submit" class="btn btn-primary"> Редактировать новость ${news.user.name} </button>
                         </div>
                </form>
                <form name="deleteNewsForm" method="POST" action="deletenews">
                        <div class="form=group">
                            <input type="hidden" name="editNewsId" value="${news.idNews}">
                            <button type="submit" class="btn btn-primary"> Удалить новость ${news.user.name} </button>
                    </div>
                </form>
            </c:if>
<!--DISCUSSIONS-->
         <c:if test = "${pageFlag=='discussions'}">
            <c:choose>
                <c:when test = "${fn:length(discussionList) > 0}">
                    <c:out value="Дискуссии:"/>
                    </br>
                    </br>
                </c:when>
                <c:otherwise>
                     <c:out value="Для обозрения доступных дискуссий пройдите в  Коментарии пользователей"/>
                     <br/>
                     <br/>
                </c:otherwise>
            </c:choose>

            <c:forEach var="item" items="${discussionList}">
                <a href="${pageContext.request.contextPath}/site/inform/admin/discuss?discussNewsId=${item.news.idNews}">${item.news.nameNews}    <b>${item.news.user.name}:<fmt:formatDate type="both" value="${item.news.dateNews}"/></b></a>
                <hr/>
            </c:forEach>

            <nav aria-label="Page navigation example" >
                        <ul autofocus class="pagination justify-content-center" style="vertical-align: bottom;">
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/comment?move=previous">Previous</a></li>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/comment?move=next">Next</a></li>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/comment?maxResults=5">5</a></li>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/comment?maxResults=15">15</a></li>
                            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/comment?maxResults=50">50</a></li>
                            <li class="page-item"><a class="page-link">Page: ${currentPage}</a></li>
                            <li class="page-item"><a class="page-link">Total: ${pagesCount}</a></li>
                        </ul>
            </nav>

        </c:if>
<!--CONTACT-->
        <c:if test = "${pageFlag=='contact'}">
        <h4>Контакты пользователей</h4>
        <c:forEach var="entry" items="${mapContacts}">
                        <p>Пользователь: ${entry.key}  mail: ${entry.value}
        </c:forEach>
          <nav aria-label="Page navigation example" >
            <ul autofocus class="pagination justify-content-center" style="vertical-align: bottom;">
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/contact?move=previous">Previous</a></li>
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/contact?move=next">Next</a></li>
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/contact?maxResults=5">5</a></li>
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/contact?maxResults=15">15</a></li>
                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/site/inform/admin/contact?maxResults=50">50</a></li>
                <li class="page-item"><a class="page-link">Page: ${currentPage}</a></li>
                <li class="page-item"><a class="page-link">Total: ${pagesCount}</a></li>
            </ul>
          </nav>
        </c:if>

</div>
