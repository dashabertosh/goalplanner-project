<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" uri="/WEB-INF/tld/footertag" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <meta charset="UTF-8">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <title><fmt:message key="goal.create.title"/></title>

    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <!--Custom styles-->
    <link rel="stylesheet" type="text/css" href="css/createGoalCss.css">
    <style>
        .lang-change{
            background: #FFC312; /* Цвет фона */
            border: 1px solid #7a7b7e; /* Параметры рамки */
            width: 30px; /* Ширина кнопки */
            height: 30px; /* Высота */
            align-content: center;
            border-radius: 30px;}
    </style>
</head>
<body>
<div class="container">
    <%@ include file="include/menu.jsp" %>
    <form class="form-horizontal" action="do?command=createGoal" method="POST">
        <input type="hidden" name="command" value="language"/>
        <fieldset>
            <div class="container">
                <div class="d-flex justify-content-center h-100">
                    <div class="card">
                        <div class="card-header">
                            <h3><fmt:message key="goal.create.title"/></h3>
                        </div>
                        <div class="card-body">
                            <form>
                                <label class="input" for="name_goal"><fmt:message key="goal.create.name"/></label>
                                <input id="name_goal" name="name_goal" type="text" class="form-control" placeholder="<fmt:message key="goal.create.field.name"/>">
                                <br>
                                <label class="input" for="description"><fmt:message
                                        key="goal.create.description"/></label>
                                <textarea id="description" name="description" class="form-control"
                                          placeholder="<fmt:message key="goal.create.field.description"/>"></textarea>
                                <br>
                                <label class="input" for="begin_date"><fmt:message key="goal.create.beginDate"/></label>
                                <input id="begin_date" name="begin_date" type="date" class="form-control"
                                       placeholder="end_date">
                                <br>
                                <label class="input" for="end_date"><fmt:message key="goal.create.endDate"/></label>
                                <input id="end_date" name="end_date" type="date" class="form-control"
                                       placeholder="end_date">
                                <br>
                                <label class="input" for="type_name"><fmt:message key="goal.create.type"/></label>
                                <select id="type_name" name="type_name" class="form-control">
                                    <c:forEach items="${types}" var="type">
                                        <option value="${type.name}">
                                            <c:out value="${type.name}"/>
                                        </option>
                                    </c:forEach>
                                    <option value="1"><fmt:message key="goal.create.newType"/></option>
                                </select>
                                <br>

                                <form class="create_type" action="do?command=createGoal" method="POST">
                                    <div id="myModal" class="modal">


                                        <div class="modal" tabindex="-1" role="dialog">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title"><fmt:message
                                                                key="goal.create.newType"/></h5>
                                                        <button type="button" class="close" data-dismiss="modal"
                                                                aria-label="Close">
                                                            <span aria-hidden="true">&times;</span>
                                                        </button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <label class="input-mod" for="newType"><fmt:message
                                                                key="goal.create.createTypeName"/></label>
                                                        <input id="newType" name="newType" type="text"
                                                               class="form-control">
                                                    </div>
                                                    <div class="modal-footer">
                                                        <button id="create_type" value="create_type" name="create_type"
                                                                class="btn btn-success"><fmt:message
                                                                key="common.message.create"/></button>
                                                        <button type="button" class="btn btn-secondary"
                                                                data-dismiss="modal"><fmt:message
                                                                key="goal.create.close"/></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>

                                <!-- jQuery -->
                                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
                                <!-- Latest compiled and minified JavaScript -->
                                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
                                        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
                                        crossorigin="anonymous">
                                </script>

                                <div class="form-group">
                                    <input type="submit" value="Создать" class="btn float-right login_btn">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
    <t:colontitle/>
</div>
<script>
    $('.form-control').change(function (event) {
        if ($(this).val() === "1") {
            $('.modal').modal();
        }
    });
</script>
<script>
    var data = '<fmt:message key="${message}"/>';
    if ('${message}' !== '') alert(data);
</script>
</body>
</html>
