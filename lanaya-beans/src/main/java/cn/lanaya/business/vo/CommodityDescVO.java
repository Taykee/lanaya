package cn.lanaya.business.vo;

import cn.lanaya.common.annotation.FieldProperty;
import java.util.Date;

public class CommodityDescVO {
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

    public Long getCommodityId() {
        return commodityId;
    }

    public CommodityDescVO setCommodityId(Long commodityId) {
        this.commodityId = commodityId == null ? null : commodityId;
        return this;
    }

    public String getInstruction() {
        return instruction;
    }

    public CommodityDescVO setInstruction(String instruction) {
        this.instruction = instruction == null ? null : instruction.trim();
        return this;
    }

    public Byte getRowstate() {
        return rowstate;
    }

    public CommodityDescVO setRowstate(Byte rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public CommodityDescVO setVersion(Long version) {
        this.version = version == null ? null : version;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public CommodityDescVO setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public CommodityDescVO setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public CommodityDescVO setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public Date getCreatetimeStart() {
        return createtimeStart;
    }

    public CommodityDescVO setCreatetimeStart(Date createtimeStart) {
        this.createtimeStart = createtimeStart == null ? null : createtimeStart;
        return this;
    }

    public Date getCreatetimeEnd() {
        return createtimeEnd;
    }

    public CommodityDescVO setCreatetimeEnd(Date createtimeEnd) {
        this.createtimeEnd = createtimeEnd == null ? null : createtimeEnd;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public CommodityDescVO setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }

    public Date getUpdatetimeStart() {
        return updatetimeStart;
    }

    public CommodityDescVO setUpdatetimeStart(Date updatetimeStart) {
        this.updatetimeStart = updatetimeStart == null ? null : updatetimeStart;
        return this;
    }

    public Date getUpdatetimeEnd() {
        return updatetimeEnd;
    }

    public CommodityDescVO setUpdatetimeEnd(Date updatetimeEnd) {
        this.updatetimeEnd = updatetimeEnd == null ? null : updatetimeEnd;
        return this;
    }
}