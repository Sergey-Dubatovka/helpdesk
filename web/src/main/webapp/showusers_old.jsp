<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<p>Список пользователей. </p>
<table width="75%" border="1" cellpadding="1" cellspacing="1">
    <tr>
        <td align="center"><b>Login</b></td>
        <td align="center"><b>E-mail</b></td>
        <td align="center"><b>Role</b></td>
    </tr>
    <c:forEach items="${userses}" var="user">
        <tr>
            <td align="left"> ${user.login}</td>
            <td align="left">${user.email}</td>
            <td align="center">${user.fk_role}</td>
        </tr>
    </c:forEach>
</table>
<br>
<form class="form-horizontal" action="do?command=SHOWUSERS" method="POST">

    <fieldset>

        <!-- Form Name -->
        <legend>Завершение сеанса</legend>


        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="LogoutButton"></label>
            <div class="col-md-4">
                <button id="LogoutButton" name="LogoutButton" value="1" class="btn btn-success">Редактировать пользователей</button>
            </div>
        </div>

    </fieldset>
</form>
</body>

<%@ include file="include/end-html.jsp" %>


