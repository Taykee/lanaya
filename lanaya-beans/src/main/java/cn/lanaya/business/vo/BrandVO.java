package cn.lanaya.business.vo;

import cn.lanaya.common.annotation.FieldProperty;
import java.math.BigDecimal;
import java.util.Date;

public class BrandVO {
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
    private Date listedTime;

    /**
     * 上市时间
     */
    @FieldProperty(comment = "上市时间")
    private Date listedTimeStart;

    /**
     * 上市时间
     */
    @FieldProperty(comment = "上市时间")
    private Date listedTimeEnd;

    /**
     * 退市时间
     */
    @FieldProperty(comment = "退市时间")
    private Date delistedTime;

    /**
     * 退市时间
     */
    @FieldProperty(comment = "退市时间")
    private Date delistedTimeStart;

    /**
     * 退市时间
     */
    @FieldProperty(comment = "退市时间")
    private Date delistedTimeEnd;

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

    public BrandVO setId(Long id) {
        this.id = id == null ? null : id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BrandVO setName(String name) {
        this.name = name == null ? null : name.trim();
        return this;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public BrandVO setFirstChar(String firstChar) {
        this.firstChar = firstChar == null ? null : firstChar.trim();
        return this;
    }

    public Date getListedTime() {
        return listedTime;
    }

    public BrandVO setListedTime(Date listedTime) {
        this.listedTime = listedTime == null ? null : listedTime;
        return this;
    }

    public Date getListedTimeStart() {
        return listedTimeStart;
    }

    public BrandVO setListedTimeStart(Date listedTimeStart) {
        this.listedTimeStart = listedTimeStart == null ? null : listedTimeStart;
        return this;
    }

    public Date getListedTimeEnd() {
        return listedTimeEnd;
    }

    public BrandVO setListedTimeEnd(Date listedTimeEnd) {
        this.listedTimeEnd = listedTimeEnd == null ? null : listedTimeEnd;
        return this;
    }

    public Date getDelistedTime() {
        return delistedTime;
    }

    public BrandVO setDelistedTime(Date delistedTime) {
        this.delistedTime = delistedTime == null ? null : delistedTime;
        return this;
    }

    public Date getDelistedTimeStart() {
        return delistedTimeStart;
    }

    public BrandVO setDelistedTimeStart(Date delistedTimeStart) {
        this.delistedTimeStart = delistedTimeStart == null ? null : delistedTimeStart;
        return this;
    }

    public Date getDelistedTimeEnd() {
        return delistedTimeEnd;
    }

    public BrandVO setDelistedTimeEnd(Date delistedTimeEnd) {
        this.delistedTimeEnd = delistedTimeEnd == null ? null : delistedTimeEnd;
        return this;
    }

    public String getIndustry() {
        return industry;
    }

    public BrandVO setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
        return this;
    }

    public BigDecimal getPopularity() {
        return popularity;
    }

    public BrandVO setPopularity(BigDecimal popularity) {
        this.popularity = popularity == null ? null : popularity;
        return this;
    }

    public BigDecimal getReputation() {
        return reputation;
    }

    public BrandVO setReputation(BigDecimal reputation) {
        this.reputation = reputation == null ? null : reputation;
        return this;
    }

    public BigDecimal getPenetration() {
        return penetration;
    }

    public BrandVO setPenetration(BigDecimal penetration) {
        this.penetration = penetration == null ? null : penetration;
        return this;
    }

    public String getLogoId() {
        return logoId;
    }

    public BrandVO setLogoId(String logoId) {
        this.logoId = logoId == null ? null : logoId.trim();
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public BrandVO setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public BrandVO setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
        return this;
    }

    public Integer getRowstate() {
        return rowstate;
    }

    public BrandVO setRowstate(Integer rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public BrandVO setVersion(Integer version) {
        this.version = version == null ? null : version;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public BrandVO setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public BrandVO setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public Date getCreatetimeStart() {
        return createtimeStart;
    }

    public BrandVO setCreatetimeStart(Date createtimeStart) {
        this.createtimeStart = createtimeStart == null ? null : createtimeStart;
        return this;
    }

    public Date getCreatetimeEnd() {
        return createtimeEnd;
    }

    public BrandVO setCreatetimeEnd(Date createtimeEnd) {
        this.createtimeEnd = createtimeEnd == null ? null : createtimeEnd;
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public BrandVO setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public BrandVO setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }

    public Date getUpdatetimeStart() {
        return updatetimeStart;
    }

    public BrandVO setUpdatetimeStart(Date updatetimeStart) {
        this.updatetimeStart = updatetimeStart == null ? null : updatetimeStart;
        return this;
    }

    public Date getUpdatetimeEnd() {
        return updatetimeEnd;
    }

    public BrandVO setUpdatetimeEnd(Date updatetimeEnd) {
        this.updatetimeEnd = updatetimeEnd == null ? null : updatetimeEnd;
        return this;
    }
}