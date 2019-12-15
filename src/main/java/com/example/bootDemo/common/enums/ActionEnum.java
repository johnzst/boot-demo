/*
 * RT MAP, Home of Professional MAP
 * Copyright  Bit Main Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 */
package com.example.bootDemo.common.enums;

import com.example.bootDemo.common.model.response.ResponseException;

import java.util.HashMap;
import java.util.Map;

/**
 * 动作枚举
 */
public enum ActionEnum {
    /**
     * 1上移、上架、开启... 2下移、下架、暂停...
     */
    INITIAL(0, "初始值、未配置"),
    ENABLE(1, "上移、上架、开启、启用、置顶"),
    DISABLED(2, "下移、下架、暂停、禁用、取消置顶");

    private int value;
    private String text;

    private static Map<Integer, ActionEnum> values = new HashMap<>();

    static {
        for (ActionEnum v : ActionEnum.values()) {
            values.put(v.getValue(), v);
        }
    }

    ActionEnum(int value, String text) {
        this.value = value;
        this.text = text;
    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static ActionEnum getEnum(Integer type) {
        ActionEnum operateEnum = values.get(type);
        if (operateEnum == null) {
            throw new ResponseException("查无匹配的操作码(operateAction=" + type + ")");
        }
        return operateEnum;
    }
}
