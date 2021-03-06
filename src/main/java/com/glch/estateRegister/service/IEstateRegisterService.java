package com.glch.estateRegister.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.glch.estateRegister.entity.EstateRegister;
import com.baomidou.mybatisplus.service.IService;
import com.glch.estateRegister.entity.vo.EstateRegisterVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小区房屋登记信息表 服务类
 * </p>
 *
 * @author liangwen
 * @since 2020-07-08
 */
public interface IEstateRegisterService extends IService<EstateRegister> {

    void saveEstateRegister(EstateRegisterVo estateRegisterVo) throws Exception;

    PageInfo<EstateRegister> listEstateRegister(JSONObject params) throws Exception;

    void updateEstateRegister(EstateRegisterVo estateRegisterVo) throws  Exception;
}
