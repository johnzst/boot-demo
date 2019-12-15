package com.example.bootDemo.common.consts;

import java.util.HashMap;
import java.util.Map;

public class CodeMsg {

    public static final int SUCCESS = 200;//请求成功
    public static final int ALERT_MSG = 209;//提醒消息
    public static final int ERROR = 500;//服务器异常
    public static final int INVALID_VERSION = 60100;//接口版本号不存在
    public static final int NOT_FOUND = 60101;//请求的资源找不到
    public static final int INVALID_SIGN = 60102;//非法的签名
    public static final int INVALID_TOKEN = 60103;//非法的token
    public static final int NULL_PARAM = 60104;//参数不能为空
    public static final int INVALID_PARAM = 60105;//非法的参数
    public static final int EXITING = 60106;//重复数据
    public static final int NO_MATCHING = 60107;//暂无匹配数据
    public static final int INVALID_FILE = 60108;//非法的文件类型
    public static final int INVALID_USER = 60109;//无效的用户
    public static final int OPERATE_FAIL = 60110;//操作失败
    public static final int OPERATE_ILLEGAL = 60111;//不能操作
    public static final int FILE_SIZE_ERROR = 60112;//文件过大
    public static final int NULL_FILE = 60113;//文件不存在
    public static final int UNKNOWN_HOST = 60114;//非法的网络地址
    public static final int UNKNOWN_SERVICE = 60115;//服务调用失败
    public static final int NETWORK_NOT_READABLE = 60116;//服务调用失败

    //小程序工具箱相关
    public final static int ILLEGAL_ID = 60504;
    public final static int MSG_CODE_BOTTOM = 60505;
    public final static int MSG_CODE_TOP = 60506;
    public final static int MSG_CODE_INVALID = 60507;

    protected static final Map<Integer, String> MSG = new HashMap<Integer, String>();

    static {
        // 系统常用
        MSG.put(SUCCESS, "请求成功.");
        MSG.put(ERROR, "服务器异常.");

        MSG.put(INVALID_VERSION, "接口版本号不存在.");
        MSG.put(NOT_FOUND, "请求资源未找到.");
        MSG.put(INVALID_SIGN, "签名非法.");
        MSG.put(INVALID_TOKEN, "token失效,请重新登录.");
        MSG.put(NULL_PARAM, "请求参数不能为空.");
        MSG.put(INVALID_PARAM, "非法的请求参数.");
        MSG.put(EXITING, "重复的数据请求.");

        MSG.put(NO_MATCHING, "暂无匹配数据.");
        MSG.put(INVALID_FILE, "非法的文件类型.");
        MSG.put(INVALID_USER, "非法的用户.");
        MSG.put(OPERATE_FAIL, "操作失败.");
        MSG.put(OPERATE_ILLEGAL, "非法操作.");

        MSG.put(FILE_SIZE_ERROR, "上传文件过大.");
        MSG.put(NULL_FILE, "文件不存在.");

        // 系统通用
        MSG.put(UNKNOWN_HOST, "系统网络请求失败，当前网络不稳定");
        MSG.put(UNKNOWN_SERVICE, "调用下游服务失败");

        MSG.put(ILLEGAL_ID, "非法的ID");
        MSG.put(MSG_CODE_BOTTOM, "已经到达底部");
        MSG.put(MSG_CODE_TOP, "已经到达顶部");
        MSG.put(MSG_CODE_INVALID, "无效操作");
    }

    public static String getMsg(int errCode) {
        return MSG.get(errCode);
    }
}
