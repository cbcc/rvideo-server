package cn.scau.rvideo.server.auth.controller;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class LoginParam {

    @NotNull(message = "电子邮件地址不能为空")
    @Email(message = "无效的电子邮件地址")
    private String email;

    @NotNull(message = "密码不能为空")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
