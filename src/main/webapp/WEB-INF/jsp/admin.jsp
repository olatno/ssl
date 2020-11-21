<%--
  Created by IntelliJ IDEA.
  User: olatno
  Date: 18/11/2020
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">

<%@include file="header.jsp" %>

<body ng-app="galleryApp">
<%@include file="nav.jsp" %>
<h1>Admin page for <span>${user.firstName}</span></h1>

<div class="container" ng-controller="galleryCtrl">
    <div class="row"  ng-repeat="image in imageData" >
        <div class="col-sm-4" ng-repeat="data in image">
            <div class="panel panel-primary" >
                <div class="panel-heading" ng-model="imageSelected.title" >{{data[1]}}</div>
                <div class="panel-body" ng-model="imageSelected.image" ng-click="openImage(data[0], data[1], data[2], data[4])">
                    <img ng-src="data:image/png;base64,{{data[0]}}" class="img-responsive" style="width:100px; height:40px" alt="{{data[1]}}"></div>
                <div class="panel-footer" image-identity="{{data[2]}}" ng-model="imageSelected.description">{{data[2]}}
                    <input name="image" type="checkbox" ng-model="imageSelected.data" ng-true-value="{{data[4]}}" ng-false-value="" value="{{data[4]}}" ng-change="checkedCheckboxValue()" > </div>
            </div>
        </div>
    </div>
    <div class="ng-hide" ng-show="buttonAdminAction">
        <input type="submit" value="Delete image" name="deleteImage" ng-click="deleteImage()">
        <input type="submit" value="Delete gallery" name="deleteGallery" ng-click="deleteGallery()">
    </div>
    <hr>
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
        </form>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <input type="text" class="modal-title" ng-model="openImageData.name" value="{{openImageData.name}}" placeholder="Change image title"/>
                </div>
                <div class="modal-body">
                    <img ng-src="data:image/png;base64,{{openImageData.image}}" style="width: 400px; height: 400px">
                    <input type="text" class="description" ng-model="openImageData.description" value="{{openImageData.description}}" placeholder="Change image description" />
                    <input type="hidden" ng-model="openImageData.data"  value="{{openImageData.data}}"/>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="saveChanges()">Save</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal" >Close</button>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>

