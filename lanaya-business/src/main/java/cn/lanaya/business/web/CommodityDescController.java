package cn.lanaya.business.web;

import cn.lanaya.business.entity.CommodityDesc;
import cn.lanaya.business.service.CommodityDescService;
import cn.lanaya.business.vo.CommodityDescVO;
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
@RequestMapping(value = "commodityDesc")
public class CommodityDescController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);
    @Autowired
    private CommodityDescService commodityDescService;

    @PostMapping(value = "countByClause")
    public R<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        CommodityDescVO record = JSONObject.parseObject(json, CommodityDescVO.class);
        return success(MessageEnum.SUCCESS, commodityDescService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public R<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        CommodityDescVO record = JSONObject.parseObject(json, CommodityDescVO.class);
        return doProcess(() -> {
            return commodityDescService.deleteByClause(record);
        });
    }

    @PostMapping(value = "insert")
    public R<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        CommodityDesc record = JSONObject.parseObject(json, CommodityDesc.class);
        return doProcess(() -> {
            return commodityDescService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public R<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        CommodityDesc record = JSONObject.parseObject(json, CommodityDesc.class);
        return doProcess(() -> {
            return commodityDescService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public R<List<CommodityDesc>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        CommodityDesc record = JSONObject.parseObject(json, CommodityDesc.class);
        return success(MessageEnum.SUCCESS, commodityDescService.selectByClause(record));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public R<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        CommodityDesc record = JSONObject.parseObject(json, CommodityDesc.class);
        return doProcess(() -> {
            return commodityDescService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public R<PageInfo<CommodityDescVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        CommodityDescVO record = JSONObject.parseObject(json, CommodityDescVO.class);
        PageQO page = JSONObject.parseObject(json, PageQO.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, commodityDescService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public R<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<CommodityDescVO> list = JSONObject.parseArray(json, CommodityDescVO.class);
        return doProcess(() -> {
            return commodityDescService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public R<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<CommodityDesc> list = JSONObject.parseArray(json, CommodityDesc.class);
        return doProcess(() -> {
            return commodityDescService.insertSelectiveBatch(list);
        });
    }
}