package cn.lanaya.business.dao;

import cn.lanaya.business.entity.BusinessBrand;
import cn.lanaya.business.vo.BusinessBrandVO;
import cn.lanaya.common.bean.PageQO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessBrandMapper {
    int countByClause(BusinessBrandVO record);

    int deleteByClause(@Param("param") BusinessBrandVO record);

    int deleteByPrimaryKey(Long id);

    int insert(BusinessBrand record);

    int insertSelective(BusinessBrand record);

    List<BusinessBrand> selectByClause(@Param("param") BusinessBrand record);

    BusinessBrand selectByPrimaryKey(Long id);

    int updateByClauseSelective(@Param("entity") BusinessBrand entity, @Param("param") BusinessBrandVO param);

    int updateByPrimaryKeySelective(@Param("entity") BusinessBrand record);

    int updateByPrimaryKey(BusinessBrand record);

    List<BusinessBrandVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<BusinessBrandVO> list);

    int insertSelectiveBatch(List<BusinessBrand> list);
}