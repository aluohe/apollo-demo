package com.example.demo.manager;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.SysGvRelation;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aluohe
 * @since 2020-05-22
 */
public interface SysGvRelationService extends IService<SysGvRelation> {


    void addOriginVehicle();
}
