<%--
  Created by IntelliJ IDEA.
  User: Danila
  Date: 06.09.2018
  Time: 14:01
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
    <title>Server exception</title>
</head>
<body>

<div class="container">
    <p class="text-danger font-weight-bold">
            Something going wrong... Please try again
    </p>
    <form action="${pageContext.request.contextPath}/servlet" method="get">
        <input type="hidden" name="command" value="homePage">
        <button class="btn btn-sm btn-primary " type="submit">Home page</button>
    </form>
</div>


</body>
</html>
