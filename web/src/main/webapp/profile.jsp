<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<legend>Профиль пользователя: ${user.login}</legend>
<fieldset>
    <h4>Открытые Вами заявки</h4>
    <div class="row">
        <div class="col-sm-1">${ads==null?
                " "
                :
                "<b>№</b>"
                }</div>
        <div class="col-sm-2">${ads==null?
                " "
                :
                "<b>Тема</b>"
                }</div>
        <div class="col-sm-3">${ads==null?
                "У вас нет открытых заявок "
                :
                "<b>Описание</b>"
                }</div>
        <div class="col-sm-2">${ads==null?
                " "
                :
                "<b>ЗИА</b>"
                }</div>
        <div class="col-sm-1">${ads==null?
                " "
                :
                "<b>Приоритет</b>"
                }</div>
        <div class="col-sm-2">${ads==null?
                " "
                :
                "<b>Статус</b>"
                }</div>
    </div>
    <br>

    <c:forEach items="${ads}" var="ad">
        <div class="row">
            <form class="form-horizontal" action="do?command=PROFILE" method="POST">
                <fieldset>

                    <label for="ad_ID-${ad.id}"></label>
                    <div class="col-sm-1">
                        <input id="ad_ID-${ad.id}" name="ID" type="text" value="${ad.id}"
                               class="form-control input-sm">
                    </div>
                    <label for="ad_subject-${ad.subject}"></label>
                    <div class="col-sm-2">
                        <input id="ad_subject-${ad.subject}" name="subject" type="text" value="${ad.subject}"
                               class="form-control input-sm">
                    </div>
                    <label for="description-${ad.subject}"></label>
                    <div class="col-sm-3">
                        <input id="description-${ad.subject}" name="description" type="text" value="${ad.description}"
                               class="form-control input-sm">

                    </div>
                    <div class="col-sm-2"><select id="ad_fk_zia-" name="fk_zia" class="form-control input-sm">
                        <c:forEach items="${objectses}" var="zia">
                            <option value="${zia.ID}"
                                    status=${zia.ID} ${zia.ID==ad.fk_zia?"selected":""}>
                                    ${zia.zia}
                            </option>
                        </c:forEach>
                    </select></div>
                    <div class="col-sm-1">
                        <select id="ad_fk_priority-" name="fk_priority" class="form-control input-sm">
                            <c:forEach items="${priorities}" var="priority">
                                <option value="${priority.ID}"
                                        status=${priority.ID} ${priority.ID==ad.fk_priority?"selected":""}>
                                        ${priority.priorityName}
                                </option>
                            </c:forEach>
                        </select></div>
                    <div class="col-sm-1">
                        <select id="statuses" name="fk_status" class="form-control input-sm">
                            <c:forEach items="${statuses}" var="status">
                                <option value="${status.ID}"
                                        status=${status.ID} ${status.ID==ad.fk_status?"selected":""}>
                                        ${status.statusName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Button -->
                    <div class="col-sm-1">
                        <button id="update" name="update" class="btn btn-primary btn-sm">Update</button>
                    </div>
                </fieldset>
            </form>
        </div>
    </c:forEach>

</fieldset>
</body>

<%@ include file="include/end-html.jsp" %>

