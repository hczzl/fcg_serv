package com.glch.estate.mapper;

import com.glch.estate.entity.Estate;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *  Mapper 接口
 * @author liangwen
 * @since 2020-07-08
 */
@Mapper
public interface EstateMapper extends BaseMapper<Estate> {

    List<Estate> selectByName(String xqmc);
}
