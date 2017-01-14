<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>

<form class="form-horizontal">
    <fieldset>

        <!-- Form Name -->
        <legend>Необходима авторизация</legend>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="buttonLogin">Если у Вас есть аккаунт</label>
            <div class="col-md-4">
                <button id="buttonLogin" name="buttonLogin" value="1" class="btn btn-primary">Войти</button>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="buttonSignUp">Если аккаунт отсутствует</label>
            <div class="col-md-4">
                <button id="buttonSignUp" name="buttonSignUp" value ="2" class="btn btn-primary">Зарегистрироваться</button>
            </div>
        </div>

    </fieldset>
</form>
<%@ include file="include/end-html.jsp" %>
