//服务层
app.service('itemCatService', function ($http) {
    this.findByParentId = function (parentId) {
        return $http.get('../itemCat/findByParentId.do?parentId=' + parentId);
    }
    this.deleteByIds = function (selectIds) {
        return $http.post('../itemCat/deleteByIds.do', selectIds);
    }
});
