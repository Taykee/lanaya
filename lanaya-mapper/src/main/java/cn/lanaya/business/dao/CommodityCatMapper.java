package cn.lanaya.business.dao;

import cn.lanaya.business.entity.CommodityCat;
import cn.lanaya.business.vo.CommodityCatVO;
import cn.lanaya.common.bean.PageQO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommodityCatMapper {
    int countByClause(CommodityCatVO record);

    int deleteByClause(@Param("param") CommodityCatVO record);

    int deleteByPrimaryKey(Long id);

    int insert(CommodityCat record);

    int insertSelective(CommodityCat record);

    List<CommodityCat> selectByClause(@Param("param") CommodityCat record);

    CommodityCat selectByPrimaryKey(Long id);

    int updateByClauseSelective(@Param("entity") CommodityCat entity, @Param("param") CommodityCatVO param);

    int updateByPrimaryKeySelective(@Param("entity") CommodityCat record);

    int updateByPrimaryKey(CommodityCat record);

    List<CommodityCatVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<CommodityCatVO> list);

    int insertSelectiveBatch(List<CommodityCat> list);
}