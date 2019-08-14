<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="/WEB-INF/tld/footertag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="profile.title"/></title>
    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <!--Custom styles-->
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<div class="container">
    <%@ include file="include/menu.jsp" %>
    <form class="form-horizontal" action="do?command=profile" method=POST>
        <div class="page-header">
            <h1><fmt:message key="profile.allGoals"/></h1>
            <div class="row">
                <div class=col-md-2><fmt:message key="profile.goal"/></div>
                <div class=col-md-2><fmt:message key="profile.description"/></div>
                <div class=col-md-2><fmt:message key="profile.beginDate"/></div>
                <div class=col-md-2><fmt:message key="profile.endDate"/></div>
            </div>
            <form>
                <c:forEach items="${goals}" var="goal">
                <hr color="white">
                <h5><fmt:message key="profile.goal"/> ${goal.name}</h5>
                <form class="update-goal-${goal.id}" action="do?command=profile" method="POST">
                    <div class="row">
                        <div class=col-md-2>
                            <input type="hidden" id="id_goal" class="form-control input-md" name="id_goal"
                                   value="${goal.id}"/>
                            <input id="name_goal" class="form-control input-md" name="name_goal"
                                   value="${goal.name}"/>
                        </div>
                        <div class=col-md-2>
                            <input id="description_goal" class="form-control input-md" name="description"
                                   value="${goal.description}"/>
                        </div>
                        <div class=col-md-2>
                            <input id="begin_date" class="form-control input-md" name="begin_date"
                                   value="${goal.beginDate}"/>
                        </div>

                        <div class=col-md-2>
                            <input id="end_date" class="form-control input-md" name="end_date"
                                   value="${goal.endDate}"/>
                        </div>
                        <div class=col-md-1>
                            <button id="update_goal" value="update_goal" name="update_goal" class="btn login_btn">
                                <fmt:message key="profile.update"/>
                            </button>
                        </div>
                        <div class="opp">..</div>
                        <div class=col-md-1>
                            <button id="delete_goal" value="delete_goal" name="delete_goal" class="btn btn-danger">
                                <fmt:message key="profile.delete"/>
                            </button>
                        </div>
                    </div>
                </form>
                <form>
                    <br>
                    <h6><fmt:message key="profile.tasks"/> ${goal.name}</h6>
                    <c:forEach items="${goal.tasks}" var="task">
                        <form class="update-task-${task.id}" action="do?command=profile" method="POST">
                            <div class="row">
                                <input type="hidden" id="id_task" class="form-control input-md" name="id_task"
                                       value="${task.id}"/>
                                <div class=col-md-2>
                                    <input id="name" class="form-control input-md" name="name" disabled
                                           value="${task.name}"/>
                                </div>
                                <div class=col-md-6>
                                    <input id="description" class="form-control input-md" name="description" disabled
                                           value="${task.description}"/>
                                </div>
                                <div class=col-md-2>
                                    <input id="date" class="form-control input-md" name="date" disabled
                                           value="${task.date}"/>
                                </div>
                                <div class=col-md-1>
                                    <button id="update_task" value="update_task" name="update_task"
                                            class="btn login_btn">
                                        <fmt:message key="profile.done"/>
                                    </button>
                                </div>
                            </div>
                        </form>
                        <br>
                    </c:forEach>
                </form>
                </c:forEach>
            </form>
        </div>
    </form>
    <t:colontitle/>
</div>
<script>
    var data = '<fmt:message key="${message}"/>';
    if ('${message}' !== '') alert(data);
</script>
</body>
</html>
