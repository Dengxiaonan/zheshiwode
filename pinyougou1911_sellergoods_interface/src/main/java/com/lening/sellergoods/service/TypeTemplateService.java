package com.lening.sellergoods.service;


import com.lening.pojo.TbTypeTemplate;
import entity.PageResult;
import entity.Result;

/**
 * 创作时间：2020/8/22 14:23
 * 作者：李增强
 */
public interface TypeTemplateService {
    PageResult search(TbTypeTemplate tbTypeTemplate, int page, int rows);

    void dele(Long[] ids);

    void save(TbTypeTemplate tbTypeTemplate);


    TbTypeTemplate findOne(Long id);
}
