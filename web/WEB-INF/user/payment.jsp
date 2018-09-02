<%--
  Created by IntelliJ IDEA.
  User: Danila
  Date: 29.08.2018
  Time: 13:41
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
    <title>Payment</title>
</head>
<body>

<c:set var="userTo" value="${requestScope.user}"/>
<c:set var="bankAccountTo" value="${param.bankAccountTo}" scope="page"/>
<c:set var="price" value="${param.price}" scope="page"/>

<c:set var="fakeId" value="${param.fakeId}" scope="page"/>
<p>Fake Id :: ${fakeId}</p>
<br>
<p>You want to pay to user....</p>

<br>
<c:out value="${user.firstName}"/>
<br>
<c:out value="${user.middleName}"/>
<br>
<c:out value="${user.lastName}"/>

<br>
<p>Price</p>
<c:out value="${requestScope.price}"/>

<br>

<form action="/servlet" method="get">
    <input type="hidden" name="command" value="payConfirmation">
    <input type="hidden" name="fakeId" value="${fakeId}">
    <input type="hidden" name="userTo" value="${user.id}">
    <input type="hidden" name="bankAccountTo" value="${bankAccountTo}">
    <input type="hidden" name="price" value="${price}">

    <p><input type="submit" value="Pay"/></p>
</form>

</body>
</html>
