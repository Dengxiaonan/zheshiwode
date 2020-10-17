package com.lening.sellergoods.service.impl;

import com.lening.mapper.TbItemCatMapper;
import com.lening.pojo.TbItemCat;
import com.lening.pojo.TbItemCatExample;
import com.lening.sellergoods.service.ItemCatService;
import org.junit.Test;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @date： 2020/8/27
 * @author：Dream
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Resource
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<TbItemCat> findByParentId(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return tbItemCatMapper.selectByExample(example);

    }

    List<Long> idss = null;

    @Override
    public void deleteByIds(Long[] ids) {
        idss = new ArrayList();
        /**
         * 告诉一个id，我想查询这个id还有没有子集
         * 递归
         */
        if (ids != null) {
            for (Long id : ids) {
                getIdsById(id);
            }
        }
        if (idss.size() >= 1) {
            for (Long o : idss) {
                tbItemCatMapper.deleteByPrimaryKey(o);
            }
        }
    }

    private void getIdsById(Long id) {

        idss.add(id);

        /**
         * 先那这个这个id去查询，看看有没有孩子，没有孩子直接返回，要是有孩子，自己调用自己
         */
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        if (list != null && list.size() >= 1) {
            for (TbItemCat cat : list) {
                getIdsById(cat.getId());
            }
        }
    }
}
