package cn.lanaya.business.web;

import cn.lanaya.business.entity.CommoditySpec;
import cn.lanaya.business.service.CommoditySpecService;
import cn.lanaya.business.vo.CommoditySpecVO;
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
@RequestMapping(value = "commoditySpec")
public class CommoditySpecController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);
    @Autowired
    private CommoditySpecService commoditySpecService;

    @PostMapping(value = "countByClause")
    public R<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        CommoditySpecVO record = JSONObject.parseObject(json, CommoditySpecVO.class);
        return success(MessageEnum.SUCCESS, commoditySpecService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public R<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        CommoditySpecVO record = JSONObject.parseObject(json, CommoditySpecVO.class);
        return doProcess(() -> {
            return commoditySpecService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public R<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommoditySpecVO record = JSONObject.parseObject(json, CommoditySpecVO.class);
        return doProcess(() -> {
            return commoditySpecService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public R<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        CommoditySpec record = JSONObject.parseObject(json, CommoditySpec.class);
        return doProcess(() -> {
            return commoditySpecService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public R<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        CommoditySpec record = JSONObject.parseObject(json, CommoditySpec.class);
        return doProcess(() -> {
            return commoditySpecService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public R<List<CommoditySpec>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        CommoditySpec record = JSONObject.parseObject(json, CommoditySpec.class);
        return success(MessageEnum.SUCCESS, commoditySpecService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public R<CommoditySpec> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommoditySpecVO record = JSONObject.parseObject(json, CommoditySpecVO.class);
        return success(MessageEnum.SUCCESS, commoditySpecService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public R<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        CommoditySpec record = JSONObject.parseObject(json, CommoditySpec.class);
        return doProcess(() -> {
            return commoditySpecService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public R<PageInfo<CommoditySpecVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        CommoditySpecVO record = JSONObject.parseObject(json, CommoditySpecVO.class);
        PageQO page = JSONObject.parseObject(json, PageQO.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, commoditySpecService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public R<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<CommoditySpecVO> list = JSONObject.parseArray(json, CommoditySpecVO.class);
        return doProcess(() -> {
            return commoditySpecService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public R<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<CommoditySpec> list = JSONObject.parseArray(json, CommoditySpec.class);
        return doProcess(() -> {
            return commoditySpecService.insertSelectiveBatch(list);
        });
    }
}