package com.markerhub.service.mar.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.entity.mar.MarOrder;
import com.markerhub.mapper.mar.MarOrderMapper;
import com.markerhub.service.mar.MarOrderService;
import org.springframework.stereotype.Service;

/**
*
*@author lichong
*@description：
*@date 6:42 PM 08/08/2021
**/
@Service
public class MarOrderServiceImpl extends ServiceImpl<MarOrderMapper, MarOrder> implements MarOrderService {
}
