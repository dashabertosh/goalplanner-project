<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href=".">HOME</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <c:choose>
                <c:when test="${user == null}">
                    <a class="nav-item nav-link" href="do?command=signUp"><fmt:message key="common.message.signUp"/></a>
                    <a class="nav-item nav-link" href="do?command=login"><fmt:message key="common.message.login"/></a>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${user.roleId == '1'}">
                            <a class="nav-item nav-link" href="do?command=profile"><fmt:message
                                    key="common.message.profile"/></a>
                            <a class="nav-item nav-link" href="do?command=createGoal"><fmt:message
                                    key="common.message.createGoal"/></a>
                            <a class="nav-item nav-link" href="do?command=createTask"><fmt:message
                                    key="common.message.createTask"/></a>
                            <a class="nav-item nav-link" href="do?command=logout"><fmt:message
                                    key="common.message.logout"/></a>
                        </c:when>
                        <c:otherwise>
                            <a class="nav-item nav-link" href="do?command=admin"><fmt:message
                                    key="admin.title"/></a>
                            <a class="nav-item nav-link" href="do?command=logout"><fmt:message
                                    key="common.message.logout"/></a>
                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="language">
        <c:import url="include/language-panel.jsp"/>
    </div>
</nav>
