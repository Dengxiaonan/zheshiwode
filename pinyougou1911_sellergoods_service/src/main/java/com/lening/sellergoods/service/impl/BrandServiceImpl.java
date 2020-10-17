package com.lening.sellergoods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lening.mapper.TbBrandMapper;
import com.lening.pojo.TbBrand;
import com.lening.pojo.TbBrandExample;
import com.lening.sellergoods.service.BrandService;
import entity.PageResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @date： 2020/8/20
 * @author：Dream
 */
@Service
public class BrandServiceImpl implements BrandService {
    @Resource
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        return brandMapper.findAll();
    }

    @Override
    public PageResult findPage(int page, int rows) {
        PageHelper.startPage(page, rows);
        /**
         * 有两个接收结果的，page，pageinfo
         */
        Page tbBrands = (Page<TbBrand>) brandMapper.selectByExample(null);
        return new PageResult(tbBrands.getTotal(), tbBrands.getResult());

    }

    @Override
    public TbBrand findAll(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(TbBrand tbBrand) {
        if (tbBrand.getId() != null && tbBrand != null) {
            brandMapper.updateByPrimaryKeySelective(tbBrand);
        } else {
            brandMapper.insert(tbBrand);
        }
    }

    @Override
    public void deleteBrand(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                brandMapper.deleteByPrimaryKey(id);
            }
        }
    }

    @Override
    public PageResult search(TbBrand tbBrand, int page, int rows) {
        PageHelper.startPage(page, rows);
        /**
         * 有两个接收结果的，page，pageinfo
         */
        /**
         * example的使用，代码生成器生成的mapper里面有所有的方法，条件基本可以满足
         * 代码生成器生成的实体类，一个表对应两个实体类，一个就是普通的，一个example的
         */
        TbBrandExample example = new TbBrandExample();
        TbBrandExample.Criteria criteria = example.createCriteria();
        if (tbBrand != null) {
            if (tbBrand.getName() != null && !"".equals(tbBrand.getName())) {
                criteria.andNameLike("%" + tbBrand.getName() + "%");
            }
            if (tbBrand.getFirstChar() != null && !"".equals(tbBrand.getFirstChar())) {
                criteria.andFirstCharEqualTo(tbBrand.getFirstChar());
            }
        }
        Page tbBrands = (Page<TbBrand>) brandMapper.selectByExample(example);
        return new PageResult(tbBrands.getTotal(), tbBrands.getResult());
    }

    @Override
    public List<Map> getBrandList() {

        return brandMapper.getBrandList();
    }
}
