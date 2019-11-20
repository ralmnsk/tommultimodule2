<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head><title>CONTACT</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <h2>Контакт пользователя ${user.name}</h2>

            </br>
            </br>
             <a href="${pageContext.request.contextPath}/">Главная</a>
            </br>
            <c:if test = "${(user.role == 'ROLE_ADMIN')}">
                                <a href="${pageContext.request.contextPath}/topsecret">Страница администратора</a>
                                </br>
                                <a href="${pageContext.request.contextPath}/topsecret2">Страница администратора 2</a>
                                </br>
                            </c:if>
            <a href="${pageContext.request.contextPath}/site/logout">Logout</a>
            <hr/>
            <a href="${pageContext.request.contextPath}/site/addnews">Добавить новость</a>
            </br>
            <a href="${pageContext.request.contextPath}/site/mynews">Мои новости</a>
            </br>
            <a href="${pageContext.request.contextPath}/site/contact">Мой контакт</a>
            <p>
            </br>
        <form name="updateContactForm" method="POST" action="gocontact">
            <div class="form-group">
                <label for="exampleInputText1">Почта ${user.name}:</label>
                <input style="width: 300px;" type="text" class="form-control" name="mail" value="${contact.mail}">
                <button type="submit" class="btn btn-primary"> Изменить </button>
            </div>
        </form>
        <form name="deleteContactForm" method="POST" action="delcontact">
             <div class="form-group">
                 <button type="submit" class="btn btn-primary"> Удалить </button>
             </div>
        </form>
        </div>
    </body>
</html>
