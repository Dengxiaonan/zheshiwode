package com.lening.sellergoods.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lening.mapper.TbSpecificationMapper;
import com.lening.mapper.TbSpecificationOptionMapper;
import com.lening.pojo.TbSpecification;
import com.lening.pojo.TbSpecificationExample;
import com.lening.pojo.TbSpecificationOption;
import com.lening.pojo.TbSpecificationOptionExample;
import com.lening.pojogroup.Specification;
import com.lening.sellergoods.service.SpecificationService;
import entity.PageResult;
import entity.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @date： 2020/8/21
 * @author：Dream
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Resource
    private TbSpecificationMapper specificationMapper;
    @Resource
    private TbSpecificationOptionMapper specificationOptionMapper;

    @Override
    public void dele(Long[] ids) {
        if (ids != null && ids.length >= 1) {
            for (Long id : ids) {
                /**
                 * 把规格删除了，但是规格选项需要一起删掉，但是规格选项在另外一个mapper里面，导入规格选项
                 */
                specificationMapper.deleteByPrimaryKey(id);
                /**
                 * 按照id删除，直接调用deleteByPrimaryKey这个方法
                 * 按照其他字段删除，要是不是自动生成的mapper，自己直接去写，
                 * 在自动生成的里面也支持按照某个字段就行删除，和条件查询很相似
                 * 删除也需要写一个example
                 */
                /**
                 * 要删除的是规格选项，所以要写的是规格选项的example
                 */
                TbSpecificationOptionExample example = new TbSpecificationOptionExample();
                TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
                criteria.andSpecIdEqualTo(id);
                specificationOptionMapper.deleteByExample(example);
            }
        }

    }

    @Override
    public PageResult search(TbSpecification tbSpecification, int page, int rows) {
        PageHelper.startPage(page, rows);
        TbSpecificationExample example = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
        if (tbSpecification != null) {
            if (tbSpecification.getSpecName() != null && !"".equals(tbSpecification.getSpecName())) {
                criteria.andSpecNameLike("%" + tbSpecification.getSpecName() + "%");
            }
        }
        Page<TbSpecification> pages = (Page<TbSpecification>) specificationMapper.selectByExample(example);
        return new PageResult(pages.getTotal(), pages.getResult());
    }

    @Override
    public void saven(Specification specification) {
        if (specification != null && specification.getSpecification() != null) {
            if (specification.getSpecification().getId() != null) {
                /**
                 * 修改,规格直接修改，规格选项，先删除后新增
                 */
                specificationMapper.updateByPrimaryKey(specification.getSpecification());

                /**
                 * 删除规格选项按照规格id删除，不是主键，属于条件删除，需要使用example
                 */
                TbSpecificationOptionExample example = new TbSpecificationOptionExample();
                TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
                criteria.andSpecIdEqualTo(specification.getSpecification().getId());
                specificationOptionMapper.deleteByExample(example);

                /**
                 * 删除按照规格id一次删除多个
                 * 但是新增需要一个一个的新增
                 */
                if (specification.getSpecificationOptionList() != null && specification.getSpecificationOptionList().size() >= 1) {
                    for (TbSpecificationOption option : specification.getSpecificationOptionList()) {
                        option.setSpecId(specification.getSpecification().getId());
                        specificationOptionMapper.insert(option);
                    }
                }


            } else {
                /**
                 * 先新增规格，然后把规格的id带回来，在新增规格选项
                 * 新增后把数据库新增的id拿回来，我们获取的方式在xml中配置
                 * <selectKey resultType="java.lang.Long" order="AFTER" keyProperty="id">
                 * 		SELECT LAST_INSERT_ID() AS id
                 * 	</selectKey>
                 *
                 * 	useGeneratedKeys="true" keyProperty="id" keyColumn="id",直接写在insert 标签上
                 */

                specificationMapper.insert(specification.getSpecification());
                List<TbSpecificationOption> optionList = specification.getSpecificationOptionList();
                if (optionList != null && optionList.size() >= 1) {
                    for (TbSpecificationOption specificationOption : optionList) {
                        specificationOption.setSpecId(specification.getSpecification().getId());
                        specificationOptionMapper.insert(specificationOption);
                    }
                }
            }
        }
    }

    @Override
    public Specification findOne(Long id) {
        Specification specification = new Specification();
        /**
         * 传递过来的id是规格的id，所以去查询规格，直接按照id查询就OK啦
         */
        TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
        specification.setSpecification(tbSpecification);

        /**
         * 查询规格选项，但是没有规格选项的id，只有规格的id，用sql直接查询就OK啦，但是，自动生成的代码查询，
         * 就需要使用example查询
         */
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);

        List<TbSpecificationOption> options = specificationOptionMapper.selectByExample(example);
        specification.setSpecificationOptionList(options);
        return specification;
    }

    @Override
    public List<Map> getSpecList() {
        return specificationMapper.selectOptionList();
    }

}
