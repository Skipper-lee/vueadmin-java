package com.markerhub.service.sys;

import com.markerhub.common.dto.SysMenuDto;
import com.markerhub.entity.sys.SysMenu;
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
public interface SysMenuService extends IService<SysMenu> {

	List<SysMenuDto> getCurrentUserNav();

	List<SysMenu> tree();

}
