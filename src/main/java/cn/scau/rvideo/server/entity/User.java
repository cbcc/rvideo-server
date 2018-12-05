package cn.scau.rvideo.server.entity;

import cn.scau.rvideo.server.controller.validation.group.Register;
import cn.scau.rvideo.server.controller.validation.group.UpdateCustomFields;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    private Integer id;

    @NotNull(message = "昵称不能为空", groups = {Register.class, UpdateCustomFields.class})
    @Size(min = 1, max = 20, message = "用户名长度[1-20]", groups = {Register.class, UpdateCustomFields.class})
    private String name;

    @NotNull(message = "密码不能为空", groups = {Register.class})
    @Size(min = 6, max = 20, message = "密码长度[6-20]", groups = {Register.class})
    private String password;

    @NotNull(message = "电子邮件地址不能为空", groups = {Register.class})
    @Email(message = "无效的电子邮件地址", groups = {Register.class})
    private String email;

    @NotNull(message = "性别不能为空", groups = {UpdateCustomFields.class})
    private Integer sex;        // 性别 1为男 0为女

    @Size(max = 100, message = "签名长度[0-100]", groups = {UpdateCustomFields.class})
    private String sign;       // 签名

    private String face;       // 头像URL

    private String roles;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", sign='" + sign + '\'' +
                ", face='" + face + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }
}
