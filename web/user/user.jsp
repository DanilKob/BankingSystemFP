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
<c:out value="${user.middleName}"/>
<c:out value="${user.firstName}"/>
<c:out value="${user.lastName}"/>
<c:out value="${sessionScope.role}"/>
<form action="user.jsp">
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
    <input type="hidden" name="command" value="credits">
    <p><input type="submit" value="<fmt:message key="user.credits"/>"/></p>
</form>

<form action="${pageContext.request.contextPath}/servlet" method="get">
    <input type="hidden" name="command" value="deposits">
    <p><input type="submit" value="<fmt:message key="user.deposits"/>"/></p>
</form>


<c:if test="${not empty requestScope.credits}">
    <table>
        <caption>Credits</caption>
        <tr>
            <td>
                id
            </td>
            <td>
                Balance
            </td>
            <td>
                Type
            </td>
        </tr>
        <c:forEach items="${requestScope.credits}" var="credit">
            <tr>
                <td>
                    <c:out value="${credit.id} "/><br>
                </td>
                <td>
                    <c:out value="${credit.balance}"/><br>
                </td>
                <td>
                    <form action="/servlet" method="get">
                        <input type="hidden" name="command" value="creditPage">
                        <input type="hidden" name="creditId" value="${credit.id}">
                        <p><input type="submit" value="Credit Page"/></p>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>


<c:if test="${not empty requestScope.deposits}">
    <table>
        <caption>Deposit</caption>
        <tr>
            <td>
                id
            </td>
            <td>
                Balance
            </td>
            <td>
                Type
            </td>
        </tr>
        <c:forEach items="${requestScope.deposits}" var="deposit">
            <tr>
                <td>
                    <c:out value="${deposit.id} "/><br>
                </td>
                <td>
                    <c:out value="${deposit.balance}"/><br>
                </td>
                <td>
                    <form action="/servlet" method="get">
                        <input type="hidden" name="command" value="depositPage">
                        <input type="hidden" name="depositId" value="${deposit.id}">
                        <p><input type="submit" value="Deposit Page"/></p>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<a href="credit.jsp"> Credits </a>
</body>
</html>
