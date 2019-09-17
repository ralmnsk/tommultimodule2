<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
    <head>
        <title>
        Welcome page
        </title>

        <meta charset="utf-8">
    </head>
    <body>
        <h2>Welcome</h2>
        Здраствуйте,
        <c:if test="${user.role == 'usr'}">
                        <c:out value="пользователь"/>
                    </с:if>

        <c:if test="${user.role == 'admin'}">
                        <c:out value="администратор"/>
                    </с:if>

        <c:out value="${user.name}"/>
        </br>
        <a href="/tomapp/site/inform">Information</a>
        </br>
        <a href="/tomapp/site/logout">Logout</a>
    </body>
</html>