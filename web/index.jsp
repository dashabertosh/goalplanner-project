<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<%@ include file="include/head.jsp" %>
<link rel="stylesheet" type="text/css" href="styles.css">
<body>
<div class="container">
    <%@ include file="include/menu.jsp" %>
    <link rel="stylesheet" type="text/css" href="styles.css">
    <div class="page-header">
        <h1>Все цели</h1>
    </div>

    <div class="row">
        <div class="col-md-3">Цель</div>
        <div class="col-md-3">Описание</div>
    </div>

    <c:forEach items="${users}" var="users">
        <br>
        <div class="row">
            <div class="col-md-3">${users.name} </div>
            <div class="col-md-3">${users.password} </div>
        </div>
    </c:forEach>
    <br><br>
</div>
</body>
</html>