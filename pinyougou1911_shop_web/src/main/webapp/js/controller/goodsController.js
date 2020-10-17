app.controller("goodsController", function ($scope, $controller, goodsService, uploadService) {
    $controller('baseController', {$scope: $scope});
    $scope.save = function () {
        goodsService.save($scope.entity).success(function (response) {
            if (response.success) {
                alert('保存成功');
                $scope.entity = {};
                editor.html('');//清空富文本编辑器
            } else {
                alert(response.message);
            }
        })
    }
    $scope.uploadFile = function () {
        uploadService.uploadFile().success(function (response) {
            if (response.success) {
                $scope.image_entity.url = response.message;
            } else {
                alert(response.message);
            }
        });
    }
    $scope.entity = {goods: {}, goodsDesc: {itemImages: []}};//定义页面实体结构
    $scope.add_image_entity = function () {
        $scope.entity.goodsDesc.itemImages.push($scope.image_entity);
    }
})