package cn.lanaya.business.web;

import cn.lanaya.business.entity.CommodityCat;
import cn.lanaya.business.service.CommodityCatService;
import cn.lanaya.business.vo.CommodityCatVO;
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
@RequestMapping(value = "commodityCat")
@Slf4j
public class CommodityCatController extends AbstractController {
    @Autowired
    private CommodityCatService commodityCatService;

    @PostMapping(value = "countByClause")
    public Result<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        CommodityCatVO record = JSONObject.parseObject(json, CommodityCatVO.class);
        return success(MessageEnum.SUCCESS, commodityCatService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public Result<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        CommodityCatVO record = JSONObject.parseObject(json, CommodityCatVO.class);
        return doProcess(json, () -> {
            return commodityCatService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public Result<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommodityCatVO record = JSONObject.parseObject(json, CommodityCatVO.class);
        return doProcess(json, () -> {
            return commodityCatService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public Result<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        CommodityCat record = JSONObject.parseObject(json, CommodityCat.class);
        return doProcess(json, () -> {
            return commodityCatService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public Result<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        CommodityCat record = JSONObject.parseObject(json, CommodityCat.class);
        return doProcess(json, () -> {
            return commodityCatService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public Result<List<CommodityCat>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        CommodityCat record = JSONObject.parseObject(json, CommodityCat.class);
        return success(MessageEnum.SUCCESS, commodityCatService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public Result<CommodityCat> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommodityCatVO record = JSONObject.parseObject(json, CommodityCatVO.class);
        return success(MessageEnum.SUCCESS, commodityCatService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public Result<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        CommodityCat record = JSONObject.parseObject(json, CommodityCat.class);
        return doProcess(json, () -> {
            return commodityCatService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public Result<PageInfo<CommodityCatVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        CommodityCatVO record = JSONObject.parseObject(json, CommodityCatVO.class);
        PageQo page = JSONObject.parseObject(json, PageQo.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, commodityCatService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public Result<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<CommodityCatVO> list = JSONObject.parseArray(json, CommodityCatVO.class);
        return doProcess(json, () -> {
            return commodityCatService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public Result<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<CommodityCat> list = JSONObject.parseArray(json, CommodityCat.class);
        return doProcess(json, () -> {
            return commodityCatService.insertSelectiveBatch(list);
        });
    }
}