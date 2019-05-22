package cn.lanaya.business.dao;

import cn.lanaya.business.entity.CommodityDesc;
import cn.lanaya.business.vo.CommodityDescVO;
import cn.lanaya.common.bean.PageQO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommodityDescMapper {
    int countByClause(CommodityDescVO record);

    int deleteByClause(@Param("param") CommodityDescVO record);

    int insert(CommodityDesc record);

    int insertSelective(CommodityDesc record);

    List<CommodityDesc> selectByClause(@Param("param") CommodityDesc record);

    int updateByClauseSelective(@Param("entity") CommodityDesc entity, @Param("param") CommodityDescVO param);

    int updateByPrimaryKeySelective(@Param("entity") CommodityDesc record);

    int updateByPrimaryKey(CommodityDesc record);

    List<CommodityDescVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<CommodityDescVO> list);

    int insertSelectiveBatch(List<CommodityDesc> list);
}