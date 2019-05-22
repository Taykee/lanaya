app.service('brandService', function ($http) {
    this.pageByClause = function (entity) {
        return $http.post('../brand/pageByClause', entity);
    };

    this.insertSelective = function (entity) {
        return $http.post('../brand/insertSelective', entity);
    };

    this.updateByPrimaryKeySelective = function () {

        return $http.post('../brand/updateByPrimaryKeySelective', $scope.entity);
    };

    this.selectByPrimaryKey = function (id) {
        return $http.post('../brand/selectByPrimaryKey', json({id:id}));
    };
    this.deleteByPrimaryKeyBatch = function () {
        return $http.post('../brand/deleteByPrimaryKeyBatch', '');
    };
});