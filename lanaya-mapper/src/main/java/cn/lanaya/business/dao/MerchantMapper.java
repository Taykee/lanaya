package cn.lanaya.business.dao;

import cn.lanaya.business.entity.Merchant;
import cn.lanaya.business.vo.MerchantVO;
import cn.lanaya.common.bean.PageQO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantMapper {
    int countByClause(MerchantVO record);

    int deleteByClause(@Param("param") MerchantVO record);

    int deleteByPrimaryKey(String id);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    List<Merchant> selectByClause(@Param("param") Merchant record);

    Merchant selectByPrimaryKey(String id);

    int updateByClauseSelective(@Param("entity") Merchant entity, @Param("param") MerchantVO param);

    int updateByPrimaryKeySelective(@Param("entity") Merchant record);

    int updateByPrimaryKey(Merchant record);

    List<MerchantVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<MerchantVO> list);

    int insertSelectiveBatch(List<Merchant> list);
}