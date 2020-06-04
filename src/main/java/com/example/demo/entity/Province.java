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
@TableName("province")
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("state")
    private Integer state = 1;

    @TableField("order_code")
    private Integer orderCode = 0;


}
