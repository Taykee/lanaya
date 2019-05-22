package cn.lanaya.business.web;

import cn.lanaya.business.entity.Merchant;
import cn.lanaya.business.service.MerchantService;
import cn.lanaya.business.vo.MerchantVO;
import cn.lanaya.common.bean.PageQO;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xhvms.common.format.MessageEnum;
import com.xhvms.common.format.Result;
import com.xhvms.common.web.AbstractController;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "merchant")
@Slf4j
public class MerchantController extends AbstractController {
    @Autowired
    private MerchantService merchantService;

    @PostMapping(value = "countByClause")
    public Result<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        MerchantVO record = JSONObject.parseObject(json, MerchantVO.class);
        return success(MessageEnum.SUCCESS, merchantService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public Result<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        MerchantVO record = JSONObject.parseObject(json, MerchantVO.class);
        return doProcess(json, () -> {
            return merchantService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public Result<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        MerchantVO record = JSONObject.parseObject(json, MerchantVO.class);
        return doProcess(json, () -> {
            return merchantService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public Result<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        Merchant record = JSONObject.parseObject(json, Merchant.class);
        return doProcess(json, () -> {
            return merchantService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public Result<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        Merchant record = JSONObject.parseObject(json, Merchant.class);
        return doProcess(json, () -> {
            return merchantService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public Result<List<Merchant>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        Merchant record = JSONObject.parseObject(json, Merchant.class);
        return success(MessageEnum.SUCCESS, merchantService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public Result<Merchant> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        MerchantVO record = JSONObject.parseObject(json, MerchantVO.class);
        return success(MessageEnum.SUCCESS, merchantService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public Result<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        Merchant record = JSONObject.parseObject(json, Merchant.class);
        return doProcess(json, () -> {
            return merchantService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public Result<PageInfo<MerchantVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        MerchantVO record = JSONObject.parseObject(json, MerchantVO.class);
        PageQo page = JSONObject.parseObject(json, PageQo.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, merchantService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public Result<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<MerchantVO> list = JSONObject.parseArray(json, MerchantVO.class);
        return doProcess(json, () -> {
            return merchantService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public Result<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<Merchant> list = JSONObject.parseArray(json, Merchant.class);
        return doProcess(json, () -> {
            return merchantService.insertSelectiveBatch(list);
        });
    }
}