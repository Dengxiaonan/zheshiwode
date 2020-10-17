package com.lening.sellergoods.service;


import com.lening.pojo.TbItemCat;

import java.util.List;

/**
 * 创作时间：2020/8/27 9:21
 * 作者：李增强
 */
public interface ItemCatService {
    List<TbItemCat> findByParentId(Long parentId);

    void deleteByIds(Long[] ids);
}
