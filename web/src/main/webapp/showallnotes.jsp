<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
    <style>
        table {
            font-size: x-small;
        }
    </style>
</head>
<div>
    <p>Все заявки. </p>
    <table width="90%" border="1" cellpadding="1" cellspacing="1" align="center">
        <tr>
            <td align="center"><b>№</b></td>
            <td align="center"><b>Subject</b></td>
            <td align="center"><b>Description</b></td>
            <td align="center"><b>ЗИА</b></td>
            <td align="center"><b>User</b></td>
            <td align="center"><b>Дата</b></td>
            <td align="center"><b>Статус</b></td>
            <td align="center"><b>Приоритет</b></td>


        </tr>
        <c:forEach items="${openNotes}" var="note">
            <tr>
                <td align="center">${note.noteId}</td>
                <td align="left">${note.subject}</td>
                <td align="left">${note.description}</td>
                <td align="center">${note.gamingClub.gamingClubName}</td>
                <td align="center"> ${note.user.login}</td>
                <td align="center">${note.date}</td>
                <td align="center">${note.noteStatus.statusName}</td>
                <td align="center">${note.notePriority.priorityName}</td>
            </tr>
        </c:forEach>
    </table>

    <div class="col-md-2">
        <ul class="pagination">
            <c:forEach var="i" begin="1" end = "${pageCount}">
                <li
                        <c:if test="${i == activePageIndex}">
                            class="active"
                        </c:if>

                ><a href="do?command=showallnotes&activePageIndex=${i}">${i}</a></li>
            </c:forEach>
        </ul>
    </div>

</div>
<%@ include file="include/end-html.jsp" %>


