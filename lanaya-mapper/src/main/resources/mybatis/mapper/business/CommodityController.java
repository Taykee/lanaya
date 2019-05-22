package cn.lanaya.business.web;

import cn.lanaya.business.entity.Commodity;
import cn.lanaya.business.service.CommodityService;
import cn.lanaya.business.vo.CommodityVO;
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
@RequestMapping(value = "commodity")
@Slf4j
public class CommodityController extends AbstractController {
    @Autowired
    private CommodityService commodityService;

    @PostMapping(value = "countByClause")
    public Result<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        CommodityVO record = JSONObject.parseObject(json, CommodityVO.class);
        return success(MessageEnum.SUCCESS, commodityService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public Result<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        CommodityVO record = JSONObject.parseObject(json, CommodityVO.class);
        return doProcess(json, () -> {
            return commodityService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public Result<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommodityVO record = JSONObject.parseObject(json, CommodityVO.class);
        return doProcess(json, () -> {
            return commodityService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public Result<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        Commodity record = JSONObject.parseObject(json, Commodity.class);
        return doProcess(json, () -> {
            return commodityService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public Result<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        Commodity record = JSONObject.parseObject(json, Commodity.class);
        return doProcess(json, () -> {
            return commodityService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public Result<List<Commodity>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        Commodity record = JSONObject.parseObject(json, Commodity.class);
        return success(MessageEnum.SUCCESS, commodityService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public Result<Commodity> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommodityVO record = JSONObject.parseObject(json, CommodityVO.class);
        return success(MessageEnum.SUCCESS, commodityService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public Result<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        Commodity record = JSONObject.parseObject(json, Commodity.class);
        return doProcess(json, () -> {
            return commodityService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public Result<PageInfo<CommodityVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        CommodityVO record = JSONObject.parseObject(json, CommodityVO.class);
        PageQo page = JSONObject.parseObject(json, PageQo.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, commodityService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public Result<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<CommodityVO> list = JSONObject.parseArray(json, CommodityVO.class);
        return doProcess(json, () -> {
            return commodityService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public Result<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<Commodity> list = JSONObject.parseArray(json, Commodity.class);
        return doProcess(json, () -> {
            return commodityService.insertSelectiveBatch(list);
        });
    }
}