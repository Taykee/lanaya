package cn.lanaya.business.service;

import cn.lanaya.business.entity.BusinessBrand;
import cn.lanaya.business.vo.BusinessBrandVO;
import cn.lanaya.common.bean.PageQO;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface BusinessBrandService {
    int countByClause(BusinessBrandVO record);

    int deleteByClause(BusinessBrandVO record);

    int deleteByPrimaryKey(Long id);

    int insert(BusinessBrand record);

    int insertSelective(BusinessBrand record);

    List<BusinessBrand> selectByClause(BusinessBrand record);

    BusinessBrand selectByPrimaryKey(Long id);

    int updateByClauseSelective(BusinessBrand entity, BusinessBrandVO param);

    int updateByPrimaryKeySelective(BusinessBrand record);

    int updateByPrimaryKey(BusinessBrand record);

    PageInfo<BusinessBrandVO> pageByClause(PageQO page);

    int deleteByPrimaryKeyBatch(List<BusinessBrandVO> list);

    int insertSelectiveBatch(List<BusinessBrand> list);
}