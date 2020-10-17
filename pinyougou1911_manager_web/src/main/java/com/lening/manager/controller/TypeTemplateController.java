package com.lening.manager.controller;

import com.lening.pojo.TbSpecification;
import com.lening.pojo.TbTypeTemplate;
import com.lening.sellergoods.service.TypeTemplateService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @date： 2020/8/22
 * @author：Dream
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
    @Resource
    private TypeTemplateService typeTemplateService;

    //根据id查询一个
    @RequestMapping("/findOne")
    public TbTypeTemplate findOne(Long id) {
        return typeTemplateService.findOne(id);
    }

    //添加
    @RequestMapping("/save")
    public Result save(@RequestBody TbTypeTemplate tbTypeTemplate) {
        try {
            typeTemplateService.save(tbTypeTemplate);
            return new Result(true, "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "编辑成功");
        }
    }

    //分页
    @RequestMapping("/search")
    public PageResult search(@RequestBody TbTypeTemplate tbTypeTemplate, int page, int rows) {
        PageResult search = typeTemplateService.search(tbTypeTemplate, page, rows);
        System.out.println(search);
        return search;
    }

    @RequestMapping("/del")
    public Result del(@RequestBody Long[] ids) {
        try {
            typeTemplateService.dele(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

}
