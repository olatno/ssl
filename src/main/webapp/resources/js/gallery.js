/**
 * Created by olatu on 16/11/2020.
 */
//(function() {

    var app  = angular.module('galleryApp',[]);


    app.controller('galleryCtrl', ['$scope','$http',  function ($scope,  $http) {

        $scope.data = {};
        $scope.message = '';
        $scope.image = {};

        $scope.uploadImage = function () {

                var formData = new FormData();
                var file = document.getElementById('imageFile').files[0];

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
                    $scope.message = response.data +' image has been saved successfully';
                },function (error){
                    $scope.message =  ' Image was not successful uploaded';
                });
        }
    }]);

//}());
