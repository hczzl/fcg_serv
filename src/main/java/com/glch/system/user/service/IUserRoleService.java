package com.glch.system.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.glch.system.user.entity.UserRole;

import java.util.List;
import java.util.Map;

public interface IUserRoleService extends IService<UserRole> {

    List<Map<String,Object>> queryByUserId(String id);
}
