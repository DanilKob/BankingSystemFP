<%--
  Created by IntelliJ IDEA.
  User: Danila
  Date: 13.08.2018
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'eng'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html  lang="${language}">
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <title> <fmt:message key="registration.page"/></title>
</head>
<body>

<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand">Banking system</a>
    <a class="nav-link" href="login.jsp">  <fmt:message key="index.login"/> </a>

    <form class="form-inline" action="registration.jsp">
        <select class="custom-select" id="language" name="language" onchange="submit()">
            <option value="eng" ${language == 'eng' ? 'selected' : ''}>English</option>
            <option value="rus" ${language == 'rus' ? 'selected' : ''}>Russian</option>
        </select>
    </form>
</nav>
<p class="font-weight-bold text-left"><c:out value="${sessionScope.role}"/></p>

<form action="${pageContext.request.contextPath}/servlet" method="post" class="form-signin" action="${pageContext.request.contextPath}/servlet" method="post"  style="position: absolute;left: 50%; transform: translate(-50%);">
    <input type="hidden" name="command" value="register">
    <h1 class="h3 mb-3 font-weight-normal">Please register</h1>

    <c:if test="${not empty requestScope.firstNameError}">
        <p><c:out value="${requestScope.firstNameError}"/> Error output</p>
    </c:if>
    <label for="firstName" class="sr-only">First name</label>
    <input type="text" id="firstName" name="firstName" class="form-control" placeholder="<fmt:message key="registration.first.name"/>" value="${param.firstName}" required autofocus>

    <c:if test="${not empty requestScope.middleNameError}">
        <p><c:out value="${requestScope.middleNameError}"/> Error output</p>
    </c:if>
    <label for="middleName" class="sr-only">Middle name</label>
    <input type="text" id="middleName" name="middleName" class="form-control" placeholder="<fmt:message key="registration.middle.name"/>" value="${param.middleName}" required >

    <c:if test="${not empty requestScope.lastNameError}">
        <p><c:out value="${requestScope.lastNameError}"/> Error output</p>
    </c:if>
    <label for="lastName" class="sr-only">Email address</label>
    <input type="text" id="lastName" name="lastName" class="form-control" placeholder="<fmt:message key="registration.last.name"/>" value="${param.lastName}" required >

    <c:if test="${not empty requestScope.loginError}">
        <p><c:out value="${requestScope.loginError}"/> Error output</p>
    </c:if>
    <label for="login" class="sr-only">Email address</label>
    <input type="text" id="login" name="login" class="form-control" placeholder="<fmt:message key="registration.login"/>" value="${param.login}" required >

    <c:if test="${not empty requestScope.passwordError}">
        <p><c:out value="${requestScope.passwordError}"/> Error output</p>
    </c:if>
    <label for="password" class="sr-only">Email address</label>
    <input type="text" id="password" name="password" class="form-control" placeholder="<fmt:message key="registration.password"/> " value="${param.password}" required >

    <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
</form>

</body>
</html>
