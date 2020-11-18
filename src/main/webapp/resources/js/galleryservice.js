/**
 * Created by olatu on 03/12/2016.
 */

    var app  = angular.module('GalleryServiceObj',[]);

    app.service('GalleryService', ['$http', function ($http) {

       let galleryServices = function(imageObject) {
           let req = {
               method: 'POST',
               url: '/json/gallery/imageUpload',
               data: JSON.stringify(imageObject)
           };

           return $http(req)
                  .then(function(response) {
                    return response;
           });
       }

        return {
            galleryServices:galleryServices
        };
    }]);