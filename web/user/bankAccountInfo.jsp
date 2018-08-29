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
    <title>Info</title>
</head>
<body>
<form action="/user/bankAccountInfo.jsp">
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
<br>
<a href="/user/user.jsp"> User </a>
<br>
<form action="/servlet" method="post">
    <input type="hidden" name="command" value="pay">
    <p>Pay on bank account ... </p>
    <p><input type="number" name="bankAccountTo"></p>
    <p>Price </p>
    <p><input type="number" name="price"></p>
    <p><input type="submit" value="Pay"/></p>
</form>

<br>

<form action="/servlet" method="get">
    <input type="hidden" name="command" value="history">
    <p><input type="submit" value="Account history"/></p>
</form>

<br>

<c:if test="${not empty requestScope.bankAccountHistory}">
    <c:forEach items="${requestScope.bankAccountHistory}" var="history">
        <table>
            <tr>
                <td>
                    From
                </td>
                <td>
                    To
                </td>
                <td>
                    Sender/Receiver
                </td>
                <td>
                    Price
                </td>
                <td>
                    Date
                </td>
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
</c:if>

<c:if test="${not empty requestScope.depositAccount}">
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
                <c:out value="${requestScope.depositAccount.accountType}"/><br>
            </td>
        </tr>
    </table>
</c:if>



</body>
</html>

