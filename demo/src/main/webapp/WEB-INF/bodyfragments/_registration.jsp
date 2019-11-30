<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

        <div class="container">
            <h2>Пожалуйста зарегистрируйтесь:</h2>
                <div class="form-group">
                    <form name="registrationForm" method="POST" action="registration">

                    Логин:<br/>
                    <input type="text" name="login" value=""/>
                    <br/>Пароль:<br/>
                    <input type="password" name="password" value=""/>
                    <br/>

                    <input type="submit" value="Регистрация"/>
                    </form>
                </div>
            <hr/>
            </br>
            ${errorRegistrationMessage}
            </br>

        </div>

