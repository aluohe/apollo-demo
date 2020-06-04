package com.example.demo.manager;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.SysDayData;
import com.example.demo.entity.SysHourData;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author aluohe
 * @since 2020-03-04
 */
public interface SysDayDataService extends IService<SysDayData> {

    /**
     * 今日增量
     *
     * @param
     * @return
     */
    void addDayData(SysHourData sysHourData) ;
}
