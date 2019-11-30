<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

        <div class="container">
            <h2>Редактирование новости ${user.name}</h2>
            </br>
            <form name="updateNewsForm" method="POST" action="updatenews">
                <div class="form-group">
                    <label for="exampleInputText1">Название новости</label>
                    <input type="text" class="form-control" name="nameNews" value="${news.nameNews}">
                    <input type="hidden" name="editNewsId" value="${news.idNews}">
                    <label for="exampleFormControlTextarea1"> Редактирование текста новости:</label>
                    <textarea type="text" aria-label="With textarea" class="form-control" rows="5" name="dataNews">${news.dataNews}</textarea>

                    <button type="submit" class="btn btn-primary"> Редактировать новость </button>
                </div>
            </form>
            <form name="deleteNewsForm" method="POST" action="deletenews">
            <div class="form=group">
                <input type="hidden" name="editNewsId" value="${news.idNews}">
                <button type="submit" class="btn btn-primary"> Удалить новость ${name} </button>
            </div>
            </form>
        </div>

