package cn.lanaya.business.entity;

import cn.lanaya.common.annotation.FieldProperty;
import java.util.Date;

public class CommoditySpec {
    /**
     * 
     */
    @FieldProperty(comment = "", required = true)
    private Long id;

    /**
     * 商品类目ID
     */
    @FieldProperty(comment = "商品类目ID")
    private Long commodityCatId;

    /**
     * 参数数据，格式为json格式
     */
    @FieldProperty(comment = "参数数据，格式为json格式")
    private String specData;

    /**
     * 数据状态，1-正常，0-无效，-1-禁用
     */
    @FieldProperty(comment = "数据状态，1-正常，0-无效，-1-禁用", required = true)
    private Byte rowstate;

    /**
     * 版本号
     */
    @FieldProperty(comment = "版本号", required = true)
    private Long version;

    /**
     * 创建者
     */
    @FieldProperty(comment = "创建者")
    private String createuser;

    /**
     * 更新者
     */
    @FieldProperty(comment = "更新者")
    private String updateuser;

    /**
     * 创建时间
     */
    @FieldProperty(comment = "创建时间", required = true)
    private Date createtime;

    /**
     * 更新时间
     */
    @FieldProperty(comment = "更新时间", required = true)
    private Date updatetime;

    public Long getId() {
        return id;
    }

    public CommoditySpec setId(Long id) {
        this.id = id == null ? null : id;
        return this;
    }

    public Long getCommodityCatId() {
        return commodityCatId;
    }

    public CommoditySpec setCommodityCatId(Long commodityCatId) {
        this.commodityCatId = commodityCatId == null ? null : commodityCatId;
        return this;
    }

    public String getSpecData() {
        return specData;
    }

    public CommoditySpec setSpecData(String specData) {
        this.specData = specData == null ? null : specData.trim();
        return this;
    }

    public Byte getRowstate() {
        return rowstate;
    }

    public CommoditySpec setRowstate(Byte rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public CommoditySpec setVersion(Long version) {
        this.version = version == null ? null : version;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public CommoditySpec setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public CommoditySpec setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public CommoditySpec setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public CommoditySpec setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }
}