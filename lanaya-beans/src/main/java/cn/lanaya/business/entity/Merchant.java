package cn.lanaya.business.entity;

import cn.lanaya.common.annotation.FieldProperty;
import java.util.Date;

public class Merchant {
    /**
     * 
     */
    @FieldProperty(comment = "", required = true)
    private String id;

    /**
     * 用户名
     */
    @FieldProperty(comment = "用户名", required = true)
    private String username;

    /**
     * 密码，加密存储
     */
    @FieldProperty(comment = "密码，加密存储", required = true)
    private String password;

    /**
     * 注册手机号
     */
    @FieldProperty(comment = "注册手机号")
    private String phone;

    /**
     * 注册邮箱
     */
    @FieldProperty(comment = "注册邮箱")
    private String email;

    /**
     * 商户状态，0-初始化，1-审核通过，2-审核中，3-审核拒绝，-1-注销，-2-冻结
     */
    @FieldProperty(comment = "商户状态，0-初始化，1-审核通过，2-审核中，3-审核拒绝，-1-注销，-2-冻结", required = true)
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

    public String getId() {
        return id;
    }

    public Merchant setId(String id) {
        this.id = id == null ? null : id.trim();
        return this;
    }

    public String getUsername() {
        return username;
    }

    public Merchant setUsername(String username) {
        this.username = username == null ? null : username.trim();
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Merchant setPassword(String password) {
        this.password = password == null ? null : password.trim();
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Merchant setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Merchant setEmail(String email) {
        this.email = email == null ? null : email.trim();
        return this;
    }

    public Byte getStatus() {
        return status;
    }

    public Merchant setStatus(Byte status) {
        this.status = status == null ? null : status;
        return this;
    }

    public Byte getRowstate() {
        return rowstate;
    }

    public Merchant setRowstate(Byte rowstate) {
        this.rowstate = rowstate == null ? null : rowstate;
        return this;
    }

    public Long getVersion() {
        return version;
    }

    public Merchant setVersion(Long version) {
        this.version = version == null ? null : version;
        return this;
    }

    public String getCreateuser() {
        return createuser;
    }

    public Merchant setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
        return this;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public Merchant setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public Merchant setCreatetime(Date createtime) {
        this.createtime = createtime == null ? null : createtime;
        return this;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public Merchant setUpdatetime(Date updatetime) {
        this.updatetime = updatetime == null ? null : updatetime;
        return this;
    }
}