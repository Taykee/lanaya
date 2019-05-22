package cn.lanaya.business.web;

import cn.lanaya.business.entity.Brand;
import cn.lanaya.business.service.BrandService;
import cn.lanaya.business.vo.BrandVO;
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
@RequestMapping(value = "brand")
@Slf4j
public class BrandController extends AbstractController {
    @Autowired
    private BrandService brandService;

    @PostMapping(value = "countByClause")
    public Result<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        BrandVO record = JSONObject.parseObject(json, BrandVO.class);
        return success(MessageEnum.SUCCESS, brandService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public Result<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        BrandVO record = JSONObject.parseObject(json, BrandVO.class);
        return doProcess(json, () -> {
            return brandService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public Result<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        BrandVO record = JSONObject.parseObject(json, BrandVO.class);
        return doProcess(json, () -> {
            return brandService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public Result<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        Brand record = JSONObject.parseObject(json, Brand.class);
        return doProcess(json, () -> {
            return brandService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public Result<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        Brand record = JSONObject.parseObject(json, Brand.class);
        return doProcess(json, () -> {
            return brandService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public Result<List<Brand>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        Brand record = JSONObject.parseObject(json, Brand.class);
        return success(MessageEnum.SUCCESS, brandService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public Result<Brand> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        BrandVO record = JSONObject.parseObject(json, BrandVO.class);
        return success(MessageEnum.SUCCESS, brandService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public Result<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        Brand record = JSONObject.parseObject(json, Brand.class);
        return doProcess(json, () -> {
            return brandService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public Result<PageInfo<BrandVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        BrandVO record = JSONObject.parseObject(json, BrandVO.class);
        PageQo page = JSONObject.parseObject(json, PageQo.class);
        page.getParams().put("entity", record);
        return success(MessageEnum.SUCCESS, brandService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public Result<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<BrandVO> list = JSONObject.parseArray(json, BrandVO.class);
        return doProcess(json, () -> {
            return brandService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public Result<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<Brand> list = JSONObject.parseArray(json, Brand.class);
        return doProcess(json, () -> {
            return brandService.insertSelectiveBatch(list);
        });
    }
}