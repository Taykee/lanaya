package cn.lanaya.business.dao;

import cn.lanaya.business.entity.CommoditySpecRelate;
import cn.lanaya.business.vo.CommoditySpecRelateVO;
import cn.lanaya.common.bean.PageQO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommoditySpecRelateMapper {
    int countByClause(CommoditySpecRelateVO record);

    int deleteByClause(@Param("param") CommoditySpecRelateVO record);

    int deleteByPrimaryKey(Long id);

    int insert(CommoditySpecRelate record);

    int insertSelective(CommoditySpecRelate record);

    List<CommoditySpecRelate> selectByClause(@Param("param") CommoditySpecRelate record);

    CommoditySpecRelate selectByPrimaryKey(Long id);

    int updateByClauseSelective(@Param("entity") CommoditySpecRelate entity, @Param("param") CommoditySpecRelateVO param);

    int updateByPrimaryKeySelective(@Param("entity") CommoditySpecRelate record);

    int updateByPrimaryKey(CommoditySpecRelate record);

    List<CommoditySpecRelateVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<CommoditySpecRelateVO> list);

    int insertSelectiveBatch(List<CommoditySpecRelate> list);
}