package cn.lanaya.business.service;

import cn.lanaya.business.entity.Commodity;
import cn.lanaya.business.vo.CommodityVO;
import cn.lanaya.common.bean.PageQO;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface CommodityService {
    int countByClause(CommodityVO record);

    int deleteByClause(CommodityVO record);

    int deleteByPrimaryKey(Long id);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    List<Commodity> selectByClause(Commodity record);

    Commodity selectByPrimaryKey(Long id);

    int updateByClauseSelective(Commodity entity, CommodityVO param);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);

    PageInfo<CommodityVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<CommodityVO> list);

    int insertSelectiveBatch(List<Commodity> list);
}