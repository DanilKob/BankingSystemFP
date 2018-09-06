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
<%@ taglib prefix="credit" uri="/WEB-INF/creditTable" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html  lang="${language}">
<head>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <title> <fmt:message key="user.welcome.page"/> </title>
</head>
<body>

<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand font-weight-bold">Banking system</a>

    <form class="form-inline " action="${pageContext.request.contextPath}/servlet" method="post">
        <input type="hidden" name="command" value="logOut">
        <button class="btn btn-sm btn-primary btn-outline-dark" type="submit"><fmt:message key="logout"/></button>
    </form>

    <form class="form-inline" action="${pageContext.request.contextPath}/servlet">
        <input hidden name="command" value="userHomePage">
        <select class="custom-select" id="language" name="language" onchange="submit()">
            <option value="eng" ${language == 'eng' ? 'selected' : ''}>English</option>
            <option value="rus" ${language == 'rus' ? 'selected' : ''}>Russian</option>
        </select>
    </form>

</nav>

<c:set var="user" value="${sessionScope.user}"/>

<p class="text-center ">
    <c:out value="${sessionScope.role}"/>
</p>
<p class="font-weight-bold text-center">
    <c:out value="${user.firstName}"/>
    <c:out value="${user.middleName}"/>
    <c:out value="${user.lastName}"/>
</p>


<form class="form-inline" action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="bankAccounts">
    <button class="btn btn-sm btn-primary " type="submit"> <fmt:message key="user.bank.account"/></button>
</form>

<form action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="creditTariffsInfo">
    <button class="btn btn-sm btn-primary " type="submit">Credit tariffs</button>
</form>


<form action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="depositTariffsInfo">
    <button class="btn btn-sm btn-primary " type="submit">Deposit tariffs</button>
</form>

<form action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="unconfirmedCredits">
    <button class="btn btn-sm btn-primary " type="submit">Unconfirmed tariffs</button>
</form>

<c:if test="${not empty requestScope.paymentSuccess}">
    <br>
    <p>Is payment success?</p>
    <c:out value="${requestScope.paymentSuccess}"/>
    <br>
</c:if>


<c:if test="${not empty requestScope.bankAccounts}">
    <table class="table table-striped">
        <caption>Bank accounts</caption>
        <tr>
            <th>
                #
            </th>
            <th>
                Account ID
            </th>
            <th>
                Balance
            </th>

            <th>
                Type
            </th>
        </tr>
        <c:set var="count" value="0" scope="page" />
        <c:forEach items="${requestScope.bankAccounts}" var="bankAccount">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <td>
                    <c:out value="${count} "/><br>
                </td>
                <td>
                    <c:out value="${bankAccount.entity.id} "/><br>
                </td>
                <td>
                    <c:out value="${bankAccount.entity.balance}"/><br>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/servlet" method="get">
                        <input type="hidden" name="command" value="${bankAccount.entity.accountType}">
                        <input type="hidden" name="fakeId" value="${bankAccount.fakeId}">
                        <p><input type="submit" value="${bankAccount.entity.accountType}"/></p>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>



<c:if test="${not empty requestScope.unconfirmedCredits}">
    <table class="table table-striped">
        <caption>Unconfirmed credit</caption>
        <tr>
            <th>
                #
            </th>
            <td>
                Id
            </td>
            <th>
                Balance
            </th>
            <th>
                Credit Name
            </th>
        </tr>
        <c:set var="count" value="0" scope="page" />
        <c:forEach items="${requestScope.unconfirmedCredits}" var="unconfirmedCredit">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <td>
                    <c:out value="${count} "/><br>
                </td>
                <td>
                    <c:out value="${unconfirmedCredit.entity.id}"/><br>
                </td>
                <td>
                    <c:out value="${unconfirmedCredit.entity.balance}"/><br>
                </td>
                <td>
                    <c:out value="${unconfirmedCredit.entity.creditTariff.name}"/><br>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
