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
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <title> Admin page </title>
</head>
<body>

<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand font-weight-bold">Banking system</a>

    <form class="form-inline " action="${pageContext.request.contextPath}/servlet" method="post">
        <input type="hidden" name="command" value="logOut">
        <button class="btn btn-sm btn-primary btn-outline-dark" type="submit"><fmt:message key="logout"/></button>
    </form>

    <form class="form-inline" action="${pageContext.request.contextPath}/servlet">
        <input hidden name="command" value="adminHomePage">
        <select class="custom-select" id="language" name="language" onchange="submit()">
            <option value="eng" ${language == 'eng' ? 'selected' : ''}>English</option>
            <option value="rus" ${language == 'rus' ? 'selected' : ''}>Russian</option>
        </select>
    </form>

</nav>

<c:set var="user" value="${sessionScope.user}"/>
<c:out value="${user.firstName}"/>
<c:out value="${user.middleName}"/>
<c:out value="${user.lastName}"/>
<c:out value="${sessionScope.role}"/>

<br>
<form action="${pageContext.request.contextPath}/servlet" method="post">
    <input type="hidden" name="command" value="logOut">
    <p><input type="submit" value="<fmt:message key="logout"/>"/></p>
</form>

<br>

<form action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="allUnconfirmedCredits">
    <p><input type="submit" value="Unconfirmed credits list"/></p>
</form>


<c:if test="${not empty requestScope.allUnconfirmedCredits}">
    <table class="table table-striped table-sm">
        <tr>
            <th>
                #
            </th>
            <th>
                User Id
            </th>
            <th>
                Credit Name
            </th>
            <th>
                Bank account Id
            </th>
        </tr>
        <c:set var="count" value="0" scope="page" />
        <c:forEach items="${requestScope.allUnconfirmedCredits}" var="unconfirmedCredit">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <td>
                    <c:out value="${count} "/><br>
                </td>
                <td>
                    <c:out value="${unconfirmedCredit.entity.userId}"/><br>
                </td>
                <td>
                    <c:out value="${unconfirmedCredit.entity.creditTariff.name}"/><br>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/servlet" method="get">
                        <input type="hidden" name="command" value="unconfirmedCreditInfo">
                        <input type="hidden" name="fakeId" value="${unconfirmedCredit.fakeId}">
                        <p><input type="submit" value="${unconfirmedCredit.entity.id}"/></p>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
