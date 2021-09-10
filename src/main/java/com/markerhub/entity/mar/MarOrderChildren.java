package com.markerhub.entity.mar;

import com.markerhub.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
*
*@author lichong
*@description：
*@date 7:39 PM 12/08/2021
**/
@Data
@EqualsAndHashCode(callSuper = true)
public class MarOrderChildren extends BaseEntity {
    private Long parentId;
    private Long brandId;
    private Long typeId;
    private Long nameId;
    private Integer count;
    private Double price;
    private Double money;


}
