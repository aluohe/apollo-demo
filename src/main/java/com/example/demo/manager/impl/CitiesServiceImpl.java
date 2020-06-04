package com.example.demo.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.CitiesDao;
import com.example.demo.entity.Cities;
import com.example.demo.manager.CitiesService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author aluohe
 * @since 2020-04-03
 */
@Service
public class CitiesServiceImpl extends ServiceImpl<CitiesDao, Cities> implements CitiesService {

}
