package cn.lanaya.business.service;

import cn.lanaya.business.entity.CommodityCat;
import cn.lanaya.business.vo.CommodityCatVO;
import cn.lanaya.common.bean.PageQO;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface CommodityCatService {
    int countByClause(CommodityCatVO record);

    int deleteByClause(CommodityCatVO record);

    int deleteByPrimaryKey(Long id);

    int insert(CommodityCat record);

    int insertSelective(CommodityCat record);

    List<CommodityCat> selectByClause(CommodityCat record);

    CommodityCat selectByPrimaryKey(Long id);

    int updateByClauseSelective(CommodityCat entity, CommodityCatVO param);

    int updateByPrimaryKeySelective(CommodityCat record);

    int updateByPrimaryKey(CommodityCat record);

    PageInfo<CommodityCatVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<CommodityCatVO> list);

    int insertSelectiveBatch(List<CommodityCat> list);
}