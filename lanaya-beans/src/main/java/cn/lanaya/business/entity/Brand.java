package cn.lanaya.business.entity;

import cn.lanaya.common.annotation.FieldProperty;
import java.math.BigDecimal;
import java.util.Date;

public class Brand {
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
     * 退市时间
     */
    @FieldProperty(comment = "退市时间")
    private Date delistedTime;

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
    @FieldProperty(comment = "")
    private String updateuser;

    /**
     * 
     */
    @FieldProperty(comment = "")
    private Date updatetime;

    public Long getId() {
        return id;
    }

    public Brand setId(Long id) {
        this.id = id == null ? null : id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Brand setName(String name) {
        this.name = name == null ? null : name.trim();
        return this;
    }

    public String getFirstChar() {
        return firstChar;
    }

    public Brand setFirstChar(String firstChar) {
        this.firstChar = firstChar == null ? null : firstChar.trim();
        return this;
    }

    public Date getListedTime() {
        return listedTime;
    }

    public Brand setListedTime(Date listedTime) {
        this.listedTime = listedTime == null ? null : listedTime;
        return this;
    }

    public Date getDelistedTime() {
        return delistedTime;
    }

    public Brand setDelistedTime(Date delistedTime) {
        this.delistedTime = delistedTime == null ? null : delistedTime;
        return this;
    }

    public String getIndustry() {
        return industry;
    }

    public Brand setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
        return this;
    }

    public BigDecimal getPopularity() {
        return popularity;
    }

    public Brand setPopularity(BigDecimal popularity) {
        this.popularity = popularity == null ? null : popularity;
        return this;
    }

    public BigDecimal getReputation() {
        return reputation;
    }

    public Brand setReputation(BigDecimal reputation) {
        this.reputation = reputation == null ? null : reputation;
        return this;
    }

    public BigDecimal getPenetration() {
        return penetration;
    }

    public Brand setPenetration(BigDecimal penetration) {
        this.penetration = penetration == null ? null : penetration;
        return this;
    }

    public String getLogoId() {
        return logoId;
    }

    public Brand setLogoId(String logoId) {
        this.logoId = logoId == null ? null : logoId.trim();
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public Brand setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Brand setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
        return this;
    }

    public Integer getRowstate() {
        return rowstate;
    }

    public Brand setRowstate(Integer rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public Brand setVersion(Integer version) {
        this.version = version == null ? null : version;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public Brand setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Brand setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public Brand setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public Brand setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }
}