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
    <title> Tarif info</title>
</head>
<body>


<c:if test="${not empty requestScope.creditTariffs}">
    CreditTariffs
    <table>
        <caption>Credit tariffs</caption>
        <tr>
            <td>
                #
            </td>
            <td>
                Tariff ID
            </td>
            <td>
                Name
            </td>
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
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/servlet">
        <input type="hidden" name="command" value="registerCreditAccount">
        <p> Select credit tariff
            <select name="creditTariffId" required >
                <c:forEach items="${requestScope.creditTariffs}" var="creditTariff">
                    <option value="${creditTariff.id}">${creditTariff.name}</option>
                </c:forEach>
            </select>
        </p>
        <p><input type="submit" value="Open credit"/></p>
    </form>
</c:if>


<c:if test="${not empty requestScope.depositTariffs}">
    Deposit Tariffs
    <table>
        <caption>Deposit tariffs</caption>
        <tr>
            <td>
                #
            </td>
            <td>
                Tariff ID
            </td>
            <td>
                Name
            </td>
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
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/servlet">
        <input type="hidden" name="command" value="registerDepositAccount">
        <input type="number" name="balance">
        <p> Select deposit tariff
            <select name="depositTariffId" required >
                <c:forEach items="${requestScope.depositTariffs}" var="depositTariff">
                    <option value="${depositTariff.id}">${depositTariff.name}</option>
                </c:forEach>
            </select>
        </p>
        <p><input type="submit" value="Open deposit"/></p>
    </form>
</c:if>
</body>
</html>
