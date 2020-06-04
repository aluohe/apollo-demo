package com.example.demo.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.compent.enums.BaseStateEnum;
import com.example.demo.compent.enums.GvRelationTypeEnum;
import com.example.demo.dao.SysVehicleHistoryDao;
import com.example.demo.entity.Base;
import com.example.demo.entity.SysVehicleHistory;
import com.example.demo.entity.SysVehicleRealtime;
import com.example.demo.manager.SysVehicleHistoryService;
import com.example.demo.manager.SysVehicleRealtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author aluohe
 * @since 2020-05-22
 */
@Service
public class SysVehicleHistoryServiceImpl extends ServiceImpl<SysVehicleHistoryDao, SysVehicleHistory> implements SysVehicleHistoryService {

    @Autowired
    SysVehicleHistoryDao vehicleHistoryDao;
    @Autowired
    SysVehicleRealtimeService sysVehicleRealtimeService;

    @Override
    public void handleHistory() {

        new Thread(
                () -> {

                    //处理脏数据
                    handleRepeatData();

                    handleRealTime();

                }
        ).start();


    }


    public void handleRealTime() {

        int count = sysVehicleRealtimeService.count();

        int page = getPage(count, 1000);

        for (int i = 1; i <= page; i++) {

            Page<SysVehicleRealtime> realtimePage = new Page<>(i,1000);
            IPage<SysVehicleRealtime> iPage = sysVehicleRealtimeService.page(realtimePage, null);

            List<SysVehicleRealtime> records = iPage.getRecords();
            if (!records.isEmpty()) {

                Set<Integer> userIds = records.stream().map(re -> re.getUserId()).collect(Collectors.toSet());
                Set<Integer> vehicleIds = records.stream().map(re -> re.getVehicleId()).collect(Collectors.toSet());


                Set<String> collect = records.stream().map(ve -> ve.getUserId() + "," + ve.getVehicleId()).collect(Collectors.toSet());

                LambdaQueryWrapper<SysVehicleHistory> wrapper = new LambdaQueryWrapper<>();
                wrapper.in(SysVehicleHistory::getUserId, userIds).in(SysVehicleHistory::getVehicleId, vehicleIds);
                List<SysVehicleHistory> list = list(wrapper);

                if (!list.isEmpty()) {

                    Set<String> strings = list.stream().map(ve -> ve.getUserId() + "," + ve.getVehicleId()).collect(Collectors.toSet());
                    collect.removeAll(strings);

                    for (SysVehicleHistory history : list) {
                        history.setType(GvRelationTypeEnum.RELATION_GROUP.getCode());
                    }

                    boolean b = updateBatchById(list);
                    if (!b) {
                        try {
                            throw new Exception("sql error ");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (!collect.isEmpty()) {

                        List<SysVehicleHistory> histories = new ArrayList<>(collect.size());
                        for (String s : collect) {
                            String[] split = s.split(",");

                            histories.add(new SysVehicleHistory()
                                    .setUserId(Integer.valueOf(split[0])).setVehicleId(Integer.valueOf(split[1]))
                                    .setCreateTime(new Date())
                                    .setState(BaseStateEnum.ENABLE.getCode())
                                    .setType(GvRelationTypeEnum.RELATION_GROUP.getCode())
                            );
                        }
                        ((ArrayList<SysVehicleHistory>) histories).trimToSize();
                        if (!histories.isEmpty()) {

                            boolean batch = saveBatch(histories);
                            if (!batch) {
                                try {
                                    throw new Exception("sql error ");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }


                }


            }


        }

    }


    private int getPage(int count, Integer limit) {
        int yushu = count % limit;
        int pages = count / limit;
        if (pages == 0 && yushu > 0) {
            pages = 1;
        } else if (pages > 0 && yushu > 0) {
            pages = pages + 1;
        }
        return pages;
    }

    private void handleRepeatData() {
        List<Base> bases = vehicleHistoryDao.selectRepeat();

        if (!bases.isEmpty()) {

            Set<Integer> userIds = bases.stream().map(base -> base.getUserId()).collect(Collectors.toSet());
            Set<Integer> vehicleIds = bases.stream().map(base -> base.getVehicleId()).collect(Collectors.toSet());

            LambdaQueryWrapper<SysVehicleHistory> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(SysVehicleHistory::getUserId, userIds).in(SysVehicleHistory::getVehicleId, vehicleIds);
            List<SysVehicleHistory> list = list(wrapper);

            if (!list.isEmpty()) {
                Map<String, SysVehicleHistory> historyMap = list.stream().collect(Collectors.toMap(ve -> ve.getUserId() + "*" + ve.getVehicleId(), Function.identity(), (k1, k2) -> k2));
                list.removeAll(historyMap.values());
            }

            Set<Integer> collect = list.stream().map(SysVehicleHistory::getId).collect(Collectors.toSet());

            LambdaUpdateWrapper<SysVehicleHistory> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.in(SysVehicleHistory::getId, collect);
            boolean remove = remove(updateWrapper);
            if (!remove) {
                try {
                    throw new Exception("sql error ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
