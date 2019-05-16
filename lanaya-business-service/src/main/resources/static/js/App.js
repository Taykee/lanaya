/**
 * business module
 * @type {angular.Module}
 */
var app = angular.module('business', []);
    app.controller('businessController', function ($scope, $http) {
        $http.post('../businessBrand/pageByClause').success(function (response) {
            $scope.list = response;
        });
    });