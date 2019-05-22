package cn.lanaya.business.dao;

import cn.lanaya.business.entity.Brand;
import cn.lanaya.business.vo.BrandVO;
import cn.lanaya.common.bean.PageQO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BrandMapper {
    int countByClause(BrandVO record);

    int deleteByClause(@Param("param") BrandVO record);

    int deleteByPrimaryKey(Long id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByClause(@Param("param") Brand record);

    Brand selectByPrimaryKey(Long id);

    int updateByClauseSelective(@Param("entity") Brand entity, @Param("param") BrandVO param);

    int updateByPrimaryKeySelective(@Param("entity") Brand record);

    int updateByPrimaryKey(Brand record);

    List<BrandVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<BrandVO> list);

    int insertSelectiveBatch(List<Brand> list);
}