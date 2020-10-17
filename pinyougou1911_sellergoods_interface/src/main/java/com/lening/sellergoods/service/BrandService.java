package com.lening.sellergoods.service;

import com.lening.pojo.TbBrand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService {
    List<TbBrand> findAll();

    PageResult findPage(int page, int rows);

    TbBrand findAll(Long id);

    void save(TbBrand tbBrand);

    void deleteBrand(Long[] ids);

    PageResult search(TbBrand tbBrand, int page, int rows);

    List<Map> getBrandList();

}
