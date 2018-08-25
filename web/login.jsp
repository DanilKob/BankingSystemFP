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
    <title>  <fmt:message key="login.page"/>Login </title>
</head>
<body>
<form action="login.jsp">
    <select id="language" name="language" onchange="submit()">
        <option value="eng" ${language == 'eng' ? 'selected' : ''}>English</option>
        <option value="rus" ${language == 'rus' ? 'selected' : ''}>Russian</option>
    </select>
</form>
<br>
<form action="${pageContext.request.contextPath}/servlet" method="post">
    <p>${pageContext.request.requestURL}</p>

    <input type="hidden" name="command" value="logIn">
    <input type="hidden" name="page" value="login.jsp">

    <p> <fmt:message key="login.login"/> </p>
    <c:if test="${not empty requestScope.loginError}">
        <p><c:out value="${requestScope.loginError}"/></p>
    </c:if>
    <p><input type="text" name="login" value="${param.login}" size="30%"/></p>

    <p> <fmt:message key="login.password"/> </p>
    <c:if test="${not empty requestScope.passwordError}">
        <p><c:out value="${requestScope.passwordError}"/></p>
    </c:if>
    <p><input type="text" name="password" value="${param.password}" size="30%"/></p>

    <p><input type="submit" value="LogIn"/></p>
</form>
<a href="registration.jsp"> Register </a>
</body>
</html>
