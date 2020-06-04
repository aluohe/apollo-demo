package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author aluohe
 * @since 2020-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_gv_relation")
public class SysGvRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 车id
     */
    @TableField("vehicle_id")
    private Integer vehicleId;

    /**
     * 组织id
     */
    @TableField("group_id")
    private Integer groupId;

    /**
     * 状态 0 正常 1 删除
     */
    @TableField("state")
    private Integer state;

    /**
     * 关联类型 0 组织 1 为组织拉过货物的车
     */
    @TableField("type")
    private Integer type;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    @TableField("`create`")
    private Date create;

    @TableField("`modify`")
    private Date modify;


}
