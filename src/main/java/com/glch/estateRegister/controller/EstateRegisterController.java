package com.glch.estateRegister.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageInfo;
import com.glch.base.common.JsonResponse;
import com.glch.base.controller.BaseController;
import com.glch.base.util.DateUtils;
import com.glch.base.util.StringUtil;
import com.glch.estate.entity.Estate;
import com.glch.estateRegister.entity.EstateRegister;
import com.glch.estateRegister.entity.vo.EstateRegisterUpdateVo;
import com.glch.estateRegister.entity.vo.EstateRegisterVo;
import com.glch.estateRegister.entity.vo.RegisVo;
import com.glch.estateRegister.mapper.EstateRegisterMapper;
import com.glch.estateRegister.service.DownService;
import com.glch.estateRegister.service.IEstateRegisterService;
import com.glch.log.enums.LogLevelEnum;
import com.glch.log.enums.OptTypeEnum;
import com.glch.log.service.ILogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.joda.time.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 小区房屋登记信息表 前端控制器
 * </p>
 *
 * @author liangwen
 * @since 2020-07-08
 */
@RestController
@RequestMapping("/estateRegister/")
@Api(value = "estateController", tags = "小区登记信息相关接口")
public class EstateRegisterController extends BaseController {

    @Autowired
    IEstateRegisterService estateRegisterService;
    @Autowired
    ILogService logService;
    @Autowired
    private EstateRegisterMapper estateRegisterMapper;
    @Autowired
    private DownService downService;


    @RequestMapping(value = "/saveEstateRegister", method = {RequestMethod.POST})
    @ApiOperation(value = "录入小区登记信息")
    public JsonResponse saveEstateRegister(HttpServletRequest request, @RequestBody EstateRegisterVo estateRegisterVo) {
        try {
            estateRegisterService.saveEstateRegister(estateRegisterVo);
            logService.success(request, OptTypeEnum.UPDATE, "录入小区登记信息", LogLevelEnum.INFO, null, estateRegisterVo.toString(), true);
            return JsonResponse.ok("信息录入成功");
        } catch (Exception e) {
            e.printStackTrace();
            logService.success(request, OptTypeEnum.UPDATE, "录入小区登记信息", LogLevelEnum.ERROR, null, estateRegisterVo.toString(), true);
            return JsonResponse.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/listEstateRegister", method = {RequestMethod.POST})
    @ApiOperation(value = "查询小区登记信息", response = EstateRegisterUpdateVo.class)
    public JsonResponse listEstateRegister(@ApiParam("{\"keyWord\":\"关键字\", \"estateId\":\"小区ID\", \"beginTime\":\"开始时间\", " +
            "\"endTime\":\"结束时间\", \"pageIndex\":页码, \"pageSize\":页面大小}")
                                           @RequestBody JSONObject params, HttpServletRequest request) {
        try {
            if (params.getInteger("pageIndex") == null || params.getInteger("pageSize") == null) {
                return JsonResponse.error("分页参数错误");
            }
            PageInfo<EstateRegister> pageInfo = estateRegisterService.listEstateRegister(params);
            logService.success(request, OptTypeEnum.QUERY, "查询小区登记信息", LogLevelEnum.INFO, null, params.toString(), true);
            return JsonResponse.ok("查询成功", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logService.success(request, OptTypeEnum.QUERY, "查询小区登记信息", LogLevelEnum.ERROR, null, params.toString(), true);
            return JsonResponse.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/updateEstateRegister", method = {RequestMethod.POST})
    @ApiOperation(value = "修改小区登记信息")
    public JsonResponse updateEstateRegister(@RequestBody EstateRegisterVo estateRegisterVo, HttpServletRequest request) {
        try {
            estateRegisterService.updateEstateRegister(estateRegisterVo);
            logService.success(request, OptTypeEnum.QUERY, "查询小区登记信息", LogLevelEnum.INFO, null, estateRegisterVo.toString(), true);
            return JsonResponse.ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            logService.success(request, OptTypeEnum.QUERY, "查询小区登记信息", LogLevelEnum.ERROR, null, estateRegisterVo.toString(), true);
            return JsonResponse.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteEstateRegister", method = {RequestMethod.POST})
    @ApiOperation(value = "删除小区登记信息")
    public JsonResponse deleteEstateRegister(@ApiParam("{\"ids\":\"登记信息id数组\"}") @RequestBody JSONObject params, HttpServletRequest request) {
        try {
            JSONArray ids = params.getJSONArray("ids");
            if (ids == null || ids.size() <= 0) {
                return JsonResponse.error("入参不能为空");
            }
            for (int i = 0; i < ids.size(); i++) {
                String id = ids.getString(i);
                estateRegisterService.deleteById(id);
            }
            logService.success(request, OptTypeEnum.UPDATE, "删除小区登记信息", LogLevelEnum.INFO, null, params.toString(), true);
            return JsonResponse.ok("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logService.success(request, OptTypeEnum.UPDATE, "删除小区登记信息", LogLevelEnum.ERROR, null, params.toString(), true);
            return JsonResponse.error(e.getMessage());
        }
    }

    @ApiOperation("导出小区登记信息")
    @RequestMapping(value = {"/exportMysqlData"}, method = {RequestMethod.POST})
    public JsonResponse exportMysqlData(@RequestBody RegisVo register, @ApiIgnore HttpServletResponse response, @ApiIgnore HttpSession session) {
        try {
            //限制导出的条数为10000条
            register.setPageSize(10000);
            List<Map<String, Object>> resultList = estateRegisterMapper.selectList(register);
            if (resultList == null || resultList.size() < 1) {
                return JsonResponse.error("导出数据为空");
            }
            String filePath = this.downService.downloadTotalComprehensive(resultList, DateUtils.getFormat(new Date(), "yyyyMMddHHmmss"), register);
            File zipFile = new File(filePath);
            //根据路径下载数据和图片
            if (!StringUtil.isEmpty(filePath)) {
                this.downService.downloadZip(zipFile, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.logger.error("导出异常!" + e.getMessage());
            return JsonResponse.error("导出异常!");
        }
        return null;
    }


}