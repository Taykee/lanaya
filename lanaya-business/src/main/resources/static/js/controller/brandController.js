app.controller('brandController', function ($scope, $controller, brandService) {
    $controller('baseController', {$scope:$scope});

    $scope.pageByClause = function () {
        console.log($scope.search);
        brandService.pageByClause($scope.search).then(function (response) {
            $scope.list = response.data;
        });
    };
    $scope.insertSelective = function () {
        console.log($scope.entity);
        brandService.insertSelective($scope.entity).then(function (response) {
            console.log(response);
        });
    };

    $scope.updateByPrimaryKeySelective = function () {
        brandService.updateByPrimaryKeySelective(entity).then(function (response) {

        });
    };
    $scope.selectByPrimaryKey = function (entity) {
        brandService.selectByPrimaryKey(entity).then(function (respones) {

        });
    }
    $scope.deleteByPrimaryKeyBatch = function (entity) {
        brandService.deleteByPrimaryKeyBatch(entity).then(function (respones) {

        });
    }
});