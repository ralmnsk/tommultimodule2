<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div class="container">
            <h2>Контакт пользователя ${user.name}</h2>

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

