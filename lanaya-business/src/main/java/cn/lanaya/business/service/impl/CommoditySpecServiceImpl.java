package cn.lanaya.business.service.impl;

import cn.lanaya.business.dao.CommoditySpecMapper;
import cn.lanaya.business.datasource.DatasourceName;
import cn.lanaya.business.datasource.DynamicSqlSession;
import cn.lanaya.business.entity.CommoditySpec;
import cn.lanaya.business.service.CommoditySpecService;
import cn.lanaya.business.vo.CommoditySpecVO;
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
public class CommoditySpecServiceImpl extends AbstractService implements CommoditySpecService {
    @Autowired
    protected DynamicSqlSession dynamicSqlSession;

    private static Logger log = LoggerFactory.getLogger(CommoditySpecServiceImpl.class);;

    @Override
    public int countByClause(CommoditySpecVO record) {
        log.info("countByClause - {}", record);
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);
        return mapper.countByClause(record);
    }

    @Override
    @Transactional
    public int deleteByClause(CommoditySpecVO record) {
        log.info("deleteByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        return mapper.deleteByClause(record);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        log.info("deleteByPrimaryKey - {}", id);
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        CommoditySpec saved = mapper.selectByPrimaryKey(id);
        ValidatorUtils.notNull(saved,"未找到记录，不能执行删除");
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(CommoditySpec record) {
        log.info("insert - {}", record);
        validate(record);
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        return mapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(CommoditySpec record) {
        log.info("insertSelective - {}", record);
        validate(record);
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        return mapper.insertSelective(record);
    }

    @Override
    public List<CommoditySpec> selectByClause(CommoditySpec record) {
        log.info("selectByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        return mapper.selectByClause(record);
    }

    @Override
    public CommoditySpec selectByPrimaryKey(Long id) {
        log.info("selectByPrimaryKey - {}", id);
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateByClauseSelective(CommoditySpec entity, CommoditySpecVO param) {
        log.info("updateByClauseSelective - {}, {}", entity, param);
        validate(entity);
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        return mapper.updateByClauseSelective(entity, param);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(CommoditySpec record) {
        log.info("updateByPrimaryKeySelective - {}", record);
        validate(record);
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(CommoditySpec record) {
        log.info("updateByPrimaryKey - {}", record);
        validate(record);
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        return mapper.updateByPrimaryKey(record);
    }

    private void validate(CommoditySpec record) {
        ValidatorUtils.notNull(record.getRowstate(), " 数据状态，1-正常，0-无效，-1-禁用不能为空");
        ValidatorUtils.notNull(record.getVersion(), " 版本号不能为空");
    }

    @Override
    public PageInfo<CommoditySpecVO> pageByClause(PageQO page) {
        log.info("pageByClause - {}", page);
        PageHelper.startPage(page.getNum(),page.getSize());
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        List<CommoditySpecVO> list = mapper.pageByClause(page);
        return new PageInfo<CommoditySpecVO>(list);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKeyBatch(List<CommoditySpecVO> list) {
        log.info("deleteByPrimaryKeyBatch - {}", list);
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        return mapper.deleteByPrimaryKeyBatch(list);
    }

    @Override
    @Transactional
    public int insertSelectiveBatch(List<CommoditySpec> list) {
        log.info("insertSelectiveBatch - {}", list);
        CommoditySpecMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecMapper.class);

        return mapper.insertSelectiveBatch(list);
    }
}