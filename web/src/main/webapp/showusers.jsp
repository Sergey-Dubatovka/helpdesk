<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row">
    <b>
        <div class=col-sm-1>${setOfUsers==null?                " "                :                "ID"                }</div>
        <div class=col-sm-2>${setOfUsers==null?
                " "
                :
                "Имя"
                }</div>
        <div class=col-sm-2>${setOfUsers==null?
                " "
                :
                "Пароль"
                }</div>
        <div class=col-sm-1>${setOfUsers==null?
                " "
                :
                "Email"
                }</div>
        <div class=col-sm-2>${setOfUsers==null?
                " "
                :
                "Должность"
                }</div>
    </b>
</div>
<br>
<!-- Коллекцию listUser мы получаем по get из команды сервлета UserForm -->
<c:forEach items="${setOfUsers}" var="user">
    <div class="row">
        <form class="form-user-${user.userId}" action="do?command=ShowUsers" method=POST>
            <div class=col-sm-1>
                <input id="user_id_${user.userId}" name="userId" type="text"
                       value="${user.userId}" class="form-control input-sm">
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
                <select id="role" name="userRole" class="form-control input-sm">
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.roleName}" role=${role.roleId} ${role.roleId==user.userRole.roleId?"selected":""}>
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
                        onclick="document.getElementById('user_id_${user.userId}').value=-document.getElementById('user_id_${user.userId}').value;"
                >
                    Удалить
                </button>
            </div>

        </form>
    </div>
    <br>
</c:forEach>
<table width="50%" border="1" cellpadding="2" cellspacing="2" align="center">
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
                <c:if test="${user.userRole.roleId==role.roleId}">${role.roleName}</c:if>
            </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>

<%@ include file="include/end-html.jsp" %>
