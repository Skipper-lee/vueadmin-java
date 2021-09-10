package com.markerhub.entity.mar;

import com.baomidou.mybatisplus.annotation.TableField;
import com.markerhub.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
*
*@author lichong
*@description：
*@date 11:40 AM 08/08/2021
**/
@Data
@EqualsAndHashCode(callSuper = true)
public class MarOrder extends BaseEntity {
    private String no;
    private Long cid;
    private Double money;
    @TableField(exist = false)
    private String name;
    private String telephone;
    private Long operationId;
    @TableField(exist = false)
    private String operationName;
    private Long checkId;
    @TableField(exist = false)
    private String checkName;
    private LocalDateTime checkDate;
    @TableField(exist = false)
    private List<MarOrderChildren> orderChildData;

}
