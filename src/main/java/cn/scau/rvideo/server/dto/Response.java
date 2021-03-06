package cn.scau.rvideo.server.dto;

public class Response {
    private Object data;
    private String message;
    private int status;

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Response setStatus(int status) {
        this.status = status;
        return this;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
