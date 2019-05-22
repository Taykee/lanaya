package cn.lanaya.business.service.impl;

import cn.lanaya.business.dao.MerchantMapper;
import cn.lanaya.business.datasource.DatasourceName;
import cn.lanaya.business.datasource.DynamicSqlSession;
import cn.lanaya.business.entity.Merchant;
import cn.lanaya.business.service.MerchantService;
import cn.lanaya.business.vo.MerchantVO;
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
public class MerchantServiceImpl extends AbstractService implements MerchantService {
    @Autowired
    protected DynamicSqlSession dynamicSqlSession;

    private static Logger log = LoggerFactory.getLogger(MerchantServiceImpl.class);;

    @Override
    public int countByClause(MerchantVO record) {
        log.info("countByClause - {}", record);
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);
        return mapper.countByClause(record);
    }

    @Override
    @Transactional
    public int deleteByClause(MerchantVO record) {
        log.info("deleteByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        return mapper.deleteByClause(record);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKey(String id) {
        log.info("deleteByPrimaryKey - {}", id);
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        Merchant saved = mapper.selectByPrimaryKey(id);
        ValidatorUtils.notNull(saved,"未找到记录，不能执行删除");
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int insert(Merchant record) {
        log.info("insert - {}", record);
        validate(record);
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        return mapper.insert(record);
    }

    @Override
    @Transactional
    public int insertSelective(Merchant record) {
        log.info("insertSelective - {}", record);
        validate(record);
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        return mapper.insertSelective(record);
    }

    @Override
    public List<Merchant> selectByClause(Merchant record) {
        log.info("selectByClause - {}", record);
        ValidatorUtils.notNull(record,"参数不能为空");
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        return mapper.selectByClause(record);
    }

    @Override
    public Merchant selectByPrimaryKey(String id) {
        log.info("selectByPrimaryKey - {}", id);
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        return mapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public int updateByClauseSelective(Merchant entity, MerchantVO param) {
        log.info("updateByClauseSelective - {}, {}", entity, param);
        validate(entity);
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        return mapper.updateByClauseSelective(entity, param);
    }

    @Override
    @Transactional
    public int updateByPrimaryKeySelective(Merchant record) {
        log.info("updateByPrimaryKeySelective - {}", record);
        validate(record);
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateByPrimaryKey(Merchant record) {
        log.info("updateByPrimaryKey - {}", record);
        validate(record);
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        return mapper.updateByPrimaryKey(record);
    }

    private void validate(Merchant record) {
        ValidatorUtils.isBlank(record.getUsername(), " 用户名不能为空");
        ValidatorUtils.isBlank(record.getPassword(), " 密码，加密存储不能为空");
        ValidatorUtils.notNull(record.getStatus(), " 商户状态，0-初始化，1-审核通过，2-审核中，3-审核拒绝，-1-注销，-2-冻结不能为空");
        ValidatorUtils.notNull(record.getRowstate(), " 数据状态，1-正常，0-无效，-1-禁用不能为空");
        ValidatorUtils.notNull(record.getVersion(), " 版本号不能为空");
    }

    @Override
    public PageInfo<MerchantVO> pageByClause(PageQO page) {
        log.info("pageByClause - {}", page);
        PageHelper.startPage(page.getNum(),page.getSize());
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        List<MerchantVO> list = mapper.pageByClause(page);
        return new PageInfo<MerchantVO>(list);
    }

    @Override
    @Transactional
    public int deleteByPrimaryKeyBatch(List<MerchantVO> list) {
        log.info("deleteByPrimaryKeyBatch - {}", list);
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        return mapper.deleteByPrimaryKeyBatch(list);
    }

    @Override
    @Transactional
    public int insertSelectiveBatch(List<Merchant> list) {
        log.info("insertSelectiveBatch - {}", list);
        MerchantMapper mapper = dynamicSqlSession.getMapper(DatasourceName.primary, MerchantMapper.class);

        return mapper.insertSelectiveBatch(list);
    }
}