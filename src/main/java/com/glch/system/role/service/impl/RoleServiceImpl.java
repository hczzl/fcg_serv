package com.glch.system.role.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.glch.system.role.entity.Role;
import com.glch.system.role.mapper.RoleMapper;
import com.glch.system.role.service.IRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色基本信息 服务实现类
 * </p>
 *
 * @author liangwen
 * @since 2020-07-09
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
