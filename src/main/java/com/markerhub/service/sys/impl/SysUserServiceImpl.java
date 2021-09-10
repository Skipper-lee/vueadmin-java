package com.markerhub.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.markerhub.entity.sys.SysMenu;
import com.markerhub.entity.sys.SysRole;
import com.markerhub.entity.sys.SysUser;
import com.markerhub.mapper.sys.SysUserMapper;
import com.markerhub.service.sys.SysMenuService;
import com.markerhub.service.sys.SysRoleService;
import com.markerhub.service.sys.SysUserService;
import com.markerhub.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 我的公众号：MarkerHub
 * @since 2021-04-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	SysRoleService sysRoleService;

	@Autowired
	SysUserMapper sysUserMapper;

	@Autowired
	SysMenuService sysMenuService;

	@Autowired
	RedisUtil redisUtil;

	@Override
	public SysUser getByUsername(String username) {
		return getOne(new QueryWrapper<SysUser>().eq("username", username));
	}

	@Override
	public String getUserAuthorityInfo(Long userId) {

		SysUser sysUser = sysUserMapper.selectById(userId);

		//  ROLE_admin,ROLE_normal,sys:user:list,....
		String authority = "";

		if (redisUtil.hasKey("GrantedAuthority:" + sysUser.getUsername())) {
			authority = (String) redisUtil.get("GrantedAuthority:" + sysUser.getUsername());

		} else {
			// 获取角色编码
			List<SysRole> roles = sysRoleService.list(new QueryWrapper<SysRole>()
					.inSql("id", "select role_id from sys_user_role where user_id = " + userId));

			if (roles.size() > 0) {
				String roleCodes = roles.stream().map(r -> "ROLE_" + r.getCode()).collect(Collectors.joining(","));
				authority = roleCodes.concat(",");
			}

			// 获取菜单操作编码
			List<Long> menuIds = sysUserMapper.getNavMenuIds(userId);
			if (menuIds.size() > 0) {

				List<SysMenu> menus = sysMenuService.listByIds(menuIds);
				String menuPerms = menus.stream().map(m -> m.getPerms()).collect(Collectors.joining(","));

				authority = authority.concat(menuPerms);
			}

			redisUtil.set("GrantedAuthority:" + sysUser.getUsername(), authority, 60 * 60);
		}

		return authority;
	}

	@Override
	public void clearUserAuthorityInfo(String username) {
		redisUtil.del("GrantedAuthority:" + username);
	}

	@Override
	public void clearUserAuthorityInfoByRoleId(Long roleId) {

		List<SysUser> sysUsers = this.list(new QueryWrapper<SysUser>()
				.inSql("id", "select user_id from sys_user_role where role_id = " + roleId));

		sysUsers.forEach(u -> {
			this.clearUserAuthorityInfo(u.getUsername());
		});

	}

	@Override
	public void clearUserAuthorityInfoByMenuId(Long menuId) {
		List<SysUser> sysUsers = sysUserMapper.listByMenuId(menuId);

		sysUsers.forEach(u -> {
			this.clearUserAuthorityInfo(u.getUsername());
		});
	}


	@Override
	public SysUser listUserByUserId(Long id) {
		List<SysUser> sysUsers = this.list(new QueryWrapper<SysUser>().inSql("id", "select uid from t_client_user where cid =" + id));

		if(sysUsers.size() == 0){
			return null;
		}
		return sysUsers.get(0);
	}
	/**
	 *
	 * 获取创建客户者的名称
	* */
	@Override
	public String getCreateNameByUid(Long id) {
		 String name = sysUserMapper.getCreateNameByUid(id);
		return name;
	}

    @Override
    public Long getBidByuid(Long id) {
        Long bid = sysUserMapper.getBidByuid(id);
        return bid;
    }

}
