package cn.lanaya.business.dao;

import cn.lanaya.business.entity.Commodity;
import cn.lanaya.business.vo.CommodityVO;
import cn.lanaya.common.bean.PageQO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommodityMapper {
    int countByClause(CommodityVO record);

    int deleteByClause(@Param("param") CommodityVO record);

    int deleteByPrimaryKey(Long id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    List<Commodity> selectByClause(@Param("param") Commodity record);

    Commodity selectByPrimaryKey(Long id);

    int updateByClauseSelective(@Param("entity") Commodity entity, @Param("param") CommodityVO param);

    int updateByPrimaryKeySelective(@Param("entity") Commodity record);

    int updateByPrimaryKey(Commodity record);

    List<CommodityVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<CommodityVO> list);

    int insertSelectiveBatch(List<Commodity> list);
}