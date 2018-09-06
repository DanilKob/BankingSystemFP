<%--
  Created by IntelliJ IDEA.
  User: Danila
  Date: 13.08.2018
  Time: 15:10
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
    <title>  <fmt:message key="login.page"/></title>
</head>
<body>

<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand">Banking system</a>
    <a class="nav-link" href="registration.jsp" style="float: left"> <fmt:message key="index.registration"/></a>

    <form class="form-inline" action="login.jsp">
        <select class="custom-select" id="language" name="language" onchange="submit()">
            <option value="eng" ${language == 'eng' ? 'selected' : ''}>English</option>
            <option value="rus" ${language == 'rus' ? 'selected' : ''}>Russian</option>
        </select>
    </form>
</nav>
<p class="font-weight-bold text-left"><c:out value="${sessionScope.role}"/></p>

    <form class="form-signin" action="${pageContext.request.contextPath}/servlet" method="post"  style="position: absolute;left: 50%; transform: translate(-50%);">
        <input type="hidden" name="command" value="logIn">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

        <c:if test="${not empty requestScope.loginError}">
            <p><c:out value="${requestScope.loginError}"/></p>
        </c:if>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="inputEmail" name="login" class="form-control" placeholder="<fmt:message key="login.login"/>" value="${param.login}" required autofocus>

        <c:if test="${not empty requestScope.passwordError}">
            <p><c:out value="${requestScope.passwordError}"/></p>
        </c:if>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="<fmt:message key="login.password"/>" value="${param.password}" required>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
</body>
</html>
