<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div>

   <ul class="my">
          <c:if test = "${(role == 'ROLE_USER')}">
               <p>
              <li class="my">Пользователь ${name}<img width="25" height="30" src="${pageContext.request.contextPath}/images/user.jfif"/></li>

          </c:if>
          <c:if test = "${(role == 'ROLE_ADMIN')}">
            <p>
              <li class="my">Администратор ${name}<img width="30" height="25" src="${pageContext.request.contextPath}/images/admin.jpg"/></li>

          </c:if>

           <li class="my"><a href="?lang=en">English</a></li>
           <li class="my"><a href="?lang=ru">Русский</a></li>



           <li class="my"><a href="${pageContext.request.contextPath}/"><spring:message code="message.main"/></a></li>

       <c:if test = "${(role ne 'ROLE_USER')and(role ne 'ROLE_ADMIN')}">
           <li class="my"><a href="${pageContext.request.contextPath}/login"><spring:message code="message.enter"/></a></li>
           <li class="my"><a href="${pageContext.request.contextPath}/goregistrate"><spring:message code="message.registration"/></a></li>
       </c:if>

       <c:if test = "${(role == 'ROLE_USER')or(role == 'ROLE_ADMIN')}">
            <li class="my"><a href="${pageContext.request.contextPath}/site/inform"><spring:message code="message.user.page"/></a></li>
            <li class="my"><a href="${pageContext.request.contextPath}/site/logout"><spring:message code="message.exit"/></a></li>

       </c:if>

       <c:if test = "${(role == 'ROLE_USER')or(role == 'ROLE_ADMIN')}">
           <li class="my"><a href="${pageContext.request.contextPath}/site/addnews"><spring:message code="message.add.news"/></a></li>
           <li class="my"><a href="${pageContext.request.contextPath}/site/mynews"><spring:message code="message.my.news"/></a></li>
           <li class="my"><a href="${pageContext.request.contextPath}/site/contact"><spring:message code="message.my.contact"/></a></li>
           <li class="my"><a href="${pageContext.request.contextPath}/site/comment"><spring:message code="message.my.comments"/></a></li>

       </c:if>
       <c:if test = "${(role == 'ROLE_ADMIN')}">
           <li class="my"><a href="${pageContext.request.contextPath}/site/inform/admin"><spring:message code="message.admin.page"/></a></li>
           <li class="my"><a href="${pageContext.request.contextPath}/site/inform/admin/news"><spring:message code="message.users.news"/></a></li>
           <li class="my"><a href="${pageContext.request.contextPath}/site/inform/admin/contact"><spring:message code="message.users.contacts"/></a></li>
           <li class="my"><a href="${pageContext.request.contextPath}/site/inform/admin/comment"><spring:message code="message.comments"/></a></li>

       </c:if>
   </ul>

</div>