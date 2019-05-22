package cn.lanaya.business.dao;

import cn.lanaya.business.entity.CommoditySpec;
import cn.lanaya.business.vo.CommoditySpecVO;
import cn.lanaya.common.bean.PageQO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommoditySpecMapper {
    int countByClause(CommoditySpecVO record);

    int deleteByClause(@Param("param") CommoditySpecVO record);

    int deleteByPrimaryKey(Long id);

    int insert(CommoditySpec record);

    int insertSelective(CommoditySpec record);

    List<CommoditySpec> selectByClause(@Param("param") CommoditySpec record);

    CommoditySpec selectByPrimaryKey(Long id);

    int updateByClauseSelective(@Param("entity") CommoditySpec entity, @Param("param") CommoditySpecVO param);

    int updateByPrimaryKeySelective(@Param("entity") CommoditySpec record);

    int updateByPrimaryKey(CommoditySpec record);

    List<CommoditySpecVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<CommoditySpecVO> list);

    int insertSelectiveBatch(List<CommoditySpec> list);
}