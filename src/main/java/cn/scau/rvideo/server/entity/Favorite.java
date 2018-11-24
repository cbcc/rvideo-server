package cn.scau.rvideo.server.entity;

public class Favorite {

    private Integer userId;
    private Integer videoId;

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

    @Override
    public String toString() {
        return "Favorite{" +
                "userId=" + userId +
                ", videoId=" + videoId +
                '}';
    }
}
