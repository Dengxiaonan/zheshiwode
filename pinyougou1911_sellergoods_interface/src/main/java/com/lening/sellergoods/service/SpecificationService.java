package com.lening.sellergoods.service;

import com.lening.pojo.TbSpecification;
import com.lening.pojogroup.Specification;
import entity.PageResult;
import entity.Result;

import java.util.List;
import java.util.Map;

public interface SpecificationService {
    void dele(Long[] ids);

    PageResult search(TbSpecification tbSpecification, int page, int rows);

    void saven(Specification specification);

    Specification findOne(Long id);

    List<Map> getSpecList();

}
