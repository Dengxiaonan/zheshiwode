package com.lening.sellergoods.service;

import com.lening.pojo.TbSeller;
import entity.PageResult;

public interface SellerService {


    int getSellerIdCount(String sellerId);

    void save(TbSeller tbSeller);

    PageResult search(TbSeller tbSeller, int page, int rows);


    TbSeller findOne(String sellerId);

    void updateStatus(String sellerId, String status);
}
