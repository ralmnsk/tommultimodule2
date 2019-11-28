<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

        <div class="container">
            <h2>Пожалуйста зарегистрируйтесь:</h2>
            <form name="registrationForm" method="POST" action="registration">

            Логин:<br/>
            <input type="text" name="login" value=""/>
            <br/>Пароль:<br/>
            <input type="password" name="password" value=""/>
            <br/>

            <input type="submit" value="Регистрация"/>
            </form>
            <hr/>
            </br>
            ${errorRegistrationMessage}
            </br>
            <a href="${pageContext.request.contextPath}/">Главная</a>
        </div>

