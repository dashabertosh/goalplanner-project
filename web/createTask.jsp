<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="/WEB-INF/tld/footertag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>

<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <title><fmt:message key="task.create.title"/></title>

    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <!--Custom styles-->
    <link rel="stylesheet" type="text/css" href="css/createTaskCss.css">
</head>
<body>
<div class="container">
    <%@ include file="include/menu.jsp" %>
    <form id="createTask" class="form-horizontal" action="do?command=createTask" method="POST">
        <div class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="card">
                    <div class="card-header">
                        <h3><fmt:message key="task.create.title"/></h3>
                    </div>
                    <div class="card-body">
                        <form>
                            <label class="input" for="name"><fmt:message key="task.create.name"/></label>
                            <input id="name" name="name" type="text" class="form-control" placeholder="name">
                            <br>

                            <label class="input" for="description"><fmt:message key="task.create.description"/></label>
                            <textarea id="description" name="description" class="form-control"
                                      placeholder="description"> </textarea>
                            <br>

                            <label class="input" for="date"><fmt:message key="task.create.date"/></label>
                            <input id="date" name="date" type="date" class="form-control"
                                   placeholder="date">
                            <br>

                            <label class="input" for="name_goal"><fmt:message key="task.create.goal"/></label>
                            <select id="name_goal" name="name_goal" class="form-control">
                                <c:forEach items="${goals}" var="goal">
                                    <option value="${goal.name}">
                                        <c:out value="${goal.name}"/>
                                    </option>
                                </c:forEach>
                                <option value="1"><fmt:message key="task.create.newGoal"/></option>
                            </select>
                            <br>
                            <div class="form-group">
                                <input type="submit" value="<fmt:message key="common.message.create"/>" class="btn float-right login_btn">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <t:colontitle/>
</div>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous">
</script>
<script>
    $('.form-control').change(function (event) {
        if ($(this).val() === "1") {
            document.location.href = "http://localhost:8080/goalplanner/do?command=createGoal";
        }
    });
</script>
<script>
    var data = '<fmt:message key="${message}"/>';
    if ('${message}' !== '') alert(data);
</script>
</body>
</html>
