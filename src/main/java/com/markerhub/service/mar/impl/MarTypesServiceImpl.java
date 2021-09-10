package com.markerhub.service.mar.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.entity.mar.MarTypes;
import com.markerhub.mapper.mar.MarTypesMapper;
import com.markerhub.service.mar.MarBrandsService;
import com.markerhub.service.mar.MarTypesService;
import org.springframework.stereotype.Service;

/**
*
*@author lichong
*@description：
*@date 9:48 PM 11/08/2021
**/
@Service
public class MarTypesServiceImpl extends ServiceImpl<MarTypesMapper, MarTypes> implements MarTypesService {
}
