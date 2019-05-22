package cn.lanaya.business.service.impl;

import cn.lanaya.business.dao.CommodityDescMapper;
import cn.lanaya.business.datasource.DatasourceName;
import cn.lanaya.business.datasource.DynamicSqlSession;
import cn.lanaya.business.entity.CommodityDesc;
import cn.lanaya.business.service.CommodityDescService;
import cn.lanaya.business.vo.CommodityDescVO;
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
public class CommodityDescServiceImpl extends AbstractService implements CommodityDescService {
    @Autowired
    protected DynamicSqlSession dynamicSqlSession;

    private static Logger log = LoggerFactory.getLogger(CommodityDescServiceImpl.class);;

    @Override
    public int countByClause(CommodityDescVO record) {
        log.info("countByClause - {}", record);
        CommodityDescMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityDescMapper.class);
        return mapper.countByClause(record);
    }

    @Override
    @Transactional
    public int deleteByClause(CommodityDescVO record) {
        log.info("deleteByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        CommodityDescMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityDescMapper.class);

        return mapper.deleteByClause(record);
    }

    @Override
    @Transactional
    public int insert(CommodityDesc record) {
        log.info("insert - {}", record);
        validate(record);
        CommodityDescMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityDescMapper.class);

        return mapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(CommodityDesc record) {
        log.info("insertSelective - {}", record);
        validate(record);
        CommodityDescMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityDescMapper.class);

        return mapper.insertSelective(record);
    }

    @Override
    public List<CommodityDesc> selectByClause(CommodityDesc record) {
        log.info("selectByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        CommodityDescMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityDescMapper.class);

        return mapper.selectByClause(record);
    }

    @Override
    @Transactional
    public int updateByClauseSelective(CommodityDesc entity, CommodityDescVO param) {
        log.info("updateByClauseSelective - {}, {}", entity, param);
        validate(entity);
        CommodityDescMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityDescMapper.class);

        return mapper.updateByClauseSelective(entity, param);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(CommodityDesc record) {
        log.info("updateByPrimaryKeySelective - {}", record);
        validate(record);
        CommodityDescMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityDescMapper.class);

        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(CommodityDesc record) {
        log.info("updateByPrimaryKey - {}", record);
        validate(record);
        CommodityDescMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityDescMapper.class);

        return mapper.updateByPrimaryKey(record);
    }

    private void validate(CommodityDesc record) {
        ValidatorUtils.notNull(record.getRowstate(), " 数据状态，1-正常，0-无效，-1-禁用不能为空");
        ValidatorUtils.notNull(record.getVersion(), " 版本号不能为空");
    }

    @Override
    public PageInfo<CommodityDescVO> pageByClause(PageQO page) {
        log.info("pageByClause - {}", page);
        PageHelper.startPage(page.getNum(),page.getSize());
        CommodityDescMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityDescMapper.class);

        List<CommodityDescVO> list = mapper.pageByClause(page);
        return new PageInfo<CommodityDescVO>(list);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKeyBatch(List<CommodityDescVO> list) {
        log.info("deleteByPrimaryKeyBatch - {}", list);
        CommodityDescMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityDescMapper.class);

        return mapper.deleteByPrimaryKeyBatch(list);
    }

    @Override
    @Transactional
    public int insertSelectiveBatch(List<CommodityDesc> list) {
        log.info("insertSelectiveBatch - {}", list);
        CommodityDescMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityDescMapper.class);

        return mapper.insertSelectiveBatch(list);
    }
}