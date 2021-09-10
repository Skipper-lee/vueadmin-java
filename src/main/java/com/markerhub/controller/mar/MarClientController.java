package com.markerhub.controller.mar;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.common.lang.Result;
import com.markerhub.controller.BaseController;
import com.markerhub.entity.mar.Department;
import com.markerhub.entity.mar.MarClient;
import com.markerhub.entity.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 *@author lichong
 *@description：
 *@date 9:29 PM 03/08/2021
 **/
@RestController
@RequestMapping("/mar/client")
public class MarClientController extends BaseController {
    @Autowired
    PasswordEncoder passwordEncoder;
    /**
     * @param marClient
     * @return
     */
    @PostMapping("/list")
    public Result list(@RequestBody MarClient marClient){

        Page<MarClient> pageData = marClientService.page(getPage(), new QueryWrapper<MarClient>()
                .like(StrUtil.isNotBlank(marClient.getArea()), "area", marClient.getArea())
                .like(StrUtil.isNotBlank(marClient.getCompany()),"company",marClient.getCompany())
                .like(StrUtil.isNotBlank(marClient.getName()),"name",marClient.getName())

        );
        pageData.getRecords().forEach(u -> {
            //获取分配的员工
            u.setCreateName(sysUserService.getCreateNameByUid(u.getId()));
            //通过uid 获取 blong_id
            Long bid = sysUserService.getBidByuid(u.getId());
            //通过blong_id获取User
            if (bid != null){
               u.setSysUser(sysUserService.getOne(new QueryWrapper<SysUser>().eq("id", bid)));
            }
        });

        return Result.succ(pageData);
    }
    @PostMapping("/save")
    public Result save(@Validated @RequestBody MarClient newClient, Principal principal){
        SysUser sysUser = sysUserService.getByUsername(principal.getName());
        newClient.setCreateUid(sysUser.getId());
        newClient.setCreated(LocalDateTime.now());

        marClientService.save(newClient);
        return Result.succ(newClient);
    }
    @PostMapping("/update")
    public Result update(@Validated @RequestBody MarClient newClient){

        newClient.setUpdated(LocalDateTime.now());
        marClientService.updateById(newClient);
        return Result.succ(newClient);
    }

    @GetMapping("/Info/{id}")
    public Result getInfo(@PathVariable("id")Long id){
        MarClient client = marClientService.getById(id);
        return Result.succ(client);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id")Long id){
        boolean b = marClientService.removeById(id);
        return Result.succ(b);
    }

    @GetMapping("/department")
    public Result getDepartment(){
        List<Department> list = marDepartmentService.list();
        return Result.succ(list);
    }
    @GetMapping("/department/emp/{id}")
    public Result getDepartment(@PathVariable("id")long id){
        List<SysUser> sysUsers = sysUserService.list(new QueryWrapper<SysUser>().eq("department", id));
        return Result.succ(sysUsers);
    }

    @GetMapping("/allocation")
    public Result allocation(Long uid,Long blong_uid){
        marClientService.update(new UpdateWrapper<MarClient>().eq("id",uid).set("blong_uid", blong_uid).set("allocation_date",LocalDateTime.now()));
        return Result.succ(true);
    }
}
