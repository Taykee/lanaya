package cn.lanaya.business.vo;

import cn.lanaya.common.annotation.FieldProperty;
import java.util.Date;

public class CommoditySpecVO {
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
     * 创建时间
     */
    @FieldProperty(comment = "创建时间", required = true)
    private Date createtimeStart;

    /**
     * 创建时间
     */
    @FieldProperty(comment = "创建时间", required = true)
    private Date createtimeEnd;

    /**
     * 更新时间
     */
    @FieldProperty(comment = "更新时间", required = true)
    private Date updatetime;

    /**
     * 更新时间
     */
    @FieldProperty(comment = "更新时间", required = true)
    private Date updatetimeStart;

    /**
     * 更新时间
     */
    @FieldProperty(comment = "更新时间", required = true)
    private Date updatetimeEnd;

    public Long getId() {
        return id;
    }

    public CommoditySpecVO setId(Long id) {
        this.id = id == null ? null : id;
        return this;
    }

    public Long getCommodityCatId() {
        return commodityCatId;
    }

    public CommoditySpecVO setCommodityCatId(Long commodityCatId) {
        this.commodityCatId = commodityCatId == null ? null : commodityCatId;
        return this;
    }

    public String getSpecData() {
        return specData;
    }

    public CommoditySpecVO setSpecData(String specData) {
        this.specData = specData == null ? null : specData.trim();
        return this;
    }

    public Byte getRowstate() {
        return rowstate;
    }

    public CommoditySpecVO setRowstate(Byte rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public CommoditySpecVO setVersion(Long version) {
        this.version = version == null ? null : version;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public CommoditySpecVO setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public CommoditySpecVO setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public CommoditySpecVO setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public Date getCreatetimeStart() {
        return createtimeStart;
    }

    public CommoditySpecVO setCreatetimeStart(Date createtimeStart) {
        this.createtimeStart = createtimeStart == null ? null : createtimeStart;
        return this;
    }

    public Date getCreatetimeEnd() {
        return createtimeEnd;
    }

    public CommoditySpecVO setCreatetimeEnd(Date createtimeEnd) {
        this.createtimeEnd = createtimeEnd == null ? null : createtimeEnd;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public CommoditySpecVO setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }

    public Date getUpdatetimeStart() {
        return updatetimeStart;
    }

    public CommoditySpecVO setUpdatetimeStart(Date updatetimeStart) {
        this.updatetimeStart = updatetimeStart == null ? null : updatetimeStart;
        return this;
    }

    public Date getUpdatetimeEnd() {
        return updatetimeEnd;
    }

    public CommoditySpecVO setUpdatetimeEnd(Date updatetimeEnd) {
        this.updatetimeEnd = updatetimeEnd == null ? null : updatetimeEnd;
        return this;
    }
}