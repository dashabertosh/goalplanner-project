<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href=".">HOME</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <c:choose>
                <c:when test="${user==null}">
<%--                    <a class="nav-item nav-link" href="do?command=reset">Сбросить</a>--%>
                    <a class="nav-item nav-link" href="do?command=signup">Регистрация</a>
                    <a class="nav-item nav-link" href="do?command=login">Авторизация</a>
                </c:when>
                <c:otherwise>
                    <a class="nav-item nav-link" href="do?command=profile">Профиль</a>
                    <a class="nav-item nav-link" href="do?command=creategoal">Создать цель</a>
                    <a class="nav-item nav-link" href="do?command=createtask">Создать задание</a>
<%--                    <a class="nav-item nav-link" href="do?command=EditUsers">Админка</a>--%>
<%--                    <a class="nav-item nav-link" href="do?command=Logout">Выход</a>--%>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>
