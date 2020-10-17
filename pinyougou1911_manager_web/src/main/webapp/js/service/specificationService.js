app.service('specificationService', function ($http) {
    this.dele = function (selectids) {
        return $http.post("../specification/dele.do", selectids);
    }
    this.search = function (searchEntity, page, rows) {
        return $http.post('../specification/search.do?rows=' + rows + "&page=" + page, searchEntity);
    }
    this.save = function (entity) {
        return $http.post('../specification/save.do', entity);
    }
    this.findOne = function (id) {
        return $http.get('../specification/findOne.do?id=' + id);
    }
    this.getSpecList = function () {
        return $http.get('../specification/getSpecList.do');
    }
});