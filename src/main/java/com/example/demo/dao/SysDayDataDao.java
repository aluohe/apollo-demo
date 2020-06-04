package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SysDayData;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author aluohe
 * @since 2020-03-04
 */
@Mapper
public interface SysDayDataDao extends BaseMapper<SysDayData> {

    SysDayData selectSysData(SysDayData sysDayData);


}
