package com.foyatech.oaps.struts.Action.common;
/**
 * 共用的回傳物件
 * @author equalhsiao
 * 2018/03/20
 * @param <T>
 */
public class CommonResponse<T> {

    private String rc = "0000";

    private String rm = "SUCCESS";

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getRm() {
        return rm;
    }

    public void setRm(String rm) {
        this.rm = rm;
    }

}
