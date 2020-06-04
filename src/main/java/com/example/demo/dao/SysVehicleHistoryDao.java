package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Base;
import com.example.demo.entity.SysVehicleHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author aluohe
 * @since 2020-05-22
 */
@Mapper
public interface SysVehicleHistoryDao extends BaseMapper<SysVehicleHistory> {


    List<Base> selectRepeat();


}
