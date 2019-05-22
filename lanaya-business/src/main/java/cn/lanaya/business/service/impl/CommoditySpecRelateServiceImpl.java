package cn.lanaya.business.service.impl;

import cn.lanaya.business.dao.CommoditySpecRelateMapper;
import cn.lanaya.business.datasource.DatasourceName;
import cn.lanaya.business.datasource.DynamicSqlSession;
import cn.lanaya.business.entity.CommoditySpecRelate;
import cn.lanaya.business.service.CommoditySpecRelateService;
import cn.lanaya.business.vo.CommoditySpecRelateVO;
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
public class CommoditySpecRelateServiceImpl extends AbstractService implements CommoditySpecRelateService {
    @Autowired
    protected DynamicSqlSession dynamicSqlSession;

    private static Logger log = LoggerFactory.getLogger(CommoditySpecRelateServiceImpl.class);;

    @Override
    public int countByClause(CommoditySpecRelateVO record) {
        log.info("countByClause - {}", record);
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);
        return mapper.countByClause(record);
    }

    @Override
    @Transactional
    public int deleteByClause(CommoditySpecRelateVO record) {
        log.info("deleteByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        return mapper.deleteByClause(record);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        log.info("deleteByPrimaryKey - {}", id);
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        CommoditySpecRelate saved = mapper.selectByPrimaryKey(id);
        ValidatorUtils.notNull(saved,"未找到记录，不能执行删除");
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(CommoditySpecRelate record) {
        log.info("insert - {}", record);
        validate(record);
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        return mapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(CommoditySpecRelate record) {
        log.info("insertSelective - {}", record);
        validate(record);
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        return mapper.insertSelective(record);
    }

    @Override
    public List<CommoditySpecRelate> selectByClause(CommoditySpecRelate record) {
        log.info("selectByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        return mapper.selectByClause(record);
    }

    @Override
    public CommoditySpecRelate selectByPrimaryKey(Long id) {
        log.info("selectByPrimaryKey - {}", id);
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateByClauseSelective(CommoditySpecRelate entity, CommoditySpecRelateVO param) {
        log.info("updateByClauseSelective - {}, {}", entity, param);
        validate(entity);
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        return mapper.updateByClauseSelective(entity, param);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(CommoditySpecRelate record) {
        log.info("updateByPrimaryKeySelective - {}", record);
        validate(record);
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(CommoditySpecRelate record) {
        log.info("updateByPrimaryKey - {}", record);
        validate(record);
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        return mapper.updateByPrimaryKey(record);
    }

    private void validate(CommoditySpecRelate record) {
        ValidatorUtils.notNull(record.getRowstate(), " 数据状态，1-正常，0-无效，-1-禁用不能为空");
        ValidatorUtils.notNull(record.getVersion(), " 版本号不能为空");
    }

    @Override
    public PageInfo<CommoditySpecRelateVO> pageByClause(PageQO page) {
        log.info("pageByClause - {}", page);
        PageHelper.startPage(page.getNum(),page.getSize());
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        List<CommoditySpecRelateVO> list = mapper.pageByClause(page);
        return new PageInfo<CommoditySpecRelateVO>(list);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKeyBatch(List<CommoditySpecRelateVO> list) {
        log.info("deleteByPrimaryKeyBatch - {}", list);
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        return mapper.deleteByPrimaryKeyBatch(list);
    }

    @Override
    @Transactional
    public int insertSelectiveBatch(List<CommoditySpecRelate> list) {
        log.info("insertSelectiveBatch - {}", list);
        CommoditySpecRelateMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommoditySpecRelateMapper.class);

        return mapper.insertSelectiveBatch(list);
    }
}