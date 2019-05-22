package cn.lanaya.business.web;

import cn.lanaya.business.entity.Commodity;
import cn.lanaya.business.service.CommodityService;
import cn.lanaya.business.vo.CommodityVO;
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
@RequestMapping(value = "commodity")
public class CommodityController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);
    @Autowired
    private CommodityService commodityService;

    @PostMapping(value = "countByClause")
    public R<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        CommodityVO record = JSONObject.parseObject(json, CommodityVO.class);
        return success(MessageEnum.SUCCESS, commodityService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public R<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        CommodityVO record = JSONObject.parseObject(json, CommodityVO.class);
        return doProcess(() -> {
            return commodityService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public R<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommodityVO record = JSONObject.parseObject(json, CommodityVO.class);
        return doProcess(() -> {
            return commodityService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public R<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        Commodity record = JSONObject.parseObject(json, Commodity.class);
        return doProcess(() -> {
            return commodityService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public R<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        Commodity record = JSONObject.parseObject(json, Commodity.class);
        return doProcess(() -> {
            return commodityService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public R<List<Commodity>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        Commodity record = JSONObject.parseObject(json, Commodity.class);
        return success(MessageEnum.SUCCESS, commodityService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public R<Commodity> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommodityVO record = JSONObject.parseObject(json, CommodityVO.class);
        return success(MessageEnum.SUCCESS, commodityService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public R<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        Commodity record = JSONObject.parseObject(json, Commodity.class);
        return doProcess(() -> {
            return commodityService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public R<PageInfo<CommodityVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        CommodityVO record = JSONObject.parseObject(json, CommodityVO.class);
        PageQO page = JSONObject.parseObject(json, PageQO.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, commodityService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public R<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<CommodityVO> list = JSONObject.parseArray(json, CommodityVO.class);
        return doProcess(() -> {
            return commodityService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public R<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<Commodity> list = JSONObject.parseArray(json, Commodity.class);
        return doProcess(() -> {
            return commodityService.insertSelectiveBatch(list);
        });
    }
}