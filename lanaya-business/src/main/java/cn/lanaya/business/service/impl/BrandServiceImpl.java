package cn.lanaya.business.service.impl;

import cn.lanaya.business.dao.BrandMapper;
import cn.lanaya.business.entity.Brand;
import cn.lanaya.business.service.BrandService;
import cn.lanaya.business.datasource.DatasourceName;
import cn.lanaya.business.vo.BrandVO;
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
public class BrandServiceImpl extends AbstractService implements BrandService {

    @Autowired
    protected DynamicSqlSession dynamicSqlSession;

    private static Logger log = LoggerFactory.getLogger(BrandServiceImpl.class);;

    @Override
    public int countByClause(BrandVO record) {
        log.info("countByClause - {}", record);
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        
        return mapper.countByClause(record);
    }

    @Override
    @Transactional
    public int deleteByClause(BrandVO record) {
        log.info("deleteByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        return mapper.deleteByClause(record);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        log.info("deleteByPrimaryKey - {}", id);
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        Brand saved = mapper.selectByPrimaryKey(id);
        ValidatorUtils.notNull(saved,"未找到记录，不能执行删除");
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(Brand record) {
        log.info("insert - {}", record);
        validate(record);
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        return mapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Brand record) {
        log.info("insertSelective - {}", record);
        validate(record);
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        record.setCreatetime(new Date());
        return mapper.insertSelective(record);
    }

    @Override
    public List<Brand> selectByClause(Brand record) {
        log.info("selectByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        return mapper.selectByClause(record);
    }

    @Override
    public Brand selectByPrimaryKey(Long id) {
        log.info("selectByPrimaryKey - {}", id);
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateByClauseSelective(Brand entity, BrandVO param) {
        log.info("updateByClauseSelective - {}, {}", entity, param);
        validate(entity);
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        return mapper.updateByClauseSelective(entity, param);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Brand record) {
        log.info("updateByPrimaryKeySelective - {}", record);
        validate(record);
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(Brand record) {
        log.info("updateByPrimaryKey - {}", record);
        validate(record);
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        return mapper.updateByPrimaryKey(record);
    }

    private void validate(Brand record) {
        ValidatorUtils.notBlank(record.getName(), " 品牌名称不能为空");
        ValidatorUtils.notBlank(record.getFirstChar(), " 名称首字母不能为空");

    }

    @Override
    public PageInfo<BrandVO> pageByClause(PageQO page) {
        log.info("pageByClause - {}", page);
        PageHelper.startPage(page.getNum(),page.getSize());
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        List<BrandVO> list = mapper.pageByClause(page);
        return new PageInfo<BrandVO>(list);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKeyBatch(List<BrandVO> list) {
        log.info("deleteByPrimaryKeyBatch - {}", list);
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        return mapper.deleteByPrimaryKeyBatch(list);
    }

    @Override
    @Transactional
    public int insertSelectiveBatch(List<Brand> list) {
        log.info("insertSelectiveBatch - {}", list);
        BrandMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, BrandMapper.class);
        return mapper.insertSelectiveBatch(list);
    }
}