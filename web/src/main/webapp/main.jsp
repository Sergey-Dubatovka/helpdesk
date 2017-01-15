<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Основное содержимое -->
<head>
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="page-header">
    <h3>Система заявок</h3><br>
        <h4>Статистика</h4>
    <br>
    <h5>Всего заявок в системе:${allAds}</h5>

    <h5>
       Открытые заявки:
        ${openAds}</h5>
    <h5>
        Из них выполняются:
        ${workingAds}</h5>
    <h5>
        Всего заявок выполнено:
        ${closedAds}</h5>

    <br><br>
    <h5>
        Всего пользователей в системе:
        ${countOfUsers}</h5>
    <h5>
        Менеджеров:
        ${countOfManagers}</h5>
    <h5>
        Исполнителей:
        ${countOfWorkers}</h5>
    <h5>
        Директор: ${director}
        </h5>



</div>
</body>

<%@ include file="include/end-html.jsp" %>


