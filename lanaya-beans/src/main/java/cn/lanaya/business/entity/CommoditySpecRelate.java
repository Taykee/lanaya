package cn.lanaya.business.entity;

import cn.lanaya.common.annotation.FieldProperty;
import java.util.Date;

public class CommoditySpecRelate {
    /**
     * 
     */
    @FieldProperty(comment = "", required = true)
    private Long id;

    /**
     * 商品ID
     */
    @FieldProperty(comment = "商品ID")
    private Long commodityId;

    /**
     * 参数数据，格式为json格式
     */
    @FieldProperty(comment = "参数数据，格式为json格式")
    private String data;

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

    public CommoditySpecRelate setId(Long id) {
        this.id = id == null ? null : id;
        return this;
    }

    public Long getCommodityId() {
        return commodityId;
    }

    public CommoditySpecRelate setCommodityId(Long commodityId) {
        this.commodityId = commodityId == null ? null : commodityId;
        return this;
    }

    public String getData() {
        return data;
    }

    public CommoditySpecRelate setData(String data) {
        this.data = data == null ? null : data.trim();
        return this;
    }

    public Byte getRowstate() {
        return rowstate;
    }

    public CommoditySpecRelate setRowstate(Byte rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public CommoditySpecRelate setVersion(Long version) {
        this.version = version == null ? null : version;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public CommoditySpecRelate setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public CommoditySpecRelate setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public CommoditySpecRelate setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public CommoditySpecRelate setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }
}