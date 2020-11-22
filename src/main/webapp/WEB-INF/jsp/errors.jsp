
<%--
  Created by IntelliJ IDEA.
  User: olatno
  Date: 14/11/2020
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglibs.jsp" %>
<html>

<%@include file="header.jsp" %>

<body>
    <%@include file="nav.jsp" %>
    <div class="container" >
        <c:set value="${message}"  var="messages"/>
        <h3>${messages} </h3>
    </div>
</body>
</html>
