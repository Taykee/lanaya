app.controller('businessBrandController', function ($scope, $controller, businessBrandService) {
    $controller('baseController', {$scope:$scope});
    $scope.pageByClause = function (entity) {
        businessBrandService.pageByClause(entity).success(function (response) {
            $scope.list = response.data;
        });
    };
});