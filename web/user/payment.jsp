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
    <title>Payment</title>
</head>
<body>
<c:set var="user" value="${requestScope.user}"/>
<br>
<c:out value="${user.firstName}"/>
<br>
<c:out value="${user.middleName}"/>
<br>
<c:out value="${user.lastName}"/>

</body>
</html>
