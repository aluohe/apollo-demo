package com.example.demo.compent.enums;

import lombok.Getter;

/**
 * @author aluohe
 * @className BaseStateEnum
 * @projectName kamo-cloud
 * @date 2019/11/8 15:06
 * @description 基础状态枚举类
 * @modified_by
 * @version: 1.0.0
 */
@Getter
public enum BaseStateEnum {

    FORBIDDEN(0, "禁用"),
    ENABLE(1, "启用"),


    ;


    private Integer code;
    private String name;

    BaseStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }



}
