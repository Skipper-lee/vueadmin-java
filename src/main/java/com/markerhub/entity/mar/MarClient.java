package com.markerhub.entity.mar;

import com.baomidou.mybatisplus.annotation.TableField;
import com.markerhub.entity.BaseEntity;
import com.markerhub.entity.sys.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
*
*@author lichong
*@description：
*@date 11:01 PM 02/08/2021
**/
@Data
@EqualsAndHashCode(callSuper = true)
public class MarClient extends BaseEntity {
    private String name;
    private String gender;
    private String company;
    private String area;
    private String telephone;
    @TableField(exist = false)
    private String createName;

    private Long createUid;
    private LocalDateTime allocationDate;
    /**
    * syser ： 负责客户的员工
    * */
    @TableField(exist = false)
    private SysUser sysUser;
    private String address;
}
