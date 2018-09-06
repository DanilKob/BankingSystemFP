<%--
  Created by IntelliJ IDEA.
  User: Danila
  Date: 28.08.2018
  Time: 17:24
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
    <title>Bank account info</title>
</head>
<body>
<c:set var="fakeId" value="${param.fakeId}" scope="page"/>
<c:set var="command" value="${param.command}" scope="page"/>

<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand font-weight-bold">Banking system</a>
    <a class="nav-link" href="registration.jsp" style="float: left"> <fmt:message key="index.registration"/></a>

    <form class="form-inline " action="${pageContext.request.contextPath}/servlet" method="get">
        <input type="hidden" name="command" value="userHomePage">
        <button class="btn btn-sm btn-primary btn-outline-dark" type="submit">Home</button>
    </form>

    <form class="form-inline " action="${pageContext.request.contextPath}/servlet" method="post">
        <input type="hidden" name="command" value="logOut">
        <button class="btn btn-sm btn-primary btn-outline-dark" type="submit"><fmt:message key="logout"/></button>
    </form>

    <form class="form-inline" action="${pageContext.request.contextPath}/servlet">
        <input type="hidden" name="command" value="${command}">
        <input type="hidden" name="fakeId" value="${fakeId}">
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
<div class="container">
    <form action="${pageContext.request.contextPath}/servlet" method="post">
        <input type="hidden" name="command" value="pay">
        <input type="hidden" name="fakeId" value="${fakeId}">

        <h1 class="h3 mb-3 font-weight-normal">Pay on bank account ...</h1>

        <label for="bankAccountTo" class="sr-only">Target account id </label>
        <input type="number" id="bankAccountTo" name="bankAccountTo" class="form-control" placeholder=" target bank account id"  required autofocus>


        <label for="price" class="sr-only">Price</label>
        <input type="number" id="price" name="price" class="form-control" placeholder="price"  required>

        <button class="btn btn-sm btn-primary " type="submit"><fmt:message key="bank.account.pay"/></button>
    </form>
</div>

<form action="/servlet" method="get">
    <input type="hidden" name="command" value="history">
    <input type="hidden" name="fakeId" value="${fakeId}">
    <button class="btn btn-sm btn-primary " type="submit"><fmt:message key="user.history"/></button>
</form>

<br>

<c:if test="${not empty requestScope.bankAccountHistory}">
    <c:forEach items="${requestScope.bankAccountHistory}" var="history">
        <table class="table table-striped table-sm">
            <tr>
                <th>
                    From
                </th>
                <th>
                    To
                </th>
                <th>
                    Sender/Receiver
                </th>
                <th>
                    Price
                </th>
                <th>
                    Date
                </th>
            </tr>
            <tr>
                <td>
                    <c:out value="${history.fromAccountId}"/><br>
                </td>
                <td>
                    <c:out value="${history.toAccountId}"/><br>
                </td>
                <td>
                    <c:out value="${history.user.firstName}"/><br>
                    <c:out value="${history.user.middleName}"/><br>
                    <c:out value="${history.user.lastName}"/><br>
                </td>
                <td>
                    <c:out value="${history.balance}"/><br>
                </td>
                <td>
                    <c:out value="${history.date}"/><br>
                </td>
            </tr>
        </table>
    </c:forEach>
</c:if>

<c:if test="${not empty requestScope.creditAccount}">
    <table class="table table-striped">
        <tr>
            <th>
                id
            </th>
            <th>
                Balance
            </th>
            <th>
                Credit Name
            </th>
            <th>
                Indebtedness
            </th>
            <th>
                Type
            </th>
        </tr>

        <tr>
            <td>
                <c:out value="${requestScope.creditAccount.id}"/>
            </td>
            <td>
                <c:out value="${requestScope.creditAccount.balance}"/>
            </td>
            <td>
                <c:out value="${requestScope.creditAccount.creditTariff.name}"/>
            </td>
            <td>
                <c:out value="${requestScope.creditAccount.indebtedness}"/>
            </td>
            <td>
                <c:out value="${requestScope.creditAccount.accountType}"/>
            </td>
        </tr>
    </table>
</c:if>


<c:if test="${not empty requestScope.depositAccount}">
    <table class="table table-striped ">
        <tr>
            <th>
                id
            </th>
            <th>
                Balance
            </th>
            <th>
                Deposit Name
            </th>
            <th>
                Deposit amount
            </th>
            <th>
                Type
            </th>
        </tr>
        <tr>
            <td>
                <c:out value="${requestScope.depositAccount.id}"/><br>
            </td>
            <td>
                <c:out value="${requestScope.depositAccount.balance}"/><br>
            </td>
            <td>
                <c:out value="${requestScope.depositAccount.depositTariff.name}"/><br>
            </td>
            <td>
                <c:out value="${requestScope.depositAccount.depositAmount}"/><br>
            </td>
            <td>
                <c:out value="${requestScope.depositAccount.accountType}"/><br>
            </td>
        </tr>
    </table>
</c:if>

</body>
</html>

