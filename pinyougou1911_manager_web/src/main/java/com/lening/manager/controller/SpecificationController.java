package com.lening.manager.controller;

import com.lening.pojo.TbSpecification;
import com.lening.pojogroup.Specification;
import com.lening.sellergoods.service.SpecificationService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @date： 2020/8/21
 * @author：Dream
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {
    @Resource
    private SpecificationService specificationService;

    //查询全部品牌
    @RequestMapping("/getSpecList")
    public List<Map> getSpecList() {
        return specificationService.getSpecList();

    }

    //单个查询
    @RequestMapping("/findOne")
    public Specification findOne(Long id) {
        return specificationService.findOne(id);
    }


    //添加
    @RequestMapping("/save")
    public Result save(@RequestBody Specification specification) {
        try {
            specificationService.saven(specification);
            return new Result(true, "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, "编辑失败");
        }

    }


    @RequestMapping("/search")
    public PageResult search(@RequestBody TbSpecification tbSpecification, int page, int rows) {
        return specificationService.search(tbSpecification, page, rows);
    }

    @RequestMapping("/dele")
    public Result dele(@RequestBody Long[] ids) {
        try {
            System.out.println("这里是id+" + ids);
            specificationService.dele(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }
}
