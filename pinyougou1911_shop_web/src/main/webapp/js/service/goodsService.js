app.service('goodsService', function ($http) {
    this.save = function (entity) {
        return $http.post('../goods/save.do', entity);
    }
})