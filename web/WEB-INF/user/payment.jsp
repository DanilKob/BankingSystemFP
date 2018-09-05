<%--
  Created by IntelliJ IDEA.
  User: Danila
  Date: 29.08.2018
  Time: 13:41
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
    <title>Payment</title>
</head>
<body>

<c:set var="userTo" value="${requestScope.user}"/>
<c:set var="bankAccountTo" value="${param.bankAccountTo}" scope="page"/>
<c:set var="price" value="${param.price}" scope="page"/>
<c:set var="fakeId" value="${param.fakeId}" scope="page"/>


<c:if test="${(not empty userTo)&&(not emptybankAccountTo)&&(not emptyprice)&&(not empty fakeId)}">
    <br>
    <p class="text-info">You want to pay to...</p>

    <table class="table table-sm table-bordered">
        <tr>
            <th>
                Account id
            </th>
            <th>
                Uaer
            </th>
            <th>
                Price
            </th>
        </tr>
        <tr>
            <td>
                <c:out value="${bankAccountTo}"/>
            </td>
            <td>
                <c:out value="${user.firstName}"/>
                <c:out value="${user.middleName}"/>
                <c:out value="${user.lastName}"/>
            </td>
            <td>
                <c:out value="${requestScope.price}"/>
            </td>
        </tr>
    </table>


    <form action="/servlet" method="get">
        <input type="hidden" name="command" value="payConfirmation">
        <input type="hidden" name="fakeId" value="${fakeId}">
        <input type="hidden" name="userTo" value="${user.id}">
        <input type="hidden" name="bankAccountTo" value="${bankAccountTo}">
        <input type="hidden" name="price" value="${price}">
        <button class="btn btn-sm btn-primary " type="submit"> Pay</button>
    </form>
</c:if>

<c:if test="${not empty requestScope.paymentSuccess}">
    <br>
    <p class="font-weight-bold">Is payment success?</p>
    <p class="font-weight-bold"></p><c:out value="${requestScope.paymentSuccess}"/>
</c:if>


<form action="${pageContext.request.contextPath}/servlet">
    <input hidden name="command" value="userHomePage">
    <button class="btn btn-sm btn-primary " type="submit"> Home</button>
</form>

</body>
</html>
