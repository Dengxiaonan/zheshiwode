app.controller("typeTemplateController", function ($scope, $controller, typeTemplateService, brandService, specificationService) {
    $controller("baseController", {$scope: $scope});
    $scope.findOne = function (id) {
        typeTemplateService.findOne(id).success(function (response) {
            $scope.entity = response;
            $scope.entity.brandIds = JSON.parse($scope.entity.brandIds);
            $scope.entity.specIds = JSON.parse($scope.entity.specIds);
            /**
             * 修改的时候，方式这个为空，在转json的时候，异常了，导致出现问题
             */
            if ($scope.entity.customAttributeItems == null) {
                $scope.entity.customAttributeItems = [];
            } else {
                $scope.entity.customAttributeItems = JSON.parse($scope.entity.customAttributeItems);
            }
        })
    }
    $scope.save = function () {
        typeTemplateService.save($scope.entity).success(function (response) {
            if (response.success) {
                $scope.reloadList();
            } else {
                alert(response.message);
            }
        })
    }
    $scope.specList = function () {
        specificationService.getSpecList().success(function (response) {
            alert(response);
            $scope.specList = {data: response};
        })
    }
    $scope.brandList = function () {
        brandService.getBrandList().success(function (response) {

            $scope.brandList = {data: response};
        })
    }
    $scope.search = function (searchEntity, page, rows) {
        typeTemplateService.search(searchEntity, page, rows).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        })
    }
    $scope.jsonToString = function (jsonString, key) {
        //我们要把我们的json的字符串转成json数组
        var json = JSON.parse(jsonString);
        //最后要返回一个字符串
        var rs = "";
        for (var x = 0; x < json.length; x++) {
            if (x > 0) {
                rs += ",";
            }
            rs += json[x][key];
        }
        return rs;
    }

    $scope.addTableRow = function () {
        $scope.entity.customAttributeItems.push({});
    }
    $scope.deleTableRow = function (index) {
        $scope.entity.customAttributeItems.splice(index, 1);
    }
    $scope.dele = function () {
        typeTemplateService.dele($scope.selectIds).success(function (response) {
            if (response.success) {
                $scope.reloadList();
            } else {
                alert(response.message);
            }
        });
    }

})