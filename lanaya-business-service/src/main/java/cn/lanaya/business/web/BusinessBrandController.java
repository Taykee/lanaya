package cn.lanaya.business.web;

import cn.lanaya.business.entity.BusinessBrand;
import cn.lanaya.business.service.BusinessBrandService;
import cn.lanaya.business.vo.BusinessBrandVO;
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
@RequestMapping(value = "businessBrand")
public class BusinessBrandController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(BusinessBrandController.class);

    @Autowired
    private BusinessBrandService businessBrandService;

    @PostMapping(value = "countByClause")
    public R<Object> countByClause(@RequestBody String json) {
        log.info("countByClause", json);
        BusinessBrandVO record = JSONObject.parseObject(json, BusinessBrandVO.class);
        return success(MessageEnum.SUCCESS, businessBrandService.countByClause(record));
    }

    @PostMapping(value = "deleteByClause")
    public R<Object> deleteByClause(@RequestBody String json) {
        log.info("deleteByClause", json);
        BusinessBrandVO record = JSONObject.parseObject(json, BusinessBrandVO.class);
        return doProcess(() -> {
            return businessBrandService.deleteByClause(record);
        });
    }

    @PostMapping(value = "deleteByPrimaryKey")
    public R<Object> deleteByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        BusinessBrandVO record = JSONObject.parseObject(json, BusinessBrandVO.class);
        return doProcess(() -> {
            return businessBrandService.deleteByPrimaryKey(record.getId());
        });
    }

    @PostMapping(value = "insert")
    public R<Object> insert(@RequestBody String json) {
        log.info("insert", json);
        BusinessBrand record = JSONObject.parseObject(json, BusinessBrand.class);
        return doProcess(() -> {
            return businessBrandService.insert(record);
        });
    }

    @PostMapping(value = "insertSelective")
    public R<Object> insertSelective(@RequestBody String json) {
        log.info("insertSelective", json);
        BusinessBrand record = JSONObject.parseObject(json, BusinessBrand.class);
        return doProcess(() -> {
            return businessBrandService.insertSelective(record);
        });
    }

    @PostMapping(value = "selectByClause")
    public R<List<BusinessBrand>> selectByClause(@RequestBody String json) {
        log.info("selectByClause", json);
        BusinessBrand record = JSONObject.parseObject(json, BusinessBrand.class);
        return success(MessageEnum.SUCCESS, businessBrandService.selectByClause(record));
    }

    @PostMapping(value = "selectByPrimaryKey")
    public R<BusinessBrand> selectByPrimaryKey(@RequestBody String json) {
        log.info("deleteByPrimaryKey", json);
        BusinessBrandVO record = JSONObject.parseObject(json, BusinessBrandVO.class);
        return success(MessageEnum.SUCCESS, businessBrandService.selectByPrimaryKey(record.getId()));
    }

    @PostMapping(value = "updateByPrimaryKeySelective")
    public R<Object> updateByPrimaryKeySelective(@RequestBody String json) {
        log.info("updateByPrimaryKeySelective", json);
        BusinessBrand record = JSONObject.parseObject(json, BusinessBrand.class);
        return doProcess(() -> {
            return businessBrandService.updateByPrimaryKeySelective(record);
        });
    }

    @PostMapping(value = "pageByClause")
    public R<PageInfo<BusinessBrandVO>> pageByClause(@RequestBody String json) {
        log.info("pageByClause", json);
        BusinessBrandVO record = JSONObject.parseObject(json, BusinessBrandVO.class);
        PageQO page = JSONObject.parseObject(json, PageQO.class);
        page.addParam("entity", record);
        return success(MessageEnum.SUCCESS, businessBrandService.pageByClause(page));
    }

    @PostMapping(value = "deleteByPrimaryKeyBatch")
    public R<Object> deleteByPrimaryKeyBatch(@RequestBody String json) {
        log.info("deleteByPrimaryKeyBatch", json);
        List<BusinessBrandVO> list = JSONObject.parseArray(json, BusinessBrandVO.class);
        return doProcess(() -> {
            return businessBrandService.deleteByPrimaryKeyBatch(list);
        });
    }

    @PostMapping(value = "insertSelectiveBatch")
    public R<Object> insertSelectiveBatch(@RequestBody String json) {
        log.info("insertSelectiveBatch", json);
        List<BusinessBrand> list = JSONObject.parseArray(json, BusinessBrand.class);
        return doProcess(() -> {
            return businessBrandService.insertSelectiveBatch(list);
        });
    }
}