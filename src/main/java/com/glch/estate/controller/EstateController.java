package com.glch.estate.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.glch.base.common.JsonResponse;
import com.glch.base.controller.BaseController;
import com.glch.estate.entity.Estate;
import com.glch.estate.entity.vo.EstateVo;
import com.glch.estate.service.IEstateService;
import com.glch.login.entity.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liangwen
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/estate/")
@Api(value = "estateController", tags = "小区相关接口")
public class EstateController extends BaseController {

    @Autowired
    IEstateService estateService;

    @RequestMapping(value = "/listEstate", method = {RequestMethod.POST})
    @ApiOperation(value = "根据名称查询小区")
    public JsonResponse listEstate() {
        List<Estate> estateList =  estateService.selectList(new EntityWrapper<Estate>());
        return JsonResponse.ok(estateList);
    }

    @RequestMapping(value = "/selectByName", method = {RequestMethod.POST})
    @ApiOperation(value = "根据名称查询小区")
    public JsonResponse selectByName(@ApiParam("{\"xqmc\":\"小区名称\"}") @RequestBody JSONObject params) {
        if(params == null){
            return JsonResponse.error("传入参数不能为空！");
        }
        if(params.getString("xqmc") == null){
            return JsonResponse.error("传入小区名称不能为空！");
        }
        List<Estate> estateList = estateService.selectByName(params.getString("xqmc"));
        return JsonResponse.ok(estateList);
    }

    @RequestMapping(value = "/addEstate", method = {RequestMethod.POST})
    @ApiOperation(value = "新增小区")
    public JsonResponse addEstate(@RequestBody EstateVo estateVo) {
        try {
            estateService.addEstate(estateVo);
            return JsonResponse.ok("新增成功");
        }catch (Exception e){
            e.printStackTrace();
            return JsonResponse.ok("新增失败");
        }
    }
}

