<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="../styles.css">
</head>
<body>
<form class="container">
    <fmt:message key="common.message.language"/>
    <label for="${language}">
        <select id="language" class="form-control" name="language" onchange="submit()">
            <option value="en" <c:if test="${language == 'en'}"> selected </c:if> ><fmt:message
                    key="common.message.english"/></option>
            <option value="ru" <c:if test="${language == 'ru'}"> selected </c:if> ><fmt:message
                    key="common.message.russian"/></option>
        </select>
    </label>
</form>
</body>
</html>
