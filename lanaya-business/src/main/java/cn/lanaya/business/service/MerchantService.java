package cn.lanaya.business.service;

import cn.lanaya.business.entity.Merchant;
import cn.lanaya.business.vo.MerchantVO;
import cn.lanaya.common.bean.PageQO;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface MerchantService {
    int countByClause(MerchantVO record);

    int deleteByClause(MerchantVO record);

    int deleteByPrimaryKey(String id);

    int insert(Merchant record);

    int insertSelective(Merchant record);

    List<Merchant> selectByClause(Merchant record);

    Merchant selectByPrimaryKey(String id);

    int updateByClauseSelective(Merchant entity, MerchantVO param);

    int updateByPrimaryKeySelective(Merchant record);

    int updateByPrimaryKey(Merchant record);

    PageInfo<MerchantVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<MerchantVO> list);

    int insertSelectiveBatch(List<Merchant> list);
}