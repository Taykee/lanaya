app.controller('businessBrandController', function ($scope, $controller, businessBrandService) {
    $controller('baseController', {$scope:$scope});

    $scope.pageByClause = function (entity) {
        businessBrandService.pageByClause(entity).success(function (response) {
            $scope.list = response.data;
        });
    };
    $scope.insertSelective = function (entity) {
        console.log(entity);
        businessBrandService.insertSelective(entity).success(function (response) {
            console.log(response);
        })
    };

    $scope.updateByPrimaryKeySelective = function () {
        businessBrandService.updateByPrimaryKeySelective(entity).success(function (response) {

        });
    };
    $scope.selectByPrimaryKey = function (entity) {
        businessBrandService.selectByPrimaryKey(entity).success(function (respones) {

        });
    }
    $scope.deleteByPrimaryKeyBatch = function (entity) {
        businessBrandService.deleteByPrimaryKeyBatch(entity).success(function (respones) {

        });
    }
});