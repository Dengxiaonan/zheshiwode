package com.lening.shop.controller;

import com.lening.pojo.TbSeller;
import com.lening.sellergoods.service.SellerService;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @date： 2020/8/25
 * @author：Dream
 */
@RestController
@RequestMapping("/seller")
public class SellerController {
    @Resource
    private SellerService sellerService;

    @RequestMapping("/checkSellerId")
    public Result checkSellerId(String sellerId) {
        int count = sellerService.getSellerIdCount(sellerId);
        System.out.println(count);
        if (count >= 1) {
            return new Result(false, "账户已存在");
        }
        return new Result(true, "可以使用");
    }

    @RequestMapping("/save")
    public Result save(@RequestBody TbSeller tbSeller) {
        try {
            sellerService.save(tbSeller);
            return new Result(true, "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "编辑失败");
        }
    }
}
