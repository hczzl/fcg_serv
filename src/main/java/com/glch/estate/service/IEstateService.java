package com.glch.estate.service;

import com.glch.estate.entity.Estate;
import com.baomidou.mybatisplus.service.IService;
import com.glch.estate.entity.vo.EstateVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liangwen
 * @since 2020-07-08
 */
public interface IEstateService extends IService<Estate> {

    List<Estate> selectByName(String xqmc);

    int addEstate(EstateVo estateVo) throws  Exception;
}