app.controller("sellerController", function ($scope, $controller, sellerService) {
    $controller("baseController", {$scope: $scope});
    $scope.search = function (searchEntity, page, rows) {
        sellerService.search(searchEntity, page, rows).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        })
    }
    $scope.findOne = function (sellerId) {
        sellerService.findOne(sellerId).success(function (response) {
            $scope.entity = response;
        })
    }
    $scope.updateStatus = function (sellerId, status) {
        sellerService.updateStatus(sellerId, status).success(function (response) {
            if (response.success) {
                $scope.reloadList();
            } else {
                alert(response.message);
            }
        });
    }
})