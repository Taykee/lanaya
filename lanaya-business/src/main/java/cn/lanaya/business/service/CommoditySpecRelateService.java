package cn.lanaya.business.service;

import cn.lanaya.business.entity.CommoditySpecRelate;
import cn.lanaya.business.vo.CommoditySpecRelateVO;
import cn.lanaya.common.bean.PageQO;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface CommoditySpecRelateService {
    int countByClause(CommoditySpecRelateVO record);

    int deleteByClause(CommoditySpecRelateVO record);

    int deleteByPrimaryKey(Long id);

    int insert(CommoditySpecRelate record);

    int insertSelective(CommoditySpecRelate record);

    List<CommoditySpecRelate> selectByClause(CommoditySpecRelate record);

    CommoditySpecRelate selectByPrimaryKey(Long id);

    int updateByClauseSelective(CommoditySpecRelate entity, CommoditySpecRelateVO param);

    int updateByPrimaryKeySelective(CommoditySpecRelate record);

    int updateByPrimaryKey(CommoditySpecRelate record);

    PageInfo<CommoditySpecRelateVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<CommoditySpecRelateVO> list);

    int insertSelectiveBatch(List<CommoditySpecRelate> list);
}