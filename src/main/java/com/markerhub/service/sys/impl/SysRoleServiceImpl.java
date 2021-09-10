package com.markerhub.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.markerhub.entity.sys.SysRole;
import com.markerhub.mapper.sys.SysRoleMapper;
import com.markerhub.service.sys.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 我的公众号：MarkerHub
 * @since 2021-04-05
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Override
	public List<SysRole> listRolesByUserId(Long userId) {

		List<SysRole> sysRoles = this.list(new QueryWrapper<SysRole>()
				.inSql("id", "select role_id from sys_user_role where user_id = " + userId));

		return sysRoles;
	}


}
