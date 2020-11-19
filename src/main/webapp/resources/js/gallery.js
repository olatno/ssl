/**
 * Created by olatu on 16/11/2020.
 */

var app  = angular.module('galleryApp',[]);


    app.controller('galleryCtrl', ['$scope','$http',  function ($scope,  $http) {

        $scope.data = {};
        $scope.message = '';
        $scope.imageData = [];

        $http({
            method: 'GET',
            url: '/getUserImages ',
        }).then(function (response){
            $scope.imageData.push(response.data);
        },function (error){
            $scope.message =  ' Gallery is empty';
        });

        $scope.uploadImage = function () {

                let formData = new FormData();
                let file = document.getElementById('imageFile').files[0];

                 formData.append('file', file);

                $http({
                    method: 'POST',
                    url: '/saveImage?name='+$scope.data.name+'&description='+$scope.data.description,
                    data:formData,
                    enctype: 'multipart/form-data',
                    headers: { 'Content-Type': undefined },
                    processData: false,
                    contentType: false
                }).then(function (response){
                    $scope.imageData = [];
                    $scope.imageData.push(response.data);
                    $scope.message =  'image has been saved successfully';
                },function (error){
                    $scope.message =  ' Image was not successful uploaded';
                });
            $scope.data = {};
            document.getElementById('imageFile').value = '';
        }

    }]);

