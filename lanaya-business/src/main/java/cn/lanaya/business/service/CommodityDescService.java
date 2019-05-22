package cn.lanaya.business.service;

import cn.lanaya.business.entity.CommodityDesc;
import cn.lanaya.business.vo.CommodityDescVO;
import cn.lanaya.common.bean.PageQO;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface CommodityDescService {
    int countByClause(CommodityDescVO record);

    int deleteByClause(CommodityDescVO record);

    int insert(CommodityDesc record);

    int insertSelective(CommodityDesc record);

    List<CommodityDesc> selectByClause(CommodityDesc record);

    int updateByClauseSelective(CommodityDesc entity, CommodityDescVO param);

    int updateByPrimaryKeySelective(CommodityDesc record);

    int updateByPrimaryKey(CommodityDesc record);

    PageInfo<CommodityDescVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<CommodityDescVO> list);

    int insertSelectiveBatch(List<CommodityDesc> list);
}