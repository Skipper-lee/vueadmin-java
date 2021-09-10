package com.markerhub.entity.mar;

import com.baomidou.mybatisplus.annotation.TableField;
import com.markerhub.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
*
*@author lichong
*@description：
*@date 6:24 PM 07/08/2021
**/
@Data
public class Department  {
    private Long id;
    private String name;
    @TableField(exist = false)
    private String mangerName;
    private Long manger;
}
