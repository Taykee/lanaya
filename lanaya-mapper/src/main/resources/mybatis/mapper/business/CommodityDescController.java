package cn.lanaya.business.web;

import cn.lanaya.business.entity.CommodityDesc;
import cn.lanaya.business.service.CommodityDescService;
import cn.lanaya.business.vo.CommodityDescVO;
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
@RequestMapping(value = "commodityDesc")
@Slf4j
public class CommodityDescController extends AbstractController {
    @Autowired
    private CommodityDescService commodityDescService;

    @PostMapping(value = "countByClause")
    public Result<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        CommodityDescVO record = JSONObject.parseObject(json, CommodityDescVO.class);
        return success(MessageEnum.SUCCESS, commodityDescService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public Result<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        CommodityDescVO record = JSONObject.parseObject(json, CommodityDescVO.class);
        return doProcess(json, () -> {
            return commodityDescService.deleteByClause(record);
        });
    }

    @PostMapping(value = "insert")
    public Result<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        CommodityDesc record = JSONObject.parseObject(json, CommodityDesc.class);
        return doProcess(json, () -> {
            return commodityDescService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public Result<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        CommodityDesc record = JSONObject.parseObject(json, CommodityDesc.class);
        return doProcess(json, () -> {
            return commodityDescService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public Result<List<CommodityDesc>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        CommodityDesc record = JSONObject.parseObject(json, CommodityDesc.class);
        return success(MessageEnum.SUCCESS, commodityDescService.selectByClause(record));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public Result<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        CommodityDesc record = JSONObject.parseObject(json, CommodityDesc.class);
        return doProcess(json, () -> {
            return commodityDescService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public Result<PageInfo<CommodityDescVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        CommodityDescVO record = JSONObject.parseObject(json, CommodityDescVO.class);
        PageQo page = JSONObject.parseObject(json, PageQo.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, commodityDescService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public Result<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<CommodityDescVO> list = JSONObject.parseArray(json, CommodityDescVO.class);
        return doProcess(json, () -> {
            return commodityDescService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public Result<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<CommodityDesc> list = JSONObject.parseArray(json, CommodityDesc.class);
        return doProcess(json, () -> {
            return commodityDescService.insertSelectiveBatch(list);
        });
    }
}