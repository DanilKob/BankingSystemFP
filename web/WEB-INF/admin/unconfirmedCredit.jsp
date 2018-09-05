<%--
  Created by IntelliJ IDEA.
  User: Danila
  Date: 04.09.2018
  Time: 21:01
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
    <title>Info</title>
</head>
<body>
<c:set var="fakeId" value="${param.fakeId}" scope="page"/>
<c:set var="command" value="${param.command}" scope="page"/>


<form action="/servlet">
    <select id="language" name="language" onchange="submit()">
        <option value="eng" ${language == 'eng' ? 'selected' : ''}>English</option>
        <option value="rus" ${language == 'rus' ? 'selected' : ''}>Russian</option>
        <input type="hidden" name="command" value="${command}">
        <input type="hidden" name="fakeId" value="${fakeId}">
    </select>

</form>
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

<form action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="confirmCredit">
    <input type="number" name="creditIndebtedness">
    <input type="hidden" name="fakeId" value="${fakeId}">
    <p><input type="submit" value="Confirm credit"/></p>
</form>

<form action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="cancelCredit">
    <input type="hidden" name="fakeId" value="${fakeId}">
    <p><input type="submit" value="Cancel credit"/></p>
</form>

<c:if test="${not empty requestScope.creditAccount}">
    <table>
        <tr>
            <td>
                User id
            </td>
            <td>
                Tariff name
            </td>
            <td>
                Tariff rate
            </td>
            <td>
                Tariff accrual rate
            </td>
        </tr>
        <tr>
            <td>
                <c:out value="${requestScope.creditAccount.userId}"/>
            </td>
            <td>
                <c:out value="${requestScope.creditAccount.creditTariff.name}"/>
            </td>
            <td>
                <c:out value="${requestScope.creditAccount.creditTariff.rate}"/>
            </td>
            <td>
                <c:out value="${requestScope.creditAccount.creditTariff.accrualRate}"/>
            </td>
        </tr>
    </table>
</c:if>


<c:if test="${not empty requestScope.allUserDeposits}">
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
                Deposit amount
            </td>
            <td>
                Type
            </td>
        </tr>
        <c:forEach items="${requestScope.allUserDeposits}" var="depositAccount">
            <tr>
                <td>
                    <c:out value="${depositAccount.id}"/><br>
                </td>
                <td>
                    <c:out value="${depositAccount.balance}"/><br>
                </td>
                <td>
                    <c:out value="${depositAccount.depositTariff.name}"/><br>
                </td>
                <td>
                    <c:out value="${depositAccount.depositAmount}"/><br>
                </td>
                <td>
                    <c:out value="${depositAccount.accountType}"/><br>
                </td>
            </tr>
        </c:forEach>

    </table>
</c:if>

</body>
</html>
