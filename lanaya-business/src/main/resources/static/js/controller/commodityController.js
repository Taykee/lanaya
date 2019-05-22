app.controller('commodityController', function ($scope, $controller, commodityService) {
    $controller('baseController', {$scope:$scope});

    $scope.pageByClause = function () {
        console.log($scope.search);
        commodityService.pageByClause($scope.search).then(function (response) {
            $scope.list = response.data;
        });
    };
    $scope.insertSelective = function () {
        console.log($scope.entity);
        commodityService.insertSelective($scope.entity).then(function (response) {
            console.log(response);
        });
    };

    $scope.updateByPrimaryKeySelective = function () {
        commodityService.updateByPrimaryKeySelective(entity).then(function (response) {

        });
    };
    $scope.selectByPrimaryKey = function (entity) {
        commodityService.selectByPrimaryKey(entity).then(function (respones) {

        });
    }
    $scope.deleteByPrimaryKeyBatch = function (entity) {
        commodityService.deleteByPrimaryKeyBatch(entity).then(function (respones) {

        });
    }
});