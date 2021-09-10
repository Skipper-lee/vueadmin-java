package com.markerhub.service.mar;

import com.baomidou.mybatisplus.extension.service.IService;
import com.markerhub.entity.mar.MarClient;
import com.markerhub.entity.sys.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MarClientService extends IService<MarClient> {
    List<MarClient> getClientByPage(int current);


    MarClient getByName(String username);


}
