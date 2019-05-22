package cn.lanaya.business.web;

import cn.lanaya.business.entity.CommoditySpec;
import cn.lanaya.business.service.CommoditySpecService;
import cn.lanaya.business.vo.CommoditySpecVO;
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
@RequestMapping(value = "commoditySpec")
@Slf4j
public class CommoditySpecController extends AbstractController {
    @Autowired
    private CommoditySpecService commoditySpecService;

    @PostMapping(value = "countByClause")
    public Result<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        CommoditySpecVO record = JSONObject.parseObject(json, CommoditySpecVO.class);
        return success(MessageEnum.SUCCESS, commoditySpecService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public Result<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        CommoditySpecVO record = JSONObject.parseObject(json, CommoditySpecVO.class);
        return doProcess(json, () -> {
            return commoditySpecService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public Result<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommoditySpecVO record = JSONObject.parseObject(json, CommoditySpecVO.class);
        return doProcess(json, () -> {
            return commoditySpecService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public Result<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        CommoditySpec record = JSONObject.parseObject(json, CommoditySpec.class);
        return doProcess(json, () -> {
            return commoditySpecService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public Result<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        CommoditySpec record = JSONObject.parseObject(json, CommoditySpec.class);
        return doProcess(json, () -> {
            return commoditySpecService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public Result<List<CommoditySpec>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        CommoditySpec record = JSONObject.parseObject(json, CommoditySpec.class);
        return success(MessageEnum.SUCCESS, commoditySpecService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public Result<CommoditySpec> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommoditySpecVO record = JSONObject.parseObject(json, CommoditySpecVO.class);
        return success(MessageEnum.SUCCESS, commoditySpecService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public Result<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        CommoditySpec record = JSONObject.parseObject(json, CommoditySpec.class);
        return doProcess(json, () -> {
            return commoditySpecService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public Result<PageInfo<CommoditySpecVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        CommoditySpecVO record = JSONObject.parseObject(json, CommoditySpecVO.class);
        PageQo page = JSONObject.parseObject(json, PageQo.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, commoditySpecService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public Result<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<CommoditySpecVO> list = JSONObject.parseArray(json, CommoditySpecVO.class);
        return doProcess(json, () -> {
            return commoditySpecService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public Result<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<CommoditySpec> list = JSONObject.parseArray(json, CommoditySpec.class);
        return doProcess(json, () -> {
            return commoditySpecService.insertSelectiveBatch(list);
        });
    }
}