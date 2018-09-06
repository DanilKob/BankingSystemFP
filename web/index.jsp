<%--
  Created by IntelliJ IDEA.
  User: Danila
  Date: 08.08.2018
  Time: 9:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language :'eng' }" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" />
<html  lang="${language}">
<head>
  <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
  <title> <fmt:message key="index.start.page"/> </title>
</head>
<body>
<nav class="navbar navbar-light bg-light">
  <a class="navbar-brand">Banking system</a>
  <a class="nav-link" href="registration.jsp" style="float: left"> <fmt:message key="index.registration"/></a>
  <a class="nav-link" href="login.jsp" style="float: left"> <fmt:message key="index.login"/> </a>

  <form class="form-inline" action="index.jsp">
    <select class="custom-select" id="language" name="language" onchange="submit()">
      <option value="eng" ${language == 'eng' ? 'selected' : ''}>English</option>
      <option value="rus" ${language == 'rus' ? 'selected' : ''}>Russian</option>
    </select>
  </form>
</nav>
<p class="font-weight-bold text-left"><c:out value="${sessionScope.role}"/></p>

</body>
</html>
