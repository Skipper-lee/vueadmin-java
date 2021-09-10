package com.markerhub.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.service.mar.MarClientService;
import com.markerhub.service.mar.MarDepartmentService;
import com.markerhub.service.mar.MarOrderService;
import com.markerhub.service.sys.*;
import com.markerhub.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
/**
*
*@author lichong
*@description：
*@date 6:12 PM 03/08/2021
**/
public class BaseController {

	@Autowired
	HttpServletRequest req;

	@Autowired
	RedisUtil redisUtil;

	@Autowired
	public SysUserService sysUserService;

	@Autowired
	SysRoleService sysRoleService;

	@Autowired
	SysMenuService sysMenuService;

	@Autowired
	SysUserRoleService sysUserRoleService;

	@Autowired
	SysRoleMenuService sysRoleMenuService;

	@Autowired
	public MarClientService marClientService;

	@Autowired
	public MarDepartmentService marDepartmentService;
	@Autowired
	public MarOrderService marOrderService;
	/**
	 * 获取页面
	 * @return
	 */
	public Page getPage()  {
		int current = ServletRequestUtils.getIntParameter(req, "current", 1);
		int size = ServletRequestUtils.getIntParameter(req, "size", 10);
		return new Page(current, size);
	}

}
