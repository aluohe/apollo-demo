package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author aluohe
 * @className Base
 * @projectName demo
 * @date 2020/5/22 18:00
 * @description
 * @modified_by
 * @version:
 */
@Data
@Accessors(chain = true)
public class Base {

    private Integer userId;

    private Integer vehicleId;
}