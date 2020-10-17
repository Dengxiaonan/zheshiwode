app.service("sellerService", function ($http) {
    this.checkSellerId = function (sellerId) {
        return $http.get('../seller/checkSellerId.do?sellerId=' + sellerId);
    }
    this.add = function (entity) {
        return $http.post('../seller/save.do', entity);
    }
})