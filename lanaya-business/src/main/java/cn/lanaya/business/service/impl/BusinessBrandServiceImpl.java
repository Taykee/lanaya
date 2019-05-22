package cn.lanaya.business.service.impl;

import cn.lanaya.business.dao.BusinessBrandMapper;
import cn.lanaya.business.entity.BusinessBrand;
import cn.lanaya.business.service.BusinessBrandService;
import cn.lanaya.business.datasource.DatasourceName;
import cn.lanaya.business.vo.BusinessBrandVO;
import cn.lanaya.common.bean.AbstractService;
import cn.lanaya.business.datasource.DynamicSqlSession;
import cn.lanaya.common.bean.PageQO;
import cn.lanaya.common.validate.ValidatorUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BusinessBrandServiceImpl extends AbstractService implements BusinessBrandService {

    @Autowired
    protected DynamicSqlSession dynamicSqlSession;

    private static Logger log = LoggerFactory.getLogger(BusinessBrandServiceImpl.class);;

    @Override
    public int countByClause(BusinessBrandVO record) {
        log.info("countByClause - {}", record);
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        
        return mapper.countByClause(record);
    }

    @Override
    @Transactional
    public int deleteByClause(BusinessBrandVO record) {
        log.info("deleteByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        return mapper.deleteByClause(record);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        log.info("deleteByPrimaryKey - {}", id);
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        BusinessBrand saved = mapper.selectByPrimaryKey(id);
        ValidatorUtils.notNull(saved,"未找到记录，不能执行删除");
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(BusinessBrand record) {
        log.info("insert - {}", record);
        validate(record);
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        return mapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(BusinessBrand record) {
        log.info("insertSelective - {}", record);
        validate(record);
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        record.setCreatetime(new Date());
        return mapper.insertSelective(record);
    }

    @Override
    public List<BusinessBrand> selectByClause(BusinessBrand record) {
        log.info("selectByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        return mapper.selectByClause(record);
    }

    @Override
    public BusinessBrand selectByPrimaryKey(Long id) {
        log.info("selectByPrimaryKey - {}", id);
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateByClauseSelective(BusinessBrand entity, BusinessBrandVO param) {
        log.info("updateByClauseSelective - {}, {}", entity, param);
        validate(entity);
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        return mapper.updateByClauseSelective(entity, param);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(BusinessBrand record) {
        log.info("updateByPrimaryKeySelective - {}", record);
        validate(record);
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(BusinessBrand record) {
        log.info("updateByPrimaryKey - {}", record);
        validate(record);
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        return mapper.updateByPrimaryKey(record);
    }

    private void validate(BusinessBrand record) {
        ValidatorUtils.notBlank(record.getName(), " 品牌名称不能为空");
        ValidatorUtils.notBlank(record.getFirstChar(), " 名称首字母不能为空");

    }

    @Override
    public PageInfo<BusinessBrandVO> pageByClause(PageQO page) {
        log.info("pageByClause - {}", page);
        PageHelper.startPage(page.getNum(),page.getSize());
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        List<BusinessBrandVO> list = mapper.pageByClause(page);
        return new PageInfo<BusinessBrandVO>(list);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKeyBatch(List<BusinessBrandVO> list) {
        log.info("deleteByPrimaryKeyBatch - {}", list);
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        return mapper.deleteByPrimaryKeyBatch(list);
    }

    @Override
    @Transactional
    public int insertSelectiveBatch(List<BusinessBrand> list) {
        log.info("insertSelectiveBatch - {}", list);
        BusinessBrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BusinessBrandMapper.class);
        return mapper.insertSelectiveBatch(list);
    }
}