<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div class="container">
            <h2>Добавление новости ${user.name}</h2>

            </br>
            <form name="createNewsForm" method="POST" action="createnews">
                <div class="form-group">
                    <label for="exampleInputText1">Название новости</label>
                    <input type="text" class="form-control" name="nameNews">

                    <label for="exampleFormControlTextarea1"> Введите текст новости:</label>
                    <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="dataNews"></textarea>

                    <button type="submit" class="btn btn-primary"> Создать новость </button>
                </div>
            </form>
        </div>

