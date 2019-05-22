package cn.lanaya.business.web;

import cn.lanaya.business.entity.CommoditySpecRelate;
import cn.lanaya.business.service.CommoditySpecRelateService;
import cn.lanaya.business.vo.CommoditySpecRelateVO;
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
@RequestMapping(value = "commoditySpecRelate")
@Slf4j
public class CommoditySpecRelateController extends AbstractController {
    @Autowired
    private CommoditySpecRelateService commoditySpecRelateService;

    @PostMapping(value = "countByClause")
    public Result<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        CommoditySpecRelateVO record = JSONObject.parseObject(json, CommoditySpecRelateVO.class);
        return success(MessageEnum.SUCCESS, commoditySpecRelateService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public Result<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        CommoditySpecRelateVO record = JSONObject.parseObject(json, CommoditySpecRelateVO.class);
        return doProcess(json, () -> {
            return commoditySpecRelateService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public Result<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommoditySpecRelateVO record = JSONObject.parseObject(json, CommoditySpecRelateVO.class);
        return doProcess(json, () -> {
            return commoditySpecRelateService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public Result<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        CommoditySpecRelate record = JSONObject.parseObject(json, CommoditySpecRelate.class);
        return doProcess(json, () -> {
            return commoditySpecRelateService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public Result<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        CommoditySpecRelate record = JSONObject.parseObject(json, CommoditySpecRelate.class);
        return doProcess(json, () -> {
            return commoditySpecRelateService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public Result<List<CommoditySpecRelate>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        CommoditySpecRelate record = JSONObject.parseObject(json, CommoditySpecRelate.class);
        return success(MessageEnum.SUCCESS, commoditySpecRelateService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public Result<CommoditySpecRelate> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        CommoditySpecRelateVO record = JSONObject.parseObject(json, CommoditySpecRelateVO.class);
        return success(MessageEnum.SUCCESS, commoditySpecRelateService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public Result<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        CommoditySpecRelate record = JSONObject.parseObject(json, CommoditySpecRelate.class);
        return doProcess(json, () -> {
            return commoditySpecRelateService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public Result<PageInfo<CommoditySpecRelateVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        CommoditySpecRelateVO record = JSONObject.parseObject(json, CommoditySpecRelateVO.class);
        PageQo page = JSONObject.parseObject(json, PageQo.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, commoditySpecRelateService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public Result<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<CommoditySpecRelateVO> list = JSONObject.parseArray(json, CommoditySpecRelateVO.class);
        return doProcess(json, () -> {
            return commoditySpecRelateService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public Result<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<CommoditySpecRelate> list = JSONObject.parseArray(json, CommoditySpecRelate.class);
        return doProcess(json, () -> {
            return commoditySpecRelateService.insertSelectiveBatch(list);
        });
    }
}