package com.example.demo.compent.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author aluohe
 * @className GvRelationTypeEnum
 * @projectName kamo-cloud
 * @date 2020/5/15 15:14
 * @description 组织-车关系类型
 * @modified_by
 * @version: 1.0.0
 */
@Getter
@AllArgsConstructor
public enum GvRelationTypeEnum {

    RELATION_GROUP(0, "组织","主司机"),
    RELATION_GOODS(1, "为组织拉过货","副司机"),
    ;
    private Integer code;

    private String groupRelation;
    private String driverRelation;

}