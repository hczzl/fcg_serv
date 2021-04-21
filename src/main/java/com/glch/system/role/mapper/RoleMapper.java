package com.glch.system.role.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.glch.system.role.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色基本信息 Mapper 接口
 * </p>
 *
 * @author liangwen
 * @since 2020-07-09
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
