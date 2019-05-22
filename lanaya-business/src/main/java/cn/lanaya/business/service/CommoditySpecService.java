package cn.lanaya.business.service;

import cn.lanaya.business.entity.CommoditySpec;
import cn.lanaya.business.vo.CommoditySpecVO;
import cn.lanaya.common.bean.PageQO;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface CommoditySpecService {
    int countByClause(CommoditySpecVO record);

    int deleteByClause(CommoditySpecVO record);

    int deleteByPrimaryKey(Long id);

    int insert(CommoditySpec record);

    int insertSelective(CommoditySpec record);

    List<CommoditySpec> selectByClause(CommoditySpec record);

    CommoditySpec selectByPrimaryKey(Long id);

    int updateByClauseSelective(CommoditySpec entity, CommoditySpecVO param);

    int updateByPrimaryKeySelective(CommoditySpec record);

    int updateByPrimaryKey(CommoditySpec record);

    PageInfo<CommoditySpecVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<CommoditySpecVO> list);

    int insertSelectiveBatch(List<CommoditySpec> list);
}