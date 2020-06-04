package com.example.demo.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.compent.enums.BaseStateEnum;
import com.example.demo.compent.enums.GvRelationTypeEnum;
import com.example.demo.dao.SysVehicleRealtimeDao;
import com.example.demo.entity.SysGvRecords;
import com.example.demo.entity.SysGvRelation;
import com.example.demo.entity.SysVehicleGroup;
import com.example.demo.entity.SysVehicleRealtime;
import com.example.demo.manager.SysGvRecordsService;
import com.example.demo.manager.SysGvRelationService;
import com.example.demo.manager.SysVehicleGroupService;
import com.example.demo.manager.SysVehicleRealtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
public class SysVehicleRealtimeServiceImpl extends ServiceImpl<SysVehicleRealtimeDao, SysVehicleRealtime> implements SysVehicleRealtimeService {

}
