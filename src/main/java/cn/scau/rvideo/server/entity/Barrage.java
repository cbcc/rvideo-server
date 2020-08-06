package cn.scau.rvideo.server.entity;

import java.sql.Timestamp;

public class Barrage {

    private Integer id;
    private Integer userId;
    private Integer videoId;
    private String content;// 内容
    private Timestamp date;// 时间

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

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Barrage{" +
                "id=" + id +
                ", userId=" + userId +
                ", videoId=" + videoId +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
