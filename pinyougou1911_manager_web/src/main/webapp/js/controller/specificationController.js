app.controller("specificationController", function ($scope, $controller, specificationService) {
    $controller("baseController", {$scope: $scope});
    $scope.search = function (searchEntity, page, rows) {
        specificationService.search(searchEntity, page, rows).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        });
    }
    $scope.dele = function () {
        specificationService.dele($scope.selectIds).success(function (response) {
            if (response.success) {
                $scope.reloadList();
            } else {
                alert(response.message);
            }
        })
    }
    $scope.addTableRow = function () {
        $scope.entity.specificationOptionList.push({});
    }
    $scope.deleTableRow = function (index) {
        $scope.entity.specificationOptionList.splice(index, 1);
    }
    $scope.save = function () {
        specificationService.save($scope.entity).success(function (response) {
            if (response.success) {
                $scope.reloadList();
            } else {
                alert(response.message);
            }
        })
    }
    $scope.findOne = function (id) {
        specificationService.findOne(id).success(function (response) {
            $scope.entity = response;
        })
    }

})