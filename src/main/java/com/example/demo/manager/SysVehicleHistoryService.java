package com.example.demo.manager;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.SysVehicleHistory;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aluohe
 * @since 2020-05-22
 */
public interface SysVehicleHistoryService extends IService<SysVehicleHistory> {

    /**
     * 处理历史数据脏数据
     */
    void handleHistory();

}
