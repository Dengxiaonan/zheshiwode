package com.lening.manager.controller;

import com.lening.pojo.TbBrand;
import com.lening.sellergoods.service.BrandService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Resource
    private BrandService brandService;

    @RequestMapping("/getBrandList")
    public List<Map> getBrandList() {
        return brandService.getBrandList();
    }

    //批量删除
    @RequestMapping("/deleteBrand")
    public Result deleteBrand(@RequestBody Long[] ids) {
        try {
            brandService.deleteBrand(ids);
            return new Result(true, "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");

        }
    }


    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        List<TbBrand> list = brandService.findAll();
        return list;
    }
    //分页

    //按照id查询品牌对象
    @RequestMapping("/findOne")
    public TbBrand findOne(Long id) {
        return brandService.findAll(id);
    }

    //添加品牌
    @RequestMapping("/save")
    public Result save(@RequestBody TbBrand tbBrand) {
        try {
            brandService.save(tbBrand);
            return new Result(false, "编辑成功");
        } catch (Exception e) {
            return new Result(false, "编辑失败");
        }
    }

    //分页+模糊查询
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbBrand tbBrand, int page, int rows) {
        PageResult page1 = brandService.search(tbBrand, page, rows);
        return page1;
    }
}
