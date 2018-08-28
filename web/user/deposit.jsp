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
<form action="/user/deposit.jsp">
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


<table>
    <tr>
        <td>
            id
        </td>
        <td>
            Balance
        </td>
        <td>
            Deposit Name
        </td>
        <td>
            Type
        </td>
    </tr>
    <tr>
        <td>
            <c:out value="${requestScope.depositAccount.id}"/><br>
        </td>
        <td>
            <c:out value="${requestScope.depositAccount.balance}"/><br>
        </td>
        <td>
            <c:out value="${requestScope.depositAccount.name}"/><br>
        </td>
        <td>
            Deposit
        </td>
    </tr>
</table>

<a href="/user/user.jsp"> User </a>
</body>
</html>
