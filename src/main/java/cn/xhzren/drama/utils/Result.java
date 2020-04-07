package cn.xhzren.drama.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Result {

    public static String ok(Object data) {
        return res(StatusCodeEnum.SUCCESS, data);
    }

    public static String ok(String[] keys, Object... data) {
        Map<String, Object> res = new HashMap<>();
        for (int i=0;i<keys.length;i++) {
            res.put(keys[i], data[i]);
        }
        return res(StatusCodeEnum.SUCCESS, res);
    }
    public static String ok(Object data, String message) {
        return res(StatusCodeEnum.SUCCESS, data, message);
    }
    public static String error(Object data, String message) {
        return res(StatusCodeEnum.BUSINESS_ERROR, data, message);
    }

    public static String error(Object data) {
        return res(StatusCodeEnum.BUSINESS_ERROR, data);
    }
    public static String error(StatusCodeEnum code) {
        return res(code, null);
    }

    public static String res(StatusCodeEnum status) {
        return res(status, null, status.getMsgCN());
    }
    public static String res(StatusCodeEnum status,Object data) {
        return res(status, data, status.getMsgCN());
    }
    public static String res(StatusCodeEnum status,Object data, String message) {
        Map<String, Object> res = new HashMap<>();
        res.put("code", status.getCode());
        res.put("msg", message);
        res.put("data", data);
        return JSONObject.toJSONString(res);
    }

}
