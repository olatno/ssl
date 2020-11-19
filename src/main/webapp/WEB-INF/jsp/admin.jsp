<%--
  Created by IntelliJ IDEA.
  User: olatno
  Date: 18/11/2020
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="header.jsp" %>
</head>
<body ng-app="galleryApp">
<%@include file="nav.jsp" %>
<h1>Admin page for <span>${user.firstName}</span></h1>

<div class="container" ng-controller="galleryCtrl">
    <div class="row"  ng-repeat="image in imageData" >
        <div class="col-sm-4" ng-repeat="data in image">
            <div class="panel panel-primary">
                <div class="panel-heading">{{data[1]}}</div>
                <div class="panel-body"><img ng-src="data:image/png;base64,{{data[0]}}" class="img-responsive" style="width:100px; height:40px" alt="{{data[1]}}"></div>
                <div class="panel-footer">{{data[2]}}</div>
            </div>
        </div>
    </div>
<br><br>

    <div class="upload">
        <form ng-submit="uploadImage()" enctype="multipart/form-data">
            <div class="form-group">
                <label for="imageName">Image name</label>
                <input type="text" class="form-control" id="imageName" name='name' ng-model="data.name" placeholder="Image Name"/>
            </div>
            <div class="form-group">
                <label for="imageDescription">Image description</label>
                <input type="text" class="form-control" id="imageDescription" name='description' ng-model="data.description" placeholder="Image description"/>
            </div>
            <div class="form-group">
                <label for="imageFile">Upload image</label>
                <input type="file" id="imageFile" ng-model="data.image"  name="image">
            </div>
            <div>
                <input type="submit">
            </div>
            <p ng-show="{{message !== undefined}}">{{message}}</p>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>
    </div>
</footer>
</div>
</body>
</html>

