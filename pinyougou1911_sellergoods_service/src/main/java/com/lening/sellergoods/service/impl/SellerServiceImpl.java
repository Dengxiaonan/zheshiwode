package com.lening.sellergoods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lening.mapper.TbSellerMapper;
import com.lening.pojo.TbSeller;
import com.lening.pojo.TbSellerExample;
import com.lening.sellergoods.service.SellerService;
import entity.PageResult;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @date： 2020/8/25
 * @author：Dream
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Resource
    public TbSellerMapper tbSellerMapper;

    @Override
    public int getSellerIdCount(String sellerId) {
        System.out.println(sellerId);
        return tbSellerMapper.getSellerIdCount(sellerId);
    }

    @Override
    public void save(TbSeller tbSeller) {
        if (tbSeller != null) {
            //商家状态
            tbSeller.setStatus("0");
            tbSeller.setCreateTime(new Date());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String password = passwordEncoder.encode(tbSeller.getPassword());
            tbSeller.setPassword(password);
            tbSellerMapper.insertSelective(tbSeller);
        }
    }

    @Override
    public PageResult search(TbSeller tbSeller, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbSellerExample example = new TbSellerExample();
        TbSellerExample.Criteria criteria = example.createCriteria();

        if (tbSeller != null) {
            if (tbSeller.getStatus() != null && !"".equals(tbSeller.getStatus())) {
                criteria.andStatusEqualTo(tbSeller.getStatus());
            }
            if (tbSeller.getName() != null && !"".equals(tbSeller.getName())) {
                criteria.andNameLike("%" + tbSeller.getName() + "%");
            }
            if (tbSeller.getNickName() != null && !"".equals(tbSeller.getNickName())) {
                criteria.andNickNameLike("%" + tbSeller.getNickName() + "%");
            }
        }
        Page<TbSeller> pageinfo = (Page<TbSeller>) tbSellerMapper.selectByExample(example);
        return new PageResult(pageinfo.getTotal(), pageinfo.getResult());
    }

    @Override
    public TbSeller findOne(String sellerId) {
        return tbSellerMapper.selectByPrimaryKey(sellerId);
    }

    @Override
    public void updateStatus(String sellerId, String status) {
        tbSellerMapper.updateStatus(sellerId, status);
    }


}
