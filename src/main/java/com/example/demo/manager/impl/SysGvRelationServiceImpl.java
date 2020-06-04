package com.example.demo.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.compent.enums.BaseStateEnum;
import com.example.demo.compent.enums.GvRelationTypeEnum;
import com.example.demo.dao.SysGvRelationDao;
import com.example.demo.entity.SysGvRecords;
import com.example.demo.entity.SysGvRelation;
import com.example.demo.entity.SysVehicleGroup;
import com.example.demo.manager.SysGvRecordsService;
import com.example.demo.manager.SysGvRelationService;
import com.example.demo.manager.SysVehicleGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aluohe
 * @since 2020-05-22
 */
@Service
public class SysGvRelationServiceImpl extends ServiceImpl<SysGvRelationDao, SysGvRelation> implements SysGvRelationService {



    @Autowired
    SysVehicleGroupService groupService;

    @Autowired
    SysGvRecordsService recordsService;


    /**
     * 将 组织自有车、给组织拉过货的车添加到新表中
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addOriginVehicle() {

        new Thread(() -> {

            //添加组织自有车
            handleOrigin();
            //添加组织拉货车
            handleOwner();
        }).start();


    }

    private void handleOrigin() {
        int count = groupService.count();
        int page = getPage(count, 1000);

        for (int i = 1; i <= page; i++) {
            Page<SysVehicleGroup> Ipage = new Page<>(i, 1000);

            IPage<SysVehicleGroup> iPage = groupService.page(Ipage, null);
            List<SysVehicleGroup> records = iPage.getRecords();
            if (!records.isEmpty()) {

                Set<Integer> groupIds = records.stream().map(ve -> ve.getGroupId()).collect(Collectors.toSet());
                Set<Integer> vehicleIds = records.stream().map(ve -> ve.getVehicleId()).collect(Collectors.toSet());

                Set<String> collect = records.stream().map(ve -> ve.getGroupId() + "," + ve.getVehicleId()).collect(Collectors.toSet());

                LambdaQueryWrapper<SysGvRelation> wrapper = new LambdaQueryWrapper<>();
                wrapper.in(SysGvRelation::getGroupId, groupIds).in(SysGvRelation::getVehicleId, vehicleIds);
                List<SysGvRelation> list = list(wrapper);

                if (!list.isEmpty()) {
                    Set<String> strings = list.stream().map(ve -> ve.getGroupId() + "," + ve.getVehicleId()).collect(Collectors.toSet());
                    collect.removeAll(strings);
                }

                ArrayList<SysGvRelation> relations = new ArrayList<>(records.size());
                for (String s : collect) {
                    String[] split = s.split(",");
                    Date date = new Date();
                    relations.add(new SysGvRelation()
                            .setCreate(date)
                            .setModify(date)
                            .setType(GvRelationTypeEnum.RELATION_GROUP.getCode())
                            .setState(BaseStateEnum.ENABLE.getCode())
                            .setGroupId(Integer.valueOf(split[0]))
                            .setVehicleId(Integer.valueOf(split[1])));
                }
                boolean saveBatch = saveBatch(relations);
                if (!saveBatch) {
                    try {
                        throw new Exception("sql error");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private void handleOwner() {
        int count = recordsService.count();
        int page = getPage(count, 1000);

        for (int i = 1; i <= page; i++) {
            Page<SysGvRecords> Ipage = new Page<>(i, 1000);

            IPage<SysGvRecords> iPage = recordsService.page(Ipage, null);
            List<SysGvRecords> records = iPage.getRecords();
            if (!records.isEmpty()) {
                saveBatchRelation(records);
            }
        }
    }

    private void saveBatchRelation(List<SysGvRecords> records) {
        Set<Integer> groupIds = records.stream().map(ve -> ve.getGroupId()).collect(Collectors.toSet());
        Set<Integer> vehicleIds = records.stream().map(ve -> ve.getVehicleId()).collect(Collectors.toSet());

        Set<String> collect = records.stream().map(ve -> ve.getGroupId() + "," + ve.getVehicleId()).collect(Collectors.toSet());

        LambdaQueryWrapper<SysGvRelation> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysGvRelation::getGroupId, groupIds).in(SysGvRelation::getVehicleId, vehicleIds);
        List<SysGvRelation> list = list(wrapper);

        if (!list.isEmpty()) {
            Set<String> strings = list.stream().map(ve -> ve.getGroupId() + "," + ve.getVehicleId()).collect(Collectors.toSet());
            collect.removeAll(strings);
        }

        ArrayList<SysGvRelation> relations = new ArrayList<>(records.size());
        for (String s : collect) {
            String[] split = s.split(",");
            Date date = new Date();
            relations.add(new SysGvRelation()
                    .setCreate(date)
                    .setModify(date)
                    .setType(GvRelationTypeEnum.RELATION_GOODS.getCode())
                    .setState(BaseStateEnum.ENABLE.getCode())
                    .setGroupId(Integer.valueOf(split[0]))
                    .setVehicleId(Integer.valueOf(split[1])));
        }
        boolean saveBatch = saveBatch(relations);
        if (!saveBatch) {
            try {
                throw new Exception("sql error");
            } catch (Exception e) {
                e.printStackTrace();
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
}
