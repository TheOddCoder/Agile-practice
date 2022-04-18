package com.nju.bean;

import java.util.Objects;

public class RespBean {
    private String status;
    private String msg;

    public RespBean() {
    }

    public RespBean(String status, String msg) {

        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RespBean{" +
                "status='" + status + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RespBean respBean = (RespBean) o;
        return Objects.equals(status, respBean.status) && Objects.equals(msg, respBean.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, msg);
    }
}
