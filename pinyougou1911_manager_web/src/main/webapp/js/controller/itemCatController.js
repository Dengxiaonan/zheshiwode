//控制层
app.controller('itemCatController', function ($scope, $controller, itemCatService) {

    $controller('baseController', {$scope: $scope});//继承
    $scope.deleteByIds = function () {
        itemCatService.deleteByIds($scope.selectIds).success(function (response) {
            if (response.success) {
                if ($scope.grade == 1) {
                    $scope.findByParentId(0);
                } else if ($scope.grade == 2) {
                    $scope.findByParentId($scope.entity_1.id);
                } else if ($scope.grade == 3) {
                    $scope.findByParentId($scope.entity_2.id);
                }
            } else {
                alert(response.message);
            }
        })
    }
    $scope.grade = 1;//默认为1级
    //设置级别,直接替换grade的
    $scope.setGrade = function (value) {
        $scope.grade = value;
    }
    /**
     *定义两个实体，用来记录一级的实体和二级的实体
     */
    $scope.entity_1 = null;
    $scope.entity_2 = null;

    $scope.selectList = function (entity) {
        if ($scope.grade == 1) {
            $scope.entity_1 = null;
            $scope.entity_2 = null;
        } else if ($scope.grade == 2) {
            $scope.entity_1 = entity;
            $scope.entity_2 = null;
        } else if ($scope.grade == 3) {
            $scope.entity_2 = entity;
        }
    }

    $scope.findByParentId = function (parentId) {
        itemCatService.findByParentId(parentId).success(function (response) {
            $scope.list = response;
        })

    }
});	
