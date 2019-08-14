<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="/WEB-INF/tld/footertag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
</head>
<link rel="stylesheet" type="text/css" href="styles.css">
<body>
<div class="container">
    <%@ include file="include/menu.jsp" %>
    <div class="page-header">
        <h1>Все цели</h1>
    </div>

    <div class="row">
        <div class="col-md-3">Цель</div>
        <div class="col-md-3">Описание</div>
    </div>

    <c:forEach items="${goals}" var="user">
        <br>
        <div class="row">
            <div class="col-md-3">${user.name} </div>
            <div class="col-md-3">${user.password} </div>
        </div>
    </c:forEach>
    <br><br>
    <t:colontitle/>
</div>
</body>
</html>