<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
</head>

<div>
<form class="form-horizontal">
    <fieldset>
        <!-- Form Name -->
        <legend>Профиль пользователя: ${user.login} </legend>
        <!-- TABLE-->
        <table width="100%" border="1" cellpadding="1" cellspacing="1">
            <tr>
                <td align="center"><b>ID</b></td>
                <td align="center"><b>Subject</b></td>
                <td align="center"><b>Description</b></td>
                <td align="center"><b>ЗИА</b></td>
                <td align="center"><b>Статус</b></td>
                <td align="center"><b>Пользователь</b></td>
                <td align="center"><b>Приоритет</b></td>

            </tr>
            <c:forEach items="${adsList}" var="note">
            <tr>
                <td align="center"> ${note.id}</td>
                <td align="left">${note.subject}</td>
                <td align="left">${note.description}</td>
                <td align="center">${note.fk_zia}</td>
                <td align="center">${note.fk_status}</td>
                <td align="center">${note.fk_user}</td>
                <td align="center">${note.fk_priority}</td>
            </tr>
            </c:forEach>

    </fieldset>

</form>
</div>
<%@ include file="include/end-html.jsp" %>






