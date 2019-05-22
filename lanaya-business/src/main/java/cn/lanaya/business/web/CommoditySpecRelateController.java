package cn.lanaya.business.web;

import cn.lanaya.business.entity.CommoditySpecRelate;
import cn.lanaya.business.service.CommoditySpecRelateService;
import cn.lanaya.business.vo.CommoditySpecRelateVO;
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
@RequestMapping(value = "commoditySpecRelate")
public class CommoditySpecRelateController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);
    @Autowired
    private CommoditySpecRelateService commoditySpecRelateService;

    @PostMapping(value = "countByClause")
    public R<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        CommoditySpecRelateVO record = JSONObject.parseObject(json, CommoditySpecRelateVO.class);
        return success(MessageEnum.SUCCESS, commoditySpecRelateService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public R<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        CommoditySpecRelateVO record = JSONObject.parseObject(json, CommoditySpecRelateVO.class);
        return doProcess(() -> {
            return commoditySpecRelateService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public R<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommoditySpecRelateVO record = JSONObject.parseObject(json, CommoditySpecRelateVO.class);
        return doProcess(() -> {
            return commoditySpecRelateService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public R<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        CommoditySpecRelate record = JSONObject.parseObject(json, CommoditySpecRelate.class);
        return doProcess(() -> {
            return commoditySpecRelateService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public R<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        CommoditySpecRelate record = JSONObject.parseObject(json, CommoditySpecRelate.class);
        return doProcess(() -> {
            return commoditySpecRelateService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public R<List<CommoditySpecRelate>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        CommoditySpecRelate record = JSONObject.parseObject(json, CommoditySpecRelate.class);
        return success(MessageEnum.SUCCESS, commoditySpecRelateService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public R<CommoditySpecRelate> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommoditySpecRelateVO record = JSONObject.parseObject(json, CommoditySpecRelateVO.class);
        return success(MessageEnum.SUCCESS, commoditySpecRelateService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public R<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        CommoditySpecRelate record = JSONObject.parseObject(json, CommoditySpecRelate.class);
        return doProcess(() -> {
            return commoditySpecRelateService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public R<PageInfo<CommoditySpecRelateVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        CommoditySpecRelateVO record = JSONObject.parseObject(json, CommoditySpecRelateVO.class);
        PageQO page = JSONObject.parseObject(json, PageQO.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, commoditySpecRelateService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public R<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<CommoditySpecRelateVO> list = JSONObject.parseArray(json, CommoditySpecRelateVO.class);
        return doProcess(() -> {
            return commoditySpecRelateService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public R<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<CommoditySpecRelate> list = JSONObject.parseArray(json, CommoditySpecRelate.class);
        return doProcess(() -> {
            return commoditySpecRelateService.insertSelectiveBatch(list);
        });
    }
}