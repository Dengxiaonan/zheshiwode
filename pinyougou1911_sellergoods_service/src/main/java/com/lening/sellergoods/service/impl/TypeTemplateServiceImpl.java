package com.lening.sellergoods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lening.mapper.TbTypeTemplateMapper;
import com.lening.pojo.TbTypeTemplate;
import com.lening.pojo.TbTypeTemplateExample;
import com.lening.sellergoods.service.TypeTemplateService;
import entity.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 创作时间：2020/8/22 14:24
 * 作者：李增强
 */
@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {
    @Resource
    private TbTypeTemplateMapper tbTypeTemplateMapper;

    @Override
    public PageResult search(TbTypeTemplate tbTypeTemplate, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbTypeTemplateExample example = new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();
        if (tbTypeTemplate != null) {
            if (tbTypeTemplate.getName() != null && !"".equals(tbTypeTemplate.getName())) {
                criteria.andNameLike("%" + tbTypeTemplate.getName() + "%");
            }
        }

        Page<TbTypeTemplate> tbTypeTemplates = (Page<TbTypeTemplate>) tbTypeTemplateMapper.selectByExample(example);
        return new PageResult(tbTypeTemplates.getTotal(), tbTypeTemplates.getResult());
    }

    @Override
    public void dele(Long[] ids) {
        if (ids != null && ids.length >= 1) {
            for (Long id : ids) {
                tbTypeTemplateMapper.deleteByPrimaryKey(id);
            }
        }
    }

    @Override
    public void save(TbTypeTemplate tbTypeTemplate) {
        if (tbTypeTemplate != null) {
            if (tbTypeTemplate.getId() != null) {
                tbTypeTemplateMapper.updateByPrimaryKeySelective(tbTypeTemplate);
            } else {
                tbTypeTemplateMapper.insertSelective(tbTypeTemplate);
            }
        }
    }

    @Override
    public TbTypeTemplate findOne(Long id) {
        return tbTypeTemplateMapper.selectByPrimaryKey(id);
    }
}
