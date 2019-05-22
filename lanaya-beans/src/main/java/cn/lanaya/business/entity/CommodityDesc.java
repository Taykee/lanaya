package cn.lanaya.business.entity;

import cn.lanaya.common.annotation.FieldProperty;
import java.util.Date;

public class CommodityDesc {
    /**
     * 商品ID
     */
    @FieldProperty(comment = "商品ID")
    private Long commodityId;

    /**
     * 商品描述
     */
    @FieldProperty(comment = "商品描述")
    private String instruction;

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

    public Long getCommodityId() {
        return commodityId;
    }

    public CommodityDesc setCommodityId(Long commodityId) {
        this.commodityId = commodityId == null ? null : commodityId;
        return this;
    }

    public String getInstruction() {
        return instruction;
    }

    public CommodityDesc setInstruction(String instruction) {
        this.instruction = instruction == null ? null : instruction.trim();
        return this;
    }

    public Byte getRowstate() {
        return rowstate;
    }

    public CommodityDesc setRowstate(Byte rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public CommodityDesc setVersion(Long version) {
        this.version = version == null ? null : version;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public CommodityDesc setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public CommodityDesc setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public CommodityDesc setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public CommodityDesc setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }
}