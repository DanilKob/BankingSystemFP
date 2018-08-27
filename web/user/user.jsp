<%--
  Created by IntelliJ IDEA.
  User: Danila
  Date: 13.08.2018
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html  lang="${language}">
<head>
    <title> <fmt:message key="user.welcome.page"/> </title>
</head>
<body>
<c:set var="user" value="${sessionScope.user}"/>
<c:out value="${user.middleName}"/>
<c:out value="${user.firstName}"/>
<c:out value="${user.lastName}"/>
<c:out value="${sessionScope.role}"/>
<form action="user.jsp">
    <select id="language" name="language" onchange="submit()">
        <option value="eng" ${language == 'eng' ? 'selected' : ''}>English</option>
        <option value="rus" ${language == 'rus' ? 'selected' : ''}>Russian</option>
    </select>
</form>
<br>
<form action="${pageContext.request.contextPath}/servlet" method="post">

    <input type="hidden" name="command" value="logOut">

    <p><input type="submit" value="<fmt:message key="logout"/>"/></p>
</form>

<form action="${pageContext.request.contextPath}/servlet/user" method="get">
    <input type="hidden" name="command" value="credits">
    <p><input type="submit" value="<fmt:message key="user.credits"/>"/></p>
</form>

<c:if test="${not empty requestScope.credits}">
    <c:forEach items="${requestScope.credits}" var="credit">
        <c:out value="${credit.id} "/><br>
        <c:out value="${credit.balance}"/><br>
    </c:forEach>
</c:if>
<a href="credits.jsp"> Credits </a>
</body>
</html>
