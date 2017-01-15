<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<p>Все заявки. </p>
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
    <c:forEach items="${adsList}" var="ad">
        <tr>
            <td align="center">${ad.id}</td>
            <td align="left">${ad.subject}</td>
            <td align="left">${ad.description}</td>
            <td align="left">
                <c:forEach items="${objectses}" var="zia">
                    <c:if test="${ad.fk_zia==zia.ID}">${zia.zia}</c:if>
                </c:forEach></td>

            <td align="center"><c:forEach items="${statuses}" var="status">
                <c:if test="${ad.fk_status==status.ID}">${status.statusName}</c:if>
            </c:forEach></td>

            <td align="center">
                <c:forEach items="${users}" var="user">
                    <c:if test="${ad.fk_user==user.ID}">${user.login}</c:if>
                </c:forEach></td>
            <td align="center"><c:forEach items="${priorities}" var="pri">
                <c:if test="${ad.fk_priority==pri.ID}">${pri.priorityName}</c:if>
            </c:forEach></td>
        </tr>
    </c:forEach>
</table>
</body>
<%@ include file="include/end-html.jsp" %>


