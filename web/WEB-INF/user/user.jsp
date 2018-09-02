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
    <title> <fmt:message key="user.welcome.page"/> </title>
</head>
<body>
<c:set var="user" value="${sessionScope.user}"/>
<c:out value="${user.firstName}"/>
<c:out value="${user.middleName}"/>
<c:out value="${user.lastName}"/>
<c:out value="${sessionScope.role}"/>

<form action="/servlet">
    <input hidden name="command" value="userHomePage">
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


<form action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="bankAccounts">
    <p><input type="submit" value="<fmt:message key="user.bank.account"/>"/></p>
</form>


<form action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="creditTariffsInfo">
    <p><input type="submit" value="Credit tariffs"></p>
</form>


<form action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="depositTariffsInfo">
    <p><input type="submit" value="Deposit tariffs"></p>
</form>

<form action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="unconfirmedCredits">
    <p><input type="submit" value="Unconfirmed tariffs"></p>
</form>

<c:if test="${not empty requestScope.paymentSuccess}">
    <br>
    <p>Is payment success?</p>
    <c:out value="${requestScope.paymentSuccess}"/>
    <br>
</c:if>


<c:if test="${not empty requestScope.bankAccounts}">
    <table>
        <caption>Bank accounts</caption>
        <tr>
            <td>
                #
            </td>
            <td>
                Account ID
            </td>
            <td>
                Balance
            </td>

            <td>
                Type
            </td>
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
    <table>
        <tr>
            <td>
                #
            </td>
            <td>
                Id
            </td>
            <td>
                Balance
            </td>
            <td>
                Credit Name
            </td>
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

<a href="credit.jsp"> Credits </a>
</body>
</html>
