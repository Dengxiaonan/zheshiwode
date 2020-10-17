app.service('brandService', function ($http) {
    this.deleteBrand = function (selectIds) {
        return $http.post('../brand/deleteBrand.do', selectIds);
    }
    this.findOne = function (id) {
        return $http.get('../brand/findOne.do?id=' + id);
    }
    this.save = function (entity) {
        return $http.post('../brand/save.do', entity);
    }
    this.findAll = function () {
        return $http.get('../brand/findAll.do');
    }
    this.search = function (searchEntity, page, rows) {
        return $http.post("../brand/search.do?page=" + page + "&rows=" + rows, searchEntity);
    }
    this.findPage = function (page, rows) {
        return $http.get("../brand/findPage.do?page=" + page + "&rows=" + rows);
    }
    this.getBrandList = function () {
        return $http.get("../brand/getBrandList.do");
    }
});