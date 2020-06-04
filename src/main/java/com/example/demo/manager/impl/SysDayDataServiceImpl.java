package com.example.demo.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.SysDayDataDao;
import com.example.demo.entity.SysDayData;
import com.example.demo.entity.SysHourData;
import com.example.demo.manager.SysDayDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author aluohe
 * @since 2020-03-04
 */
@Slf4j
@Service
public class SysDayDataServiceImpl extends ServiceImpl<SysDayDataDao, SysDayData> implements SysDayDataService {

    @Autowired
    SysDayDataDao sysDayDataDao;


    @Override
    public void addDayData(SysHourData sysHourData) {
        try {
            SysDayData sysDayData = new SysDayData();
            sysDayData.setCompanyId(sysHourData.getCompanyId());
            sysDayData.setTimes(sysHourData.getTimes() / 100);
            sysDayData.setUserId(sysHourData.getUserId());

            SysDayData sysData = sysDayDataDao.selectSysData(sysDayData);
            if (sysData == null) {
                sysData = new SysDayData();
                sysData.setTimes(sysDayData.getTimes())
                        .setUserId(sysHourData.getUserId())
                        .setCompanyId(sysHourData.getCompanyId())
                        .setLoadingNumber(sysHourData.getLoadingNumber())
                        .setLoadingCount(sysHourData.getLoadingCount())
                        .setUnloadingNumber(sysHourData.getUnloadingNumber())
                        .setUnloadingCount(sysHourData.getUnloadingCount())
                        .setAmount(sysHourData.getAmount())
                        .setServiceFee(sysHourData.getServiceFee());
                sysDayDataDao.insert(sysData);
            } else {
                sysData
                        .setLoadingNumber(sysData.getLoadingNumber().add(sysHourData.getLoadingNumber()))
                        .setLoadingCount(sysData.getLoadingCount() + sysHourData.getLoadingCount())
                        .setUnloadingNumber(sysData.getUnloadingNumber().add(sysHourData.getUnloadingNumber()))
                        .setUnloadingCount(sysData.getUnloadingCount() + sysHourData.getUnloadingCount())
                        .setAmount(sysData.getAmount().add(sysHourData.getAmount()))
                        .setServiceFee(sysData.getServiceFee().add(sysHourData.getServiceFee()));
                sysDayDataDao.updateById(sysData);
            }
        } catch (Exception e) {
            log.info("更新日增量失败，时间为：" + String.valueOf(new Date()));
            log.info("---------------------------------更新日数据失败小时增量id为：" + sysHourData.getId());
            throw new RuntimeException(e);
        }
    }
}
