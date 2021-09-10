package com.markerhub.service.mar.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.markerhub.entity.mar.MarClient;
import com.markerhub.entity.sys.SysUser;
import com.markerhub.mapper.mar.MarClientMapper;
import com.markerhub.mapper.sys.SysUserMapper;
import com.markerhub.service.mar.MarClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*
*@author lichong
*@description：
*@date 11:26 PM 02/08/2021
**/
@Service
public class MarClientServiceImpl  extends ServiceImpl<MarClientMapper, MarClient> implements MarClientService {
    @Autowired
    private MarClientMapper marClientMapper;
    @Override
    public List<MarClient> getClientByPage(int current) {
        List<MarClient> listClientByPage = marClientMapper.getListClientByPage(current);

        return listClientByPage;
    }


    @Override
    public MarClient getByName(String username) {
        return this.getOne(new QueryWrapper<MarClient>().eq("name",username));
    }


}
