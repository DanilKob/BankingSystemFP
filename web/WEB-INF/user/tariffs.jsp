<%--
  Created by IntelliJ IDEA.
  User: Danila
  Date: 31.08.2018
  Time: 20:02
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
    <title> Tariff info</title>
</head>
<body>

<c:set var="command" value="${param.command}" scope="page"/>

<nav class="navbar navbar-light bg-light">
    <a class="navbar-brand font-weight-bold">Banking system</a>

    <form class="form-inline " action="${pageContext.request.contextPath}/servlet" method="post">
        <input type="hidden" name="command" value="logOut">
        <button class="btn btn-sm btn-primary btn-outline-dark" type="submit"><fmt:message key="logout"/></button>
    </form>

    <form class="form-inline" action="${pageContext.request.contextPath}/servlet">
        <input hidden name="command" value="${command}">
        <select class="custom-select" id="language" name="language" onchange="submit()">
            <option value="eng" ${language == 'eng' ? 'selected' : ''}>English</option>
            <option value="rus" ${language == 'rus' ? 'selected' : ''}>Russian</option>
        </select>
    </form>

</nav>


<c:if test="${not empty requestScope.creditTariffs}">
    <table class="table table-sm table-bordered table-striped">
        <caption><fmt:message key="user.tariffs.credit"/></caption>
        <tr>
            <th>
                #
            </th>
            <td>
                Tariff ID
            </td>
            <th>
                Name
            </th>
            <th>
                Rate
            </th>
            <th>
                Accrual Rate(min)
            </th>
        </tr>
        <c:set var="count" value="0" scope="page" />
        <c:forEach items="${requestScope.creditTariffs}" var="creditTariff">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <td>
                    <c:out value="${count} "/><br>
                </td>
                <td>
                    <c:out value="${creditTariff.id} "/><br>
                </td>
                <td>
                    <c:out value="${creditTariff.name}"/><br>
                </td>
                <td>
                    <c:out value="${creditTariff.rate}"/><br>
                </td>
                <td>
                    <c:out value="${creditTariff.accrualRate}"/><br>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="container">
        <form action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" name="command" value="registerCreditAccount">
            <p> Select credit tariff
                <select class="custom-select" name="creditTariffId" required >
                    <c:forEach items="${requestScope.creditTariffs}" var="creditTariff">
                        <option value="${creditTariff.id}">${creditTariff.name}</option>
                    </c:forEach>
                </select>
            </p>
            <button class="btn btn-sm btn-primary " type="submit"><fmt:message key="tariff.open.credit"/></button>
        </form>
    </div>

</c:if>


<c:if test="${not empty requestScope.depositTariffs}">
    <table class="table table-sm table-bordered table-striped">
        <caption><fmt:message key="user.tariffs.deposit"/></caption>
        <tr>
            <th>
                #
            </th>
            <td>
                Tariff ID
            </td>
            <th>
                Name
            </th>
            <th>
                Rate(%)
            </th>
            <th>
                Accrual Rate(min)
            </th>
        </tr>
        <c:set var="count" value="0" scope="page" />
        <c:forEach items="${requestScope.depositTariffs}" var="depositTariff">
            <c:set var="count" value="${count + 1}" scope="page"/>
            <tr>
                <td>
                    <c:out value="${count} "/><br>
                </td>
                <td>
                    <c:out value="${depositTariff.id} "/><br>
                </td>
                <td>
                    <c:out value="${depositTariff.name}"/><br>
                </td>
                <td>
                    <c:out value="${depositTariff.rate}"/><br>
                </td>
                <td>
                    <c:out value="${depositTariff.accrualRate}"/><br>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="container">
        <form action="${pageContext.request.contextPath}/servlet">
            <input type="hidden" name="command" value="registerDepositAccount">

            <label for="balance" class="sr-only">Email address</label>
            <input type="text" id="balance" name="balance" class="form-control" placeholder="Deposit amount" required>

            <p> Select deposit tariff
                <select  class="custom-select" name="depositTariffId" required >
                    <c:forEach items="${requestScope.depositTariffs}" var="depositTariff">
                        <option value="${depositTariff.id}">${depositTariff.name}</option>
                    </c:forEach>
                </select>
            </p>
            <button class="btn btn-sm btn-primary " type="submit"><fmt:message key="tarrif.open.deposit"/></button>
        </form>
    </div>

</c:if>
</body>
</html>
