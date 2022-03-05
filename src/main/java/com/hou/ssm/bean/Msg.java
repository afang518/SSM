package com.hou.ssm.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shadow
 * @Description
 * @create 2022-03-03 18:49
 */
public class Msg {
    //状态码(用于告诉前端增删改操作是否成功)  100-成功  200-失败 300-用户名格式不正确
    private int code;
    //提示信息
    private String message;

    //传给前端的数据
    private Map<String, Object> extend = new HashMap<>();

    //将键值对数据添加到 调用者的 extend中,从而实现链式添加
    public Msg add(String key, Object value) {
        this.getExtend().put(key, value);
        return this;
    }

    //返回一个操作成功的Msg对象
    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMessage("成功");
        return result;
    }
    //返回一个操作失败的Msg对象
    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMessage("失败");
        return result;
    }

    public Msg() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
