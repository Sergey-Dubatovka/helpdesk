<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="include/begin-html.jsp" %>
<head>
    <link href="css/mystyle.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form class="form-horizontal" action="do?command=LOGOUT" method="POST">

   <fieldset>

        <!-- Form Name -->
        <legend>Завершение сеанса</legend>


        <!-- Button -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="LogoutButton"></label>
            <div class="col-md-4">
                <button id="LogoutButton" name="LogoutButton" value="1" class="btn btn-success">Завершить сеанс</button>
            </div>
        </div>

    </fieldset>
</form>
</body>
<%@ include file="include/end-html.jsp" %>
