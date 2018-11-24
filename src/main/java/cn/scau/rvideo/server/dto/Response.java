package cn.scau.rvideo.server.dto;

public class Response {
    private Object data;
    private String msg;
    private int code;

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Response setCode(int code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }


    /*    public static class ResponseBuilder {
        Response response = new Response();
        public ResponseBuilder setData(Object data){
            response.setData(data);
            return this;
        }
    }*/
}
