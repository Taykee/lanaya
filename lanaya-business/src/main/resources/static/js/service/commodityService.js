app.service('commodityService', function ($http) {
    this.pageByClause = function (entity) {
        return $http.post('../commodity/pageByClause', entity);
    };

    this.insertSelective = function (entity) {
        return $http.post('../commodity/insertSelective', entity);
    };

    this.updateByPrimaryKeySelective = function () {

        return $http.post('../commodity/updateByPrimaryKeySelective', $scope.entity);
    };

    this.selectByPrimaryKey = function (id) {
        return $http.post('../commodity/selectByPrimaryKey', json({id:id}));
    };
    this.deleteByPrimaryKeyBatch = function () {
        return $http.post('../commodity/deleteByPrimaryKeyBatch', '');
    };
});