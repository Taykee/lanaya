package cn.lanaya.business.vo;

import cn.lanaya.common.annotation.FieldProperty;
import java.util.Date;

public class CommodityCatVO {
    /**
     * 类目ID
     */
    @FieldProperty(comment = "类目ID", required = true)
    private Long id;

    /**
     * 父类目ID=0时，代表的是一级的类目
     */
    @FieldProperty(comment = "父类目ID=0时，代表的是一级的类目")
    private Long parentId;

    /**
     * 类目名称
     */
    @FieldProperty(comment = "类目名称")
    private String name;

    /**
     * 状态。可选值:1(正常),2(删除)
     */
    @FieldProperty(comment = "状态。可选值:1(正常),2(删除)")
    private Integer status;

    /**
     * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     */
    @FieldProperty(comment = "排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数")
    private Integer ordered;

    /**
     * 该类目是否为父类目，1为true，0为false
     */
    @FieldProperty(comment = "该类目是否为父类目，1为true，0为false")
    private Boolean isParent;

    /**
     * 数据状态，1-正常，0-无效，-1-禁用
     */
    @FieldProperty(comment = "数据状态，1-正常，0-无效，-1-禁用")
    private Boolean rowstate;

    /**
     * 版本号
     */
    @FieldProperty(comment = "版本号")
    private Long version;

    /**
     * 创建时间
     */
    @FieldProperty(comment = "创建时间")
    private Date createtime;

    /**
     * 创建时间
     */
    @FieldProperty(comment = "创建时间")
    private Date createtimeStart;

    /**
     * 创建时间
     */
    @FieldProperty(comment = "创建时间")
    private Date createtimeEnd;

    /**
     * 创建时间
     */
    @FieldProperty(comment = "创建时间")
    private Date updatetime;

    /**
     * 创建时间
     */
    @FieldProperty(comment = "创建时间")
    private Date updatetimeStart;

    /**
     * 创建时间
     */
    @FieldProperty(comment = "创建时间")
    private Date updatetimeEnd;

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

    public Long getId() {
        return id;
    }

    public CommodityCatVO setId(Long id) {
        this.id = id == null ? null : id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public CommodityCatVO setParentId(Long parentId) {
        this.parentId = parentId == null ? null : parentId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CommodityCatVO setName(String name) {
        this.name = name == null ? null : name.trim();
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CommodityCatVO setStatus(Integer status) {
        this.status = status == null ? null : status;
        return this;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public CommodityCatVO setOrdered(Integer ordered) {
        this.ordered = ordered == null ? null : ordered;
        return this;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public CommodityCatVO setIsParent(Boolean isParent) {
        this.isParent = isParent == null ? null : isParent;
        return this;
    }

    public Boolean getRowstate() {
        return rowstate;
    }

    public CommodityCatVO setRowstate(Boolean rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public CommodityCatVO setVersion(Long version) {
        this.version = version == null ? null : version;
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public CommodityCatVO setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public Date getCreatetimeStart() {
        return createtimeStart;
    }

    public CommodityCatVO setCreatetimeStart(Date createtimeStart) {
        this.createtimeStart = createtimeStart == null ? null : createtimeStart;
        return this;
    }

    public Date getCreatetimeEnd() {
        return createtimeEnd;
    }

    public CommodityCatVO setCreatetimeEnd(Date createtimeEnd) {
        this.createtimeEnd = createtimeEnd == null ? null : createtimeEnd;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public CommodityCatVO setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }

    public Date getUpdatetimeStart() {
        return updatetimeStart;
    }

    public CommodityCatVO setUpdatetimeStart(Date updatetimeStart) {
        this.updatetimeStart = updatetimeStart == null ? null : updatetimeStart;
        return this;
    }

    public Date getUpdatetimeEnd() {
        return updatetimeEnd;
    }

    public CommodityCatVO setUpdatetimeEnd(Date updatetimeEnd) {
        this.updatetimeEnd = updatetimeEnd == null ? null : updatetimeEnd;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public CommodityCatVO setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public CommodityCatVO setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }
}