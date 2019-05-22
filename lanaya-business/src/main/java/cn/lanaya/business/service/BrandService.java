package cn.lanaya.business.service;

import cn.lanaya.business.entity.Brand;
import cn.lanaya.business.vo.BrandVO;
import cn.lanaya.common.bean.PageQO;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface BrandService {
    int countByClause(BrandVO record);

    int deleteByClause(BrandVO record);

    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByClause(Brand record);

    Brand selectByPrimaryKey(Long id);

    int updateByClauseSelective(Brand entity, BrandVO param);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    PageInfo<BrandVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<BrandVO> list);

    int insertSelectiveBatch(List<Brand> list);
}