<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login Page</title>
    <!--Made with love by Mutiullah Samim -->

    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <!--Custom styles-->
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<%@ include file="include/head.jsp" %>
<div class="container">

<%@ include file="include/menu.jsp" %>
<div class="page-header">
    <h1>Все цели</h1>
</div>

<div class="container">
    <div class="row">
        <div class=col-md-1>Номер</div>
        <div class=col-md-2>Цель</div>
        <div class=col-md-2>Описание</div>
        <div class=col-md-2>Дата начала</div>
        <div class=col-md-2>Дата завершения</div>
    </div>
</div>

<div class="container">
    <c:forEach items="${goals}" var="goal">
        <hr>
        <h5>Цель ${goal.name}</h5>
        <form class="update-goal-${goal.id}" action="do?command=profile" method=POST>
            <div class="row">
                <div class=col-md-1>
                    <input id="id_goal" class="form-control input-md" name="id"
                           value="${goal.id}"/>
                </div>
                <div class=col-md-2>
                    <input id="name_goal" class="form-control input-md" name="name"
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
                    <button id="update_goal" value="update_goal" name="update_goal" class="btn btn-success">
                        Обновить
                    </button>
                </div>

                <div class=col-md-1>
                    <button id="delete_goal" value="delete_goal" name="delete_goal" class="btn btn-danger">
                        Удалить
                    </button>
                </div>

                <div class="container">
                    <br>
                    <h6>Задания ${goal.name}</h6>
                    <c:forEach items="${goal.tasks}" var="task">

                        <form class="update-task-${task.id}" action="do?command=profile" method=POST>
                            <div class="row">
                                <div class=col-md-1>
                                    <input id="id" class="form-control input-md" name="id_task" value="${task.id}"/>
                                </div>
                                <div class=col-md-2>
                                    <input id="name" class="form-control input-md" name="name" value="${task.name_task}"/>
                                </div>
                                <div class=col-md-6>
                                    <input id="description" class="form-control input-md" name="description" value="${task.description_task}"/>
                                </div>

                                <div class=col-md-1>
                                    <button id="update_task" value="Update_task" name="update_task" class="btn btn-success">
                                        Выполнено
                                    </button>
                                </div>

                            </div>
                        </form>
                        <br>
                    </c:forEach>
                </div>
            </div>

        </form>
    </c:forEach>
</div>
</div>
</body>
</html>
