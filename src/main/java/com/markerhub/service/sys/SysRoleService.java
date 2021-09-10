package com.markerhub.service.sys;

import com.markerhub.entity.sys.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*
*@author lichong
*@description：
*@date 11:24 PM 02/08/2021
**/
@Service
public interface SysRoleService extends IService<SysRole> {

	List<SysRole> listRolesByUserId(Long userId);

}
