package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author aluohe
 * @since 2020-03-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_hour_data")
public class SysHourData implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 时间
     */
    @TableField("times")
    private Integer times;

    /**
     * 货主id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 公司id
     */
    @TableField("company_id")
    private Integer companyId;

    /**
     * 新增用户数
     */
    @TableField("user_count")
    private Integer userCount;

    /**
     * 装货吨位
     */
    @TableField("loading_number")
    private BigDecimal loadingNumber;

    /**
     * 装货车数
     */
    @TableField("loading_count")
    private Integer loadingCount;

    /**
     * 卸货吨位
     */
    @TableField("unloading_number")
    private BigDecimal unloadingNumber;

    /**
     * 卸货车数
     */
    @TableField("unloading_count")
    private Integer unloadingCount;

    /**
     * 运费
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 服务费
     */
    @TableField("service_fee")
    private BigDecimal serviceFee;


}
