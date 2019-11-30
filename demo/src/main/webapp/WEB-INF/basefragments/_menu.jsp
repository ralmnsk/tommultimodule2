<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container">

   <ul>
       <c:if test = "${(role == 'ROLE_USER')}">
           <li>Пользователь ${name}</li>
           <p>
       </c:if>
       <c:if test = "${(role == 'ROLE_ADMIN')}">
           <li>Администратор ${name}</li>
           <p>
       </c:if>

           <li><a href="${pageContext.request.contextPath}/">Главная</a></li>

       <c:if test = "${(role ne 'ROLE_USER')and(role ne 'ROLE_ADMIN')}">
           <li><a href="${pageContext.request.contextPath}/login">Вход</a></li>
           <li><a href="${pageContext.request.contextPath}/goregistrate">Регистрация</a></li>
       </c:if>

       <c:if test = "${(role == 'ROLE_USER')or(role == 'ROLE_ADMIN')}">
            <li><a href="${pageContext.request.contextPath}/site/inform">Страница пользователя</a></li>
            <li><a href="${pageContext.request.contextPath}/site/logout">Logout</a></li>
            <p>
       </c:if>

       <c:if test = "${(role == 'ROLE_USER')or(role == 'ROLE_ADMIN')}">
           <li><a href="${pageContext.request.contextPath}/site/addnews">Добавить новость</a></li>
           <li><a href="${pageContext.request.contextPath}/site/mynews">Мои новости</a></li>
           <li><a href="${pageContext.request.contextPath}/site/contact">Мой контакт</a></li>
           <li><a href="${pageContext.request.contextPath}/site/comment">Мои коментарии</a></li>
           <p>
       </c:if>
       <c:if test = "${(role == 'ROLE_ADMIN')}">
           <li><a href="${pageContext.request.contextPath}/site/inform/admin">Страница администратора</a></li>
           <li><a href="${pageContext.request.contextPath}/site/inform/admin/news">Hовости пользователей</a></li>
           <li><a href="${pageContext.request.contextPath}/site/inform/admin/contact">Контакты пользователей</a></li>
           <li><a href="${pageContext.request.contextPath}/site/inform/admin/comment">Коментарии пользователей</a></li>
           <p>
       </c:if>
   </ul>

</div>