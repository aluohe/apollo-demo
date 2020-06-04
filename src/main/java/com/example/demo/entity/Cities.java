package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author aluohe
 * @since 2020-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cities")
public class Cities implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("province_code")
    private String provinceCode;


}
