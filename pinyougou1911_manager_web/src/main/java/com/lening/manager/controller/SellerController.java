package com.lening.manager.controller;

import com.lening.pojo.TbSeller;
import com.lening.sellergoods.service.SellerService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @date： 2020/8/26
 * @author：Dream
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
    @Resource
    public SellerService sellerService;

    //审核
    @RequestMapping("/updateStatus")
    public Result updateStatus(String sellerId, String status) {
        try {
            sellerService.updateStatus(sellerId, status);
            return new Result(true, "审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "审核失败");
        }
    }

    //查询单个
    @RequestMapping("/findOne")
    public TbSeller findOne(String sellerId) {
        TbSeller one = sellerService.findOne(sellerId);

        return one;
    }

    @RequestMapping("/search")
    public PageResult search(@RequestBody TbSeller tbSeller, int rows, int page) {
        return sellerService.search(tbSeller, page, rows);
    }
}
