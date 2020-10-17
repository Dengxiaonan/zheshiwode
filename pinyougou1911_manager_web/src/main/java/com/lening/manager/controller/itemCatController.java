package com.lening.manager.controller;

import com.lening.pojo.TbItemCat;
import com.lening.sellergoods.service.ItemCatService;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @date： 2020/8/27
 * @author：Dream
 */
@RestController
@RequestMapping("/itemCat")
public class itemCatController {
    @Resource
    private ItemCatService itemCatService;

    @RequestMapping("/deleteByIds")
    public Result deleteByIds(@RequestBody Long[] ids) {
        try {
            itemCatService.deleteByIds(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            System.out.println("hjfjfh");
            return new Result(false, "删除失败");
        }
    }

    @RequestMapping("/findByParentId")
    public List<TbItemCat> findByParentId(Long parentId) {
        System.out.println(parentId);
        List<TbItemCat> byParentId = itemCatService.findByParentId(parentId);
        System.out.println(byParentId);
        return byParentId;
    }
}
