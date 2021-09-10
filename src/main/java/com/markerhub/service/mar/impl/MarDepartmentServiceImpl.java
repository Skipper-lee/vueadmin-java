package com.markerhub.service.mar.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.entity.mar.Department;
import com.markerhub.mapper.mar.MarDepartmentMapper;
import com.markerhub.service.mar.MarDepartmentService;
import org.springframework.stereotype.Service;
/**
*
*@author lichong
*@description：
*@date 6:28 PM 07/08/2021
**/
@Service
public class MarDepartmentServiceImpl extends ServiceImpl<MarDepartmentMapper, Department> implements MarDepartmentService{
}
