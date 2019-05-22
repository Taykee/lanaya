package cn.lanaya.business.service.impl;

import cn.lanaya.business.dao.CommodityCatMapper;
import cn.lanaya.business.datasource.DatasourceName;
import cn.lanaya.business.datasource.DynamicSqlSession;
import cn.lanaya.business.entity.CommodityCat;
import cn.lanaya.business.service.CommodityCatService;
import cn.lanaya.business.vo.CommodityCatVO;
import cn.lanaya.common.bean.AbstractService;
import cn.lanaya.common.bean.PageQO;
import cn.lanaya.common.validate.ValidatorUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommodityCatServiceImpl extends AbstractService implements CommodityCatService {
    
    @Autowired
    protected DynamicSqlSession dynamicSqlSession;

    private static Logger log = LoggerFactory.getLogger(CommodityCatServiceImpl.class);;

    @Override
    public int countByClause(CommodityCatVO record) {
        log.info("countByClause - {}", record);
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);
        return mapper.countByClause(record);
    }

    @Override
    @Transactional
    public int deleteByClause(CommodityCatVO record) {
        log.info("deleteByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        return mapper.deleteByClause(record);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        log.info("deleteByPrimaryKey - {}", id);
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        CommodityCat saved = mapper.selectByPrimaryKey(id);
        ValidatorUtils.notNull(saved,"未找到记录，不能执行删除");
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(CommodityCat record) {
        log.info("insert - {}", record);
        validate(record);
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        return mapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(CommodityCat record) {
        log.info("insertSelective - {}", record);
        validate(record);
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        return mapper.insertSelective(record);
    }

    @Override
    public List<CommodityCat> selectByClause(CommodityCat record) {
        log.info("selectByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        return mapper.selectByClause(record);
    }

    @Override
    public CommodityCat selectByPrimaryKey(Long id) {
        log.info("selectByPrimaryKey - {}", id);
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateByClauseSelective(CommodityCat entity, CommodityCatVO param) {
        log.info("updateByClauseSelective - {}, {}", entity, param);
        validate(entity);
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        return mapper.updateByClauseSelective(entity, param);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(CommodityCat record) {
        log.info("updateByPrimaryKeySelective - {}", record);
        validate(record);
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(CommodityCat record) {
        log.info("updateByPrimaryKey - {}", record);
        validate(record);
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        return mapper.updateByPrimaryKey(record);
    }

    private void validate(CommodityCat record){

    }

    @Override
    public PageInfo<CommodityCatVO> pageByClause(PageQO page) {
        log.info("pageByClause - {}", page);
        PageHelper.startPage(page.getNum(),page.getSize());
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        List<CommodityCatVO> list = mapper.pageByClause(page);
        return new PageInfo<CommodityCatVO>(list);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKeyBatch(List<CommodityCatVO> list) {
        log.info("deleteByPrimaryKeyBatch - {}", list);
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        return mapper.deleteByPrimaryKeyBatch(list);
    }

    @Override
    @Transactional
    public int insertSelectiveBatch(List<CommodityCat> list) {
        log.info("insertSelectiveBatch - {}", list);
        CommodityCatMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityCatMapper.class);

        return mapper.insertSelectiveBatch(list);
    }
}