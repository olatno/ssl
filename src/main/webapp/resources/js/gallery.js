/**
 * Created by olatu on 16/11/2020.
 */

var app  = angular.module('galleryApp',[]);


    app.controller('galleryCtrl', ['$scope','$http',  function ($scope,  $http) {
        let vm = this;
        $scope.data = {};
        $scope.message = '';
        $scope.imageData = [];
        $scope.buttonAdminAction = false;
        $scope.selected = {};
        $scope.imageSelected = {};
        $scope.openImageData = {};
        vm.checkedImage = '';

        vm.csrfHeader = document.querySelector('meta[name="_csrf_header"]').content;
        vm.csrfToken = document.querySelector('meta[name="_csrf"]').content;

        $http({
            method: 'GET',
            url: '/getUserImages ',
        }).then(function (response){
            if(response.data.length !== 0){
                $scope.buttonAdminAction = true;
            }
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
                    multipart: true,
                    headers: { 'Content-Type': undefined,
                               'X-CSRF-TOKEN' : vm.csrfToken
                            },
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
            if($scope.data.length !== 0){
                $scope.buttonAdminAction = true;
            }
        }
        $scope.checkedCheckboxValue = function (){
            vm.checkedImage = $scope.imageSelected.data;
        }

        $scope.deleteImage = function (){
            let imageId = vm.checkedImage;
            if(imageId !== ''){
                $http({
                    method: 'POST',
                    url: '/deleteImage ',
                    headers: {
                        'X-CSRF-TOKEN': vm.csrfToken,
                        'Content-Type':'application/x-www-form-urlencoded'
                    },
                    data: "imageId="+imageId,
                }).then(function (response){
                    $scope.imageData = [];
                    $scope.imageData.push(response.data);
                    if(response.data.length === 0){
                        $scope.buttonAdminAction = false;
                    }
                },function (error){
                    $scope.deleteMessage =  ' Image is not deleted';
                });
            }
        }

        $scope.deleteGallery = function (){
            $http({
                method: 'GET',
                url: '/deleteGallery ',
                }).then(function (response){
                    $scope.imageData = [];
                    $scope.imageData.push(response.data);
                    $scope.buttonAdminAction = false;
                },function (error){
                    $scope.deleteMessage =  ' Gallery is not deleted';
            });
        }

        $scope.openImage = function (image, name, description, id ){
            $scope.openImageData.image = image;
            $scope.openImageData.name = name;
            $scope.openImageData.description = description;
            $scope.openImageData.id = id;
            $('#myModal').modal('show');
        }
        $scope.saveChanges = function (){
            if($scope.openImageData.name !== '' || $scope.openImageData.description !== ''){
                $scope.imageSelected['id'] = $scope.openImageData.id;
                $scope.imageSelected['name'] = $scope.openImageData.name;
                $scope.imageSelected['description'] = $scope.openImageData.description;
                $http({
                    method: 'POST',
                    url: '/editImage ',
                    headers: {
                        'X-CSRF-TOKEN': vm.csrfToken,
                        'Content-Type':'application/json'
                    },
                    data: JSON.stringify($scope.imageSelected),
                }).then(function (response){
                    $scope.imageData = [];
                    $scope.imageData.push(response.data);
                    if(response.data.length === 0){
                        $scope.buttonAdminAction = false;
                    }
                },function (error){
                    $scope.deleteMessage =  ' Image is not edited';
                });
            }
        }
}]);

