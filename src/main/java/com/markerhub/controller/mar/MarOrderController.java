package com.markerhub.controller.mar;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.markerhub.common.lang.Result;
import com.markerhub.controller.BaseController;
import com.markerhub.entity.mar.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.markerhub.entity.sys.SysUser;
import com.markerhub.service.mar.MarBrandsService;
import com.markerhub.service.mar.MarNamesService;
import com.markerhub.service.mar.MarOrderChildrenService;
import com.markerhub.service.mar.MarTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
*
*@author lichong
*@description：
*@date 6:28 PM 08/08/2021
**/
@RestController
@RequestMapping("/mar/order")
public class MarOrderController extends BaseController {
    @Autowired
    private MarBrandsService marBrandsService;
    @Autowired
    private MarTypesService marTypesService;
    @Autowired
    private MarNamesService marNamesService;
    @Autowired
    private MarOrderChildrenService marOrderChildrenService;


    @PostMapping("/list")
    public Result list(@RequestBody MarOrder marOrder,String stateDate,String endDate){
        Long id = null;
        if(StrUtil.isNotBlank(marOrder.getName())){
            id = marClientService.getByName(marOrder.getName()).getId();
        }
        Page<MarOrder> pageData = marOrderService.page(getPage(), new QueryWrapper<MarOrder>()
                .ge(!stateDate.equals("NaN-NaN-NaN 0NaN:0NaN:0NaN"),"created",stateDate)
                .le(!endDate.equals("NaN-NaN-NaN 0NaN:0NaN:0NaN"),"created",endDate)
                .like( StrUtil.isNotBlank(marOrder.getNo()),"no", marOrder.getNo())
                .like( id != null,"cid", id)
                .eq(  (marOrder.getStatu() != null)  ,"statu", marOrder.getStatu()
                )

        );
        pageData.getRecords().forEach(u -> {
            u.setName(marClientService.getById(u.getCid()).getName());
            u.setOperationName(sysUserService.getById(u.getOperationId()).getUsername());
            if(u.getCheckId() != null){
                u.setCheckName(sysUserService.getById(u.getCheckId()).getUsername());
            }
        });

        return Result.succ(pageData);
    }

    @GetMapping("/clientName")
    public Result clientName(){
        List<MarClient> list = marClientService.list();
        return Result.succ(list);
    }

    @GetMapping("/brands")
    public Result getBrands(){
        List<MarBrands> list = marBrandsService.list();
        return Result.succ(list);
    }

    @GetMapping("/types/{id}")
    public Result getTypes(@PathVariable("id") Long id){
        List<MarTypes> list = marTypesService.list(new QueryWrapper<MarTypes>().eq(id != null,"bid",id));
        return Result.succ(list);
    }

    @GetMapping("/names/{id}")
    public Result getNames(@PathVariable("id") Long id){
        List<MarNames> list = marNamesService.list(new QueryWrapper<MarNames>().eq(id != null,"tid",id));
        return Result.succ(list);
    }
    
    @PostMapping("/newOrder")
    @Transactional(rollbackFor = Exception.class)
    public Result submitNewOrder(@RequestBody MarOrder newOrder ,Principal principal) {
        SysUser byUsername = sysUserService.getByUsername(principal.getName());
        newOrder.setOperationId(byUsername.getId());
        boolean save = marOrderService.save(newOrder);
        MarOrder no = marOrderService.getOne(new QueryWrapper<MarOrder>().eq(save, "no", newOrder.getNo()));
        newOrder.getOrderChildData().forEach(u->{
            u.setParentId(no.getId());
            u.setCreated(LocalDateTime.now());
            marOrderChildrenService.save(u);
        });
        return Result.succ(save);
    }

    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        boolean b = marOrderService.removeById(id);
        return Result.succ(b);
    }

    @GetMapping("/lookOrder/{id}")
    public Result lookOrder(@PathVariable("id") Long id){
        MarOrder marOrder = marOrderService.getById(id);
        System.out.println(marOrder.getId());
        marOrder.setName(marClientService.getById(marOrder.getCid()).getName());
        //获取子订单信息
        marOrder.setOrderChildData(marOrderChildrenService.list(new QueryWrapper<MarOrderChildren>().eq("parent_id", marOrder.getId())));
        System.out.println(marOrder);
        return Result.succ(marOrder);
    }



}
