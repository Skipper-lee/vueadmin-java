package com.markerhub.service.mar.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.entity.mar.MarNames;
import com.markerhub.mapper.mar.MarNamesMapper;
import com.markerhub.service.mar.MarNamesService;
import org.springframework.stereotype.Service;
/**
*
*@author lichong
*@description：
*@date 9:57 PM 11/08/2021
**/
@Service
public class MarNamesServiceImpl extends ServiceImpl<MarNamesMapper, MarNames> implements MarNamesService {
}
