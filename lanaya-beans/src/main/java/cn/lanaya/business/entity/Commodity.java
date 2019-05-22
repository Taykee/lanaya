package cn.lanaya.business.entity;

import cn.lanaya.common.annotation.FieldProperty;
import java.math.BigDecimal;
import java.util.Date;

public class Commodity {
    /**
     * 商品id，同时也是商品编号
     */
    @FieldProperty(comment = "商品id，同时也是商品编号", required = true)
    private Long id;

    /**
     * 商品标题
     */
    @FieldProperty(comment = "商品标题", required = true)
    private String title;

    /**
     * 商品卖点
     */
    @FieldProperty(comment = "商品卖点")
    private String sellPoint;

    /**
     * 商品价格，单位为：元
     */
    @FieldProperty(comment = "商品价格，单位为：元", required = true)
    private BigDecimal price;

    /**
     * 库存数量
     */
    @FieldProperty(comment = "库存数量", required = true)
    private Integer num;

    /**
     * 商品条形码
     */
    @FieldProperty(comment = "商品条形码")
    private String barcode;

    /**
     * 所属类目，叶子类目
     */
    @FieldProperty(comment = "所属类目，叶子类目", required = true)
    private Long cid;

    /**
     * 商品状态，1-正常，2-下架，-1-删除
     */
    @FieldProperty(comment = "商品状态，1-正常，2-下架，-1-删除", required = true)
    private Byte status;

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

    public Commodity setId(Long id) {
        this.id = id == null ? null : id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Commodity setTitle(String title) {
        this.title = title == null ? null : title.trim();
        return this;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public Commodity setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Commodity setPrice(BigDecimal price) {
        this.price = price == null ? null : price;
        return this;
    }

    public Integer getNum() {
        return num;
    }

    public Commodity setNum(Integer num) {
        this.num = num == null ? null : num;
        return this;
    }

    public String getBarcode() {
        return barcode;
    }

    public Commodity setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
        return this;
    }

    public Long getCid() {
        return cid;
    }

    public Commodity setCid(Long cid) {
        this.cid = cid == null ? null : cid;
        return this;
    }

    public Byte getStatus() {
        return status;
    }

    public Commodity setStatus(Byte status) {
        this.status = status == null ? null : status;
        return this;
    }

    public Byte getRowstate() {
        return rowstate;
    }

    public Commodity setRowstate(Byte rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public Commodity setVersion(Long version) {
        this.version = version == null ? null : version;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public Commodity setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public Commodity setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Commodity setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public Commodity setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }
}