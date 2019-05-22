package cn.lanaya.business.web;

import cn.lanaya.business.entity.Merchant;
import cn.lanaya.business.service.MerchantService;
import cn.lanaya.business.vo.MerchantVO;
import cn.lanaya.common.bean.PageQO;
import cn.lanaya.common.format.MessageEnum;
import cn.lanaya.common.format.R;
import cn.lanaya.common.web.AbstractController;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "merchant")
public class MerchantController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);
    @Autowired
    private MerchantService merchantService;

    @PostMapping(value = "countByClause")
    public R<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        MerchantVO record = JSONObject.parseObject(json, MerchantVO.class);
        return success(MessageEnum.SUCCESS, merchantService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public R<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        MerchantVO record = JSONObject.parseObject(json, MerchantVO.class);
        return doProcess(() -> {
            return merchantService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public R<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        MerchantVO record = JSONObject.parseObject(json, MerchantVO.class);
        return doProcess(() -> {
            return merchantService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public R<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        Merchant record = JSONObject.parseObject(json, Merchant.class);
        return doProcess(() -> {
            return merchantService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public R<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        Merchant record = JSONObject.parseObject(json, Merchant.class);
        return doProcess(() -> {
            return merchantService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public R<List<Merchant>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        Merchant record = JSONObject.parseObject(json, Merchant.class);
        return success(MessageEnum.SUCCESS, merchantService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public R<Merchant> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        MerchantVO record = JSONObject.parseObject(json, MerchantVO.class);
        return success(MessageEnum.SUCCESS, merchantService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public R<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        Merchant record = JSONObject.parseObject(json, Merchant.class);
        return doProcess(() -> {
            return merchantService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public R<PageInfo<MerchantVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        MerchantVO record = JSONObject.parseObject(json, MerchantVO.class);
        PageQO page = JSONObject.parseObject(json, PageQO.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, merchantService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public R<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<MerchantVO> list = JSONObject.parseArray(json, MerchantVO.class);
        return doProcess(() -> {
            return merchantService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public R<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<Merchant> list = JSONObject.parseArray(json, Merchant.class);
        return doProcess(() -> {
            return merchantService.insertSelectiveBatch(list);
        });
    }
}