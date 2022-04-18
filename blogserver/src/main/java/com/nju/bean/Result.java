package com.nju.bean;

public class Result {
    private int code;   //200是正常   非200表示异常
    private String msg;
    private Object data;
    public static Result success(Object data){
        return success(200,"操作成功",data);
    }
    public static Result success(String msg){
        return success(200,msg,null);
    }
    public static Result success(int code, String msg, Object data){
        Result r = new Result();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }
    public static Result fail(String msg){
        return fail(400, msg, null);
    }
    public static Result fail(String msg, Object data){
        return fail(400, msg, data);
    }
    public static Result fail(int code, String msg, Object data){
        Result r = new Result();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }
    public int getCode() {return code;}
    public void setCode(int code) {this.code = code;}
    public String getMsg() {return msg;}
    public void setMsg(String msg) {this.msg = msg;}
    public Object getData() {return data;}
    public void setData(Object data) {this.data = data;}

}
