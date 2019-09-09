<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="/WEB-INF/tld/footertag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="admin.title"/></title>
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
    <form class="form-horizontal" action="do?command=admin" method=POST>
        <div class="page-header">
            <h1><fmt:message key="admin.all.users"/></h1>
            <div class="row">
                <div class=col-md-1><fmt:message key="admin.id"/></div>
                <div class=col-md-2><fmt:message key="admin.name"/></div>
                <div class=col-md-2><fmt:message key="admin.login"/></div>
                <div class=col-md-2><fmt:message key="admin.role"/></div>
            </div>
            <form>
                <c:forEach items="${users}" var="user">
                    <hr color="white">
                    <form class="update-user-${user.id}" action="do?command=admin" method=POST>
                        <div class="row">
                            <div class=col-md-1>
                                <input id="id" class="form-control input-md" name="id"
                                       value="${user.id}"/>
                            </div>
                            <div class=col-md-2>
                                <input id="name" class="form-control input-md" name="name"
                                       value="${user.name}"/>
                            </div>
                            <div class=col-md-2>
                                <input id="login" class="form-control input-md" name="login"
                                       value="${user.login}"/>
                            </div>

                                <input id="password" type="hidden" class="form-control input-md" name="password"
                                       value="${user.password}"/>

                            <div class=col-md-2>
                                <select id="role_id" name="role_id" class="form-control">
                                    <c:forEach items="${roles}" var="role">
                                        <option value="${role.id}"
                                                role=${role.id} ${role.id==user.roleId? "selected":""}>
                                                ${role.role}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class=col-md-1>
                                <button id="update" value="update" name="update" class="btn login_btn">
                                    <fmt:message key="profile.update"/>
                                </button>
                            </div>
                            <div class="opp">..</div>
                            <div class=col-md-1>
                                <button id="delete" value="delete" name="delete" class="btn btn-danger">
                                    <fmt:message key="profile.delete"/>
                                </button>
                            </div>
                            <hr color="white">
                        </div>
                    </form>
                    <br>
                </c:forEach>
            </form>
        </div>
    </form>
</body>
</html>
