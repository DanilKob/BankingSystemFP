<%--
  Created by IntelliJ IDEA.
  User: Danila
  Date: 25.08.2018
  Time: 10:47
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
    <title>Title</title>
</head>
<body>
<form action="/user/credit.jsp">
    <select id="language" name="language" onchange="submit()">
        <option value="eng" ${language == 'eng' ? 'selected' : ''}>English</option>
        <option value="rus" ${language == 'rus' ? 'selected' : ''}>Russian</option>
    </select>
</form>
<c:set var="user" value="${sessionScope.user}"/>
<c:out value="${user.firstName}"/>
<c:out value="${user.middleName}"/>
<c:out value="${user.lastName}"/>
<c:out value="${sessionScope.role}"/>

<form action="/servlet" method="get">
    <input type="hidden" name="command" value="history">
    <input type="hidden" name="serialNumberFromPage" value="${param.serialNumberFromPage}">
    <p><input type="submit" value="Account history"/></p>
</form>

<br>

<table>
    <tr>
        <td>
            id
        </td>
        <td>
            Balance
        </td>
        <td>
            Credit Name
        </td>
        <td>
            Type
        </td>
    </tr>
    <tr>
        <td>
            <c:out value="${requestScope.creditAccount.id}"/><br>
        </td>
        <td>
            <c:out value="${requestScope.creditAccount.balance}"/><br>
        </td>
        <td>
            <c:out value="${requestScope.creditAccount.name}"/><br>
        </td>
        <td>
            <c:out value="${requestScope.creditAccount.accountType}"/><br>
        </td>
    </tr>
</table>

<a href="/user/user.jsp"> User </a>
</body>
</html>
