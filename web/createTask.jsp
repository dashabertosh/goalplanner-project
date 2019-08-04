<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="include/menu.jsp" %>
    <form class="form-horizontal" action=do?command=createTask method="POST">
        <fieldset>

            <form class="form-horizontal">
                <fieldset>

                    <!-- Form Name -->
                    <legend>Your Task</legend>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="name">Name Task</label>
                        <div class="col-md-4">
                            <input id="name" name="name" value="TestNameTask" type="text" placeholder="" class="form-control input-md" required="">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="description">Description Task</label>
                        <div class="col-md-4">
                            <input id="description" name="description" value="TestDescriptionTask" type="text" placeholder="" class="form-control input-md" required="">

                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="date">Date</label>
                        <div class="col-md-4">
                            <input id="date" name="date" value="2019" type="date" placeholder="" class="form-control input-md" required="">
                        </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="id_goals">Name Goals</label>
                        <div class="col-md-4">
                            <select id="id_goals" name="id_goals" class="form-control">
                                <c:forEach items="${goals}" var="goal">
                                    <option value="${goal.id}" role=${goal.id}>
                                            ${goal.name}
                                    </option>
                                </c:forEach>
                                <option>Создать новую цель</option>
                            </select>
                        </div>
                    </div>

                    <!-- Button -->
                    <div class="form-group">
                        <div class="col-md-4">
                            <button id="create" name="create" class="btn btn-primary">Create</button>
                        </div>
                    </div>

                </fieldset>
            </form>

        </fieldset>
    </form>
</div>
</body>
</html>
