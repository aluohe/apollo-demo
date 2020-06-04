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
@TableName("sys_vehicle_history")
public class SysVehicleHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 司机开过的车
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 车辆id
     */
    @TableField("vehicle_id")
    private Integer vehicleId;

    /**
     * 状态
     */
    @TableField("state")
    private Integer state;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 实时表的创建时间
     */
    @TableField("create_time")
    private Date createTime;

    @TableField("type")
    private Integer type;


}
