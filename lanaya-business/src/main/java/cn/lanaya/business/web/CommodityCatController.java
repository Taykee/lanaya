package cn.lanaya.business.web;

import cn.lanaya.business.entity.CommodityCat;
import cn.lanaya.business.service.CommodityCatService;
import cn.lanaya.business.vo.CommodityCatVO;
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
@RequestMapping(value = "commodityCat")
public class CommodityCatController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);
    @Autowired
    private CommodityCatService commodityCatService;

    @PostMapping(value = "countByClause")
    public R<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        CommodityCatVO record = JSONObject.parseObject(json, CommodityCatVO.class);
        return success(MessageEnum.SUCCESS, commodityCatService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public R<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        CommodityCatVO record = JSONObject.parseObject(json, CommodityCatVO.class);
        return doProcess(() -> {
            return commodityCatService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public R<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommodityCatVO record = JSONObject.parseObject(json, CommodityCatVO.class);
        return doProcess(() -> {
            return commodityCatService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public R<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        CommodityCat record = JSONObject.parseObject(json, CommodityCat.class);
        return doProcess(() -> {
            return commodityCatService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public R<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        CommodityCat record = JSONObject.parseObject(json, CommodityCat.class);
        return doProcess(() -> {
            return commodityCatService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public R<List<CommodityCat>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        CommodityCat record = JSONObject.parseObject(json, CommodityCat.class);
        return success(MessageEnum.SUCCESS, commodityCatService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public R<CommodityCat> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommodityCatVO record = JSONObject.parseObject(json, CommodityCatVO.class);
        return success(MessageEnum.SUCCESS, commodityCatService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public R<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        CommodityCat record = JSONObject.parseObject(json, CommodityCat.class);
        return doProcess(() -> {
            return commodityCatService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public R<PageInfo<CommodityCatVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        CommodityCatVO record = JSONObject.parseObject(json, CommodityCatVO.class);
        PageQO page = JSONObject.parseObject(json, PageQO.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, commodityCatService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public R<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<CommodityCatVO> list = JSONObject.parseArray(json, CommodityCatVO.class);
        return doProcess(() -> {
            return commodityCatService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public R<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<CommodityCat> list = JSONObject.parseArray(json, CommodityCat.class);
        return doProcess(() -> {
            return commodityCatService.insertSelectiveBatch(list);
        });
    }
}