app.controller('baseController', function ($scope) {
    $scope.selectIds = [];
    $scope.searchEntity = {};
    //分页控件配置
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 5,
        perPageOptions: [5, 10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();//重新加载
        }
    }
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {
            $scope.selectIds.push(id);
        } else {
            //先获取id在数组中的角标，在删除
            var index = $scope.selectIds.indexOf(id);
            $scope.selectIds.splice(index, 1);
        }
    }

    $scope.reloadList = function () {
        /**
         * $scope.paginationConf.currentPage当前页（默认是1）
         * $scope.paginationConf.itemsPerPage页面大小（默认是10）
         * $scope.searchEntity是条件查询的参数
         */
        //$scope.findPage($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
        $scope.search($scope.searchEntity, $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
    }
})