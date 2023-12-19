package cn.scau.rvideo.server.dto;

public class Response<T> {
    private T data;
    private int status;
    private String message;

    public Response() {
    }

    public Response(T data, int status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public static <T> Response<T> success(String message) {
        return new Response<>(null, Status.SUCCESS, message);
    }

    public static <T> Response<T> success(T data, String message) {
        return new Response<>(data, Status.SUCCESS, message);
    }

    public static <T> Response<T> fail(String message) {
        return new Response<>(null, Status.FAIL, message);
    }

    public static <T> Response<T> fail(T data, String message) {
        return new Response<>(data, Status.FAIL, message);
    }

    public Response<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Response<T> setStatus(int status) {
        this.status = status;
        return this;
    }

    public Response<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
