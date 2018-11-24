package cn.scau.rvideo.server.entity;

import java.sql.Timestamp;

public class Video {

    private Integer id;
    private Integer userId;
    private String name;        //视频名称
    private Integer likes;      //点赞
    private Integer views;      //播放量
    private String tag;         //标签
    private String face;    //缩略图名
    private String file;        //文件位置
    private String introduction;    //视频介绍
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
                ", date=" + date +
                '}';
    }
}
