<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: olatno
  Date: 14/11/2020
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Registration page</h1>
    <form:form name='loginForm' action="/admin" method='POST' modelAttribute="registerRequest">
        <div class="form-group">
            <label for="firstname">First Name</label>
            <form:input type="input" class="form-control" id="firstname" path='firstName' placeholder="First Name"/>
        </div>
        <div class="form-group">
            <label for="lastname">Last Name</label>
            <form:input type="input" class="form-control" id="lastname" path='lastName' placeholder="Email"/>
        </div>
        <div class="form-group">
            <label for="exampleInputEmail1">User Email</label>
            <form:input type="email" class="form-control" id="exampleInputEmail1" path='email' placeholder="Email"/>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <form:input type="password" class="form-control" id="exampleInputPassword1" path='password' placeholder="Password"/>
        </div>
        <div class="form-group">
            <label for="gallerytitle">User Email</label>
            <form:input type="text" class="form-control" id="gallerytitle" path='title' placeholder="Gallery title"/>
        </div>
        <button type="submit" name="submit" value="submit">Submit</button>
    </form:form>
    <div> <input class="buttonCount" type="hidden" value=""/></div>
</body>
</html>
