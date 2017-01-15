<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>
<head>
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
</head>

<div>
<form class="form-horizontal" action="do?command=CREATEAD" method="post">
    <fieldset>

        <!-- Form Name -->
        <legend>Create new task</legend>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="subject">Тема</label>
            <div class="col-md-4">
                <input id="subject" name="subject" type="text" placeholder="Тема" class="form-control input-md"
                       required="">

            </div>
        </div>

        <!-- Textarea -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="description">Описание</label>
            <div class="col-md-4">
                <textarea class="form-control" id="description" name="description">Описание вашей заявки</textarea>
            </div>
        </div>

        <!-- Select Basic -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="objects">ЗИА</label>
            <div class="col-md-4">
                <select id="objects" name="objects" class="form-control">
                    <c:forEach var="objects" items="${objects}">
                        <option value="${objects.ID}">${objects.zia}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <label class="col-md-3 control-label" for=""></label>
            <div class="col-md-3">
                <button id="" name="addAd" class="btn btn-primary">Добавить заявку</button>
            </div>
        </div>
    </fieldset>
</form>
</div>
<%@ include file="include/end-html.jsp" %>
