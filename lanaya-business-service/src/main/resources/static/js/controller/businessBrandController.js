app.controller('businessBrandController', function ($scope, $controller, businessBrandService) {
    $controller('baseController', {$scope:$scope});

    $scope.pageByClause = function () {
        console.log($scope.entity);
        businessBrandService.pageByClause($scope.entity).then(function (response) {
            $scope.list = response.data;
        });
    };
    $scope.insertSelective = function () {
        console.log($scope.entity);
        businessBrandService.insertSelective($scope.entity).then(function (response) {
            console.log(response);
        });
    };

    $scope.updateByPrimaryKeySelective = function () {
        businessBrandService.updateByPrimaryKeySelective(entity).then(function (response) {

        });
    };
    $scope.selectByPrimaryKey = function (entity) {
        businessBrandService.selectByPrimaryKey(entity).then(function (respones) {

        });
    }
    $scope.deleteByPrimaryKeyBatch = function (entity) {
        businessBrandService.deleteByPrimaryKeyBatch(entity).then(function (respones) {

        });
    }
});