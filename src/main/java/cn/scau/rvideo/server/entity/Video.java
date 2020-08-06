package cn.scau.rvideo.server.entity;

import cn.scau.rvideo.server.controller.validation.group.Submit;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

public class Video {

    private Integer id;
    private Integer userId;

    @NotNull(message = "标题不能为空", groups = {Submit.class})
    @Size(min = 1, max = 80, message = "标题长度[1-80]", groups = {Submit.class})
    private String name;// 视频名称

    private Integer likes;// 点赞
    private Integer views;// 播放量

    @NotNull(message = "标签不能为空", groups = {Submit.class})
    private String tag;// 标签

    @NotNull(message = "视频封面不能为空", groups = {Submit.class})
    private String face;// 缩略图名

    @NotNull(message = "视频不能为空", groups = {Submit.class})
    private String file;// 文件位置

    @NotNull(message = "简介不能为空", groups = {Submit.class})
    @Size(min = 1, max = 250, message = "简介长度[1-250]", groups = {Submit.class})
    private String introduction;// 视频介绍
    private Integer verify;// 审核
    private Timestamp date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getVerify() {
        return verify;
    }

    public void setVerify(Integer verify) {
        this.verify = verify;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", likes=" + likes +
                ", views=" + views +
                ", tag='" + tag + '\'' +
                ", face='" + face + '\'' +
                ", file='" + file + '\'' +
                ", introduction='" + introduction + '\'' +
                ", verify=" + verify +
                ", date=" + date +
                '}';
    }
}
