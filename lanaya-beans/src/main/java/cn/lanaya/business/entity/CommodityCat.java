package cn.lanaya.business.entity;

import cn.lanaya.common.annotation.FieldProperty;
import java.util.Date;

public class CommodityCat {
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
    private Date updatetime;

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

    public CommodityCat setId(Long id) {
        this.id = id == null ? null : id;
        return this;
    }

    public Long getParentId() {
        return parentId;
    }

    public CommodityCat setParentId(Long parentId) {
        this.parentId = parentId == null ? null : parentId;
        return this;
    }

    public String getName() {
        return name;
    }

    public CommodityCat setName(String name) {
        this.name = name == null ? null : name.trim();
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public CommodityCat setStatus(Integer status) {
        this.status = status == null ? null : status;
        return this;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public CommodityCat setOrdered(Integer ordered) {
        this.ordered = ordered == null ? null : ordered;
        return this;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public CommodityCat setIsParent(Boolean isParent) {
        this.isParent = isParent == null ? null : isParent;
        return this;
    }

    public Boolean getRowstate() {
        return rowstate;
    }

    public CommodityCat setRowstate(Boolean rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public CommodityCat setVersion(Long version) {
        this.version = version == null ? null : version;
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public CommodityCat setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public CommodityCat setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public CommodityCat setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public CommodityCat setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }
}