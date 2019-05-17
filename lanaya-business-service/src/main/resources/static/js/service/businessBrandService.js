app.service('businessBrandService', function ($http) {
    this.pageByClause = function (entity) {
        return $http.post('../businessBrand/pageByClause', entity);
    };

    this.insertSelective = function (entity) {
        return $http.post('../businessBrand/insertSelective', entity);
    };

    this.updateByPrimaryKeySelective = function () {

        return $http.post('../businessBrand/updateByPrimaryKeySelective', $scope.entity);
    };

    this.selectByPrimaryKey = function (id) {
        return $http.post('../businessBrand/selectByPrimaryKey', json({id:id}));
    };
    this.deleteByPrimaryKeyBatch = function () {
        return $http.post('../businessBrand/deleteByPrimaryKeyBatch', '');
    };
});