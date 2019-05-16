package cn.lanaya.business.vo;

import cn.lanaya.common.annotation.FieldProperty;
import java.math.BigDecimal;
import java.util.Date;

public class BusinessBrandVO {
    /**
     * 主键
     */
    @FieldProperty(comment = "主键", required = true)
    private Long id;

    /**
     * 品牌名称
     */
    @FieldProperty(comment = "品牌名称", required = true)
    private String name;

    /**
     * 名称首字母
     */
    @FieldProperty(comment = "名称首字母", required = true)
    private String firstChar;

    /**
     * 上市时间
     */
    @FieldProperty(comment = "上市时间")
    private Date listTime;

    /**
     * 上市时间
     */
    @FieldProperty(comment = "上市时间")
    private Date listTimeStart;

    /**
     * 上市时间
     */
    @FieldProperty(comment = "上市时间")
    private Date listTimeEnd;

    /**
     * 退市时间
     */
    @FieldProperty(comment = "退市时间")
    private Date delistTime;

    /**
     * 退市时间
     */
    @FieldProperty(comment = "退市时间")
    private Date delistTimeStart;

    /**
     * 退市时间
     */
    @FieldProperty(comment = "退市时间")
    private Date delistTimeEnd;

    /**
     * 所属行业
     */
    @FieldProperty(comment = "所属行业")
    private String industry;

    /**
     * 知名度
     */
    @FieldProperty(comment = "知名度")
    private BigDecimal popularity;

    /**
     * 美誉度
     */
    @FieldProperty(comment = "美誉度")
    private BigDecimal reputation;

    /**
     * 普及度
     */
    @FieldProperty(comment = "普及度")
    private BigDecimal penetration;

    /**
     * logo主键
     */
    @FieldProperty(comment = "logo主键")
    private String logoId;

    /**
     * 所属公司id
     */
    @FieldProperty(comment = "所属公司id")
    private String companyId;

    /**
     * 所属公司名称
     */
    @FieldProperty(comment = "所属公司名称")
    private String companyName;

    /**
     * 状态
     */
    @FieldProperty(comment = "状态")
    private Integer rowstate;

    /**
     * 版本号
     */
    @FieldProperty(comment = "版本号")
    private Integer version;

    /**
     * 
     */
    @FieldProperty(comment = "")
    private String createuser;

    /**
     * 
     */
    @FieldProperty(comment = "", required = true)
    private Date createtime;

    /**
     * 
     */
    @FieldProperty(comment = "", required = true)
    private Date createtimeStart;

    /**
     * 
     */
    @FieldProperty(comment = "", required = true)
    private Date createtimeEnd;

    /**
     * 
     */
    @FieldProperty(comment = "")
    private String updateuser;

    /**
     * 
     */
    @FieldProperty(comment = "")
    private Date updatetime;

    /**
     * 
     */
    @FieldProperty(comment = "")
    private Date updatetimeStart;

    /**
     * 
     */
    @FieldProperty(comment = "")
    private Date updatetimeEnd;

    public Long getId() {
        return id;
    }

    public BusinessBrandVO setId(Long id) {
        this.id = id == null ? null : id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BusinessBrandVO setName(String name) {
        this.name = name == null ? null : name.trim();
        return this;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public BusinessBrandVO setFirstChar(String firstChar) {
        this.firstChar = firstChar == null ? null : firstChar.trim();
        return this;
    }

    public Date getListTime() {
        return listTime;
    }

    public BusinessBrandVO setListTime(Date listTime) {
        this.listTime = listTime == null ? null : listTime;
        return this;
    }

    public Date getListTimeStart() {
        return listTimeStart;
    }

    public BusinessBrandVO setListTimeStart(Date listTimeStart) {
        this.listTimeStart = listTimeStart == null ? null : listTimeStart;
        return this;
    }

    public Date getListTimeEnd() {
        return listTimeEnd;
    }

    public BusinessBrandVO setListTimeEnd(Date listTimeEnd) {
        this.listTimeEnd = listTimeEnd == null ? null : listTimeEnd;
        return this;
    }

    public Date getDelistTime() {
        return delistTime;
    }

    public BusinessBrandVO setDelistTime(Date delistTime) {
        this.delistTime = delistTime == null ? null : delistTime;
        return this;
    }

    public Date getDelistTimeStart() {
        return delistTimeStart;
    }

    public BusinessBrandVO setDelistTimeStart(Date delistTimeStart) {
        this.delistTimeStart = delistTimeStart == null ? null : delistTimeStart;
        return this;
    }

    public Date getDelistTimeEnd() {
        return delistTimeEnd;
    }

    public BusinessBrandVO setDelistTimeEnd(Date delistTimeEnd) {
        this.delistTimeEnd = delistTimeEnd == null ? null : delistTimeEnd;
        return this;
    }

    public String getIndustry() {
        return industry;
    }

    public BusinessBrandVO setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
        return this;
    }

    public BigDecimal getPopularity() {
        return popularity;
    }

    public BusinessBrandVO setPopularity(BigDecimal popularity) {
        this.popularity = popularity == null ? null : popularity;
        return this;
    }

    public BigDecimal getReputation() {
        return reputation;
    }

    public BusinessBrandVO setReputation(BigDecimal reputation) {
        this.reputation = reputation == null ? null : reputation;
        return this;
    }

    public BigDecimal getPenetration() {
        return penetration;
    }

    public BusinessBrandVO setPenetration(BigDecimal penetration) {
        this.penetration = penetration == null ? null : penetration;
        return this;
    }

    public String getLogoId() {
        return logoId;
    }

    public BusinessBrandVO setLogoId(String logoId) {
        this.logoId = logoId == null ? null : logoId.trim();
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public BusinessBrandVO setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public BusinessBrandVO setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
        return this;
    }

    public Integer getRowstate() {
        return rowstate;
    }

    public BusinessBrandVO setRowstate(Integer rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public BusinessBrandVO setVersion(Integer version) {
        this.version = version == null ? null : version;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public BusinessBrandVO setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public BusinessBrandVO setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public Date getCreatetimeStart() {
        return createtimeStart;
    }

    public BusinessBrandVO setCreatetimeStart(Date createtimeStart) {
        this.createtimeStart = createtimeStart == null ? null : createtimeStart;
        return this;
    }

    public Date getCreatetimeEnd() {
        return createtimeEnd;
    }

    public BusinessBrandVO setCreatetimeEnd(Date createtimeEnd) {
        this.createtimeEnd = createtimeEnd == null ? null : createtimeEnd;
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public BusinessBrandVO setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public BusinessBrandVO setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }

    public Date getUpdatetimeStart() {
        return updatetimeStart;
    }

    public BusinessBrandVO setUpdatetimeStart(Date updatetimeStart) {
        this.updatetimeStart = updatetimeStart == null ? null : updatetimeStart;
        return this;
    }

    public Date getUpdatetimeEnd() {
        return updatetimeEnd;
    }

    public BusinessBrandVO setUpdatetimeEnd(Date updatetimeEnd) {
        this.updatetimeEnd = updatetimeEnd == null ? null : updatetimeEnd;
        return this;
    }
}