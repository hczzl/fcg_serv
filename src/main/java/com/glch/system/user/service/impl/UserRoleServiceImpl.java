package com.glch.system.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.glch.system.user.entity.UserRole;
import com.glch.system.user.mapper.UserRoleMapper;
import com.glch.system.user.service.IUserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public List<Map<String, Object>> queryByUserId(String id) {
        return this.baseMapper.queryByUserId(id);
    }
}
