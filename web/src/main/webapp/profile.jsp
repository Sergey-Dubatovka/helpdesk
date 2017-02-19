<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<legend>Профиль пользователя: ${loggedUser.login} / ${loggedUser.userRole.roleName}  </legend>

<fieldset>
    <h4>Открытые Вами заявки</h4>
    <div class="row">
        <div class="col-sm-1">${notes==null?
                " "
                :
                "<b>№</b>"
                }</div>
        <div class="col-sm-2">${notes==null?
                " "
                :
                "<b>Тема</b>"
                }</div>
        <div class="col-sm-3">${notes==null?
                "У вас нет открытых заявок "
                :
                "<b>Описание</b>"
                }</div>
        <div class="col-sm-2">${notes==null?
                " "
                :
                "<b>ЗИА</b>"
                }</div>
        <div class="col-sm-1">${notes==null?
                " "
                :
                "<b>Приоритет</b>"
                }</div>
        <div class="col-sm-2">${notes==null?
                " "
                :
                "<b>Статус</b>"
                }</div>
    </div>
    <br>

    <c:forEach items="${notes}" var="note">
        <div class="row">
            <form class="form-horizontal" action="do?command=PROFILE" method="POST">
                <fieldset>

                    <label for="note_noteId-${note.noteId}"></label>
                    <div class="col-sm-1">
                        <input id="note_noteId-${note.noteId}" name="ID" type="text" value="${note.noteId}"
                               class="form-control input-sm">
                    </div>
                    <label for="note_subject-${note.subject}"></label>
                    <div class="col-sm-2">
                        <input id="note_subject-${note.subject}" name="subject" type="text" value="${note.subject}"
                               class="form-control input-sm">
                    </div>
                    <label for="description-${note.description}"></label>
                    <div class="col-sm-3">
                        <input id="description-${note.description}" name="description" type="text"
                               value="${note.description}"
                               class="form-control input-sm">

                    </div>

                    <div class="col-sm-2"><select id="note_gamingClub" name="gamingClub" class="form-control input-sm">
                        <c:forEach var="gamingClub" items="${gamingClubs}">
                            <option value="${gamingClub.gamingClubName}"
                                    status=${gamingClub} ${gamingClub.gamingClubName==note.gamingClub.gamingClubName?"selected":""}>
                                    ${gamingClub.gamingClubName}
                            </option>
                        </c:forEach>

                    </select></div>
                    <div class="col-sm-1">
                        <select id="note_priority" name="notePriority" class="form-control input-sm">
                            <c:forEach items="${priorities}" var="notePriority">
                                <option value="${notePriority.priorityName}"
                                        status=${notePriority.priorityName} ${notePriority.priorityName==note.notePriority.priorityName?"selected":""}>
                                        ${notePriority.priorityName}
                                </option>
                            </c:forEach>
                        </select></div>
                    <div class="col-sm-1">
                        <select id="note_status" name="noteStatus" class="form-control input-sm">
                            <c:forEach items="${statuses}" var="noteStatus">
                                <option value="${noteStatus.statusName}"
                                        status=${noteStatus.statusName} ${noteStatus.statusName==note.noteStatus.statusName?"selected":""}>
                                        ${noteStatus.statusName}
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


<%@ include file="include/end-html.jsp" %>

