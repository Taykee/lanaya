package cn.lanaya.business.service.impl;

import cn.lanaya.business.dao.CommodityMapper;
import cn.lanaya.business.datasource.DatasourceName;
import cn.lanaya.business.datasource.DynamicSqlSession;
import cn.lanaya.business.entity.Commodity;
import cn.lanaya.business.service.CommodityService;
import cn.lanaya.business.vo.CommodityVO;
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
public class CommodityServiceImpl extends AbstractService implements CommodityService {
    @Autowired
    protected DynamicSqlSession dynamicSqlSession;

    private static Logger log = LoggerFactory.getLogger(CommodityServiceImpl.class);;

    @Override
    public int countByClause(CommodityVO record) {
        log.info("countByClause - {}", record);
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);
        return mapper.countByClause(record);
    }

    @Override
    @Transactional
    public int deleteByClause(CommodityVO record) {
        log.info("deleteByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        return mapper.deleteByClause(record);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(Long id) {
        log.info("deleteByPrimaryKey - {}", id);
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        Commodity saved = mapper.selectByPrimaryKey(id);
        ValidatorUtils.notNull(saved,"未找到记录，不能执行删除");
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(Commodity record) {
        log.info("insert - {}", record);
        validate(record);
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        return mapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Commodity record) {
        log.info("insertSelective - {}", record);
        validate(record);
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        return mapper.insertSelective(record);
    }

    @Override
    public List<Commodity> selectByClause(Commodity record) {
        log.info("selectByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        return mapper.selectByClause(record);
    }

    @Override
    public Commodity selectByPrimaryKey(Long id) {
        log.info("selectByPrimaryKey - {}", id);
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateByClauseSelective(Commodity entity, CommodityVO param) {
        log.info("updateByClauseSelective - {}, {}", entity, param);
        validate(entity);
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        return mapper.updateByClauseSelective(entity, param);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Commodity record) {
        log.info("updateByPrimaryKeySelective - {}", record);
        validate(record);
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(Commodity record) {
        log.info("updateByPrimaryKey - {}", record);
        validate(record);
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        return mapper.updateByPrimaryKey(record);
    }

    private void validate(Commodity record) {
        ValidatorUtils.isBlank(record.getTitle(), " 商品标题不能为空");
        ValidatorUtils.notNull(record.getPrice(), " 商品价格，单位为：元不能为空");
        ValidatorUtils.notNull(record.getNum(), " 库存数量不能为空");
        ValidatorUtils.notNull(record.getCid(), " 所属类目，叶子类目不能为空");
        ValidatorUtils.notNull(record.getStatus(), " 商品状态，1-正常，2-下架，-1-删除不能为空");
    }

    @Override
    public PageInfo<CommodityVO> pageByClause(PageQO page) {
        log.info("pageByClause - {}", page);
        PageHelper.startPage(page.getNum(),page.getSize());
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        List<CommodityVO> list = mapper.pageByClause(page);
        return new PageInfo<CommodityVO>(list);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKeyBatch(List<CommodityVO> list) {
        log.info("deleteByPrimaryKeyBatch - {}", list);
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        return mapper.deleteByPrimaryKeyBatch(list);
    }

    @Override
    @Transactional
    public int insertSelectiveBatch(List<Commodity> list) {
        log.info("insertSelectiveBatch - {}", list);
        CommodityMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, CommodityMapper.class);

        return mapper.insertSelectiveBatch(list);
    }
}