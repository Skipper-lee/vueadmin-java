package com.markerhub.mapper.mar;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.markerhub.entity.mar.MarClient;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MarClientMapper extends BaseMapper<MarClient> {
    List<MarClient> getListClientByPage(int current);
}

