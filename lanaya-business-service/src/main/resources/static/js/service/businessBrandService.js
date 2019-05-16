app.service('businessBrandService', function ($http) {
    this.pageByClause = function (entity) {
        return $http.post('../businessBrand/pageByClause', entity);
    }
});