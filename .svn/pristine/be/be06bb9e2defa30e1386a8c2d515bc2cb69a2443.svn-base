package com.glch.system.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.glch.system.user.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole>{

    List<Map<String,Object>> queryByUserId(@Param("userId") String id);

    UserRole selectByUserId(String id);
}
