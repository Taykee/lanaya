package cn.lanaya.business.web;

import cn.lanaya.business.entity.Brand;
import cn.lanaya.business.service.BrandService;
import cn.lanaya.business.vo.BrandVO;
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
@RequestMapping(value = "Brand")
public class BrandController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    @PostMapping(value = "countByClause")
    public R<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        BrandVO record = JSONObject.parseObject(json, BrandVO.class);
        return success(MessageEnum.SUCCESS, brandService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public R<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        BrandVO record = JSONObject.parseObject(json, BrandVO.class);
        return doProcess(() -> {
            return brandService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public R<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        BrandVO record = JSONObject.parseObject(json, BrandVO.class);
        return doProcess(() -> {
            return brandService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public R<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        Brand record = JSONObject.parseObject(json, Brand.class);
        return doProcess(() -> {
            return brandService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public R<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        Brand record = JSONObject.parseObject(json, Brand.class);
        return doProcess(() -> {
            return brandService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public R<List<Brand>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        Brand record = JSONObject.parseObject(json, Brand.class);
        return success(MessageEnum.SUCCESS, brandService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public R<Brand> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        BrandVO record = JSONObject.parseObject(json, BrandVO.class);
        return success(MessageEnum.SUCCESS, brandService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public R<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        Brand record = JSONObject.parseObject(json, Brand.class);
        return doProcess(() -> {
            return brandService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public R<PageInfo<BrandVO>> pageByClause(@RequestBody(required = false) String json) {
        log.info("pageByClause", json);
        BrandVO record = JSONObject.parseObject(json, BrandVO.class);
        PageQO page = JSONObject.parseObject(json, PageQO.class);
        page.addParam("entity", record);
        return success(MessageEnum.SUCCESS, brandService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public R<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<BrandVO> list = JSONObject.parseArray(json, BrandVO.class);
        return doProcess(() -> {
            return brandService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public R<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<Brand> list = JSONObject.parseArray(json, Brand.class);
        return doProcess(() -> {
            return brandService.insertSelectiveBatch(list);
        });
    }
}