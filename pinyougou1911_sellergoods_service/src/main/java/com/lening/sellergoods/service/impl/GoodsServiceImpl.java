package com.lening.sellergoods.service.impl;


import com.lening.mapper.TbGoodsDescMapper;
import com.lening.mapper.TbGoodsMapper;
import com.lening.pojogroup.Goods;
import com.lening.sellergoods.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @date： 2020/9/7
 * @author：Dream
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private TbGoodsMapper tbGoodsMapper;
    @Resource
    private TbGoodsDescMapper tbGoodsDescMapper;

    @Override
    public void save(Goods goods) {
        if (goods != null && goods.getGoods() != null) {
            if (goods.getGoods().getId() != null) {
                //修改
            } else {
                /**
                 * 新增，增加商品的状态为未申请，这个在数据库设置默认值
                 * 不管在哪里设置，都需要提交git
                 */
                goods.getGoods().setAuditStatus("0");
                tbGoodsMapper.insert(goods.getGoods());
                goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
                tbGoodsDescMapper.insert(goods.getGoodsDesc());
            }
        }
    }
}
