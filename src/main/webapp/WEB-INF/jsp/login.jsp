
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
    <div class="global-admin-container global-container">

        <h1>Login page</h1>
        <h2>${loginMgs}</h2>
        <form name='loginForm' action="<c:url value='/login' />" method='POST' >
            <div class="form-group">
                <label for="exampleInputEmail1">User Name</label>
                <input type="email" class="form-control" id="exampleInputEmail1" name='username' placeholder="Email"/>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input type="password" class="form-control" id="exampleInputPassword1" name='password' placeholder="Password"/>
            </div>
            <button type="submit" name="submit" value="submit">Submit</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>
    </div>
</body>
</html>
