package com.lening.mapper;

import com.lening.pojo.TbBrand;
import com.lening.pojo.TbBrandExample;

import java.util.List;
import java.util.Map;

/**
 * @date： 2020/8/20
 * @author：Dream
 */
public interface TbBrandMapper {
    //denxgao
    List<TbBrand> findAll();

    List<TbBrand> selectByExample(TbBrandExample example);

    TbBrand selectByPrimaryKey(Long id);

    int insert(TbBrand tbBrand);

    int updateByPrimaryKeySelective(TbBrand tbBrand);

    int deleteByPrimaryKey(Long id);

    List<Map> getBrandList();

}
