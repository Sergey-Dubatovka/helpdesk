<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="row">
    <b>
        <div class=col-sm-1>${users==null?                " "                :                "ID"                }</div>
        <div class=col-sm-2>${users==null?
                " "
                :
                "Имя"
                }</div>
        <div class=col-sm-2>${users==null?
                " "
                :
                "Пароль"
                }</div>
        <div class=col-sm-2>${users==null?
                " "
                :
                "Email"
                }</div>
        <div class=col-sm-2>${users==null?
                " "
                :
                "Должность"
                }</div>
    </b>
</div>
<br>
<!-- Коллекцию listUser мы получаем по get из команды сервлета UserForm -->
<c:forEach items="${users}" var="user">
    <div class="row">
        <form class="form-user-${user.ID}" action="do?command=ShowUsers" method=POST>
            <div class=col-sm-1>
                <input id="user_id_${user.ID}" name="ID" type="text"
                       value="${user.ID}" class="form-control input-sm">
            </div>
            <div class=col-sm-2>
                <input id="textinput" name="Login" type="text"
                       value="${user.login}" class="form-control input-sm">
            </div>
            <div class=col-sm-2>
                <input id="textinput1" name="Password" type="text"
                       value="${user.password}" class="form-control input-sm">
            </div>
            <div class=col-sm-2>
                <input id="textinput2" name="Email" type="text"
                       value="${user.email}" class="form-control input-sm">
            </div>

            <div class=col-sm-2>
                <select id="role" name="fk_Role" class="form-control input-sm">
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.ID}" role=${role.ID} ${role.ID==user.fk_role?"selected":""}>
                                ${role.roleName}
                        </option>
                    </c:forEach>

                </select>
            </div>

            <div class=col-sm-1>
                <button id="singlebutton1" name="singlebutton1" class="btn btn-success btn-sm">
                    Обновить
                </button>
            </div>

            <div class=col>
                <button id="singlebutton2" name="singlebutton2"
                        class="btn btn-danger btn-sm"
                        onclick="document.getElementById('user_id_${user.ID}').value=-document.getElementById('user_id_${user.ID}').value;"
                >
                    Удалить
                </button>
            </div>

        </form>
    </div>
    <br>
</c:forEach>
<table width="50%" border="1" cellpadding="2" cellspacing="2" >
    <tr>
        <td align='left'>${userses==null?
                " "
                :
                "<b>Login</b>"
                }

        </td>
        <td align="left">${userses==null?
                " "
                :
                "<b>E-mail</b>"
                }</td>
        <td align="center">${userses==null?
                " "
                :
                "<b>Role</b>"
                } </td>
    </tr>
    <c:forEach items="${userses}" var="user">
        <tr>
            <td align="left"> ${user.login}</td>
            <td align="left">${user.email}</td>
            <td align="center"><c:forEach items="${roles}" var="role">
                <c:if test="${user.fk_role==role.ID}">${role.roleName}</c:if>
            </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
<%@ include file="include/end-html.jsp" %>
