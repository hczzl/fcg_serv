package com.glch.system.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.glch.base.caches.Context;
import com.glch.base.caches.ServContexts;
import com.glch.base.common.JsonResponse;
import com.glch.base.controller.BaseController;
import com.glch.base.util.StringUtil;
import com.glch.log.enums.LogLevelEnum;
import com.glch.log.enums.OptTypeEnum;
import com.glch.log.service.impl.LogService;
import com.glch.system.user.entity.User;
import com.glch.system.user.entity.UserRole;
import com.glch.system.user.service.IUserRoleService;
import com.glch.system.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/")
@Api(value = "userController", tags = "用户相关接口")
public class UserController extends BaseController{

    @Autowired
    IUserService userService;
    @Autowired
    IUserRoleService userRoleService;
    @Autowired
    LogService logService;

    @RequestMapping(value = "/selectCurrentInfo", method = {RequestMethod.POST})
    @ApiOperation("个人中心")
    public JsonResponse selectCurrentInfo() {
        Context context = ServContexts.getCurContext();
        if(context != null){
            User user = ServContexts.getCurUser();
            if(user == null){
                return JsonResponse.tokenError();
            }
            user.setPwd("");
            List<Map<String, Object>> list = userRoleService.queryByUserId(user.getId());
            for(Map<String, Object> map : list){
                String roleId = (String)map.get("roleId");
                String roleName = (String)map.get("rolename");
                user.setRoleName(roleName);
                user.setRoleId(roleId);
            }
            return JsonResponse.ok(user);
        }else{
            return JsonResponse.tokenError();
        }
    }

    @RequestMapping(value = "/modifyUserInfo", method = {RequestMethod.POST})
    @ApiOperation("修改个人信息")
    public JsonResponse modifyUserInfo(HttpServletRequest request, @RequestBody User user) {
        try{
            if(!StringUtil.isEmpty(user.getRoleId())){
                UserRole userRole = new UserRole();
                userRole.setRoleId(user.getRoleId());
                userRoleService.update(userRole, new EntityWrapper<UserRole>().eq("userId", user.getId()));
            }
            boolean result = userService.updateById(user);
            if(result){
                logService.success(request, OptTypeEnum.UPDATE, "修改个人信息", LogLevelEnum.INFO, null, user.toString(), true);
                return JsonResponse.ok("修改成功！");
            }
            return JsonResponse.error("修改失败！");
        }catch (Exception e){
            e.printStackTrace();
            logService.failed(request, OptTypeEnum.UPDATE, "修改个人信息", LogLevelEnum.ERROR, null, user.toString(), true);
            return JsonResponse.error("修改失败！");
        }
    }

    @RequestMapping(value = "/changPwd", method = {RequestMethod.POST})
    @ApiOperation("修改密码")
    public JsonResponse changPwd(HttpServletRequest request,
                                 @ApiParam("{\"id\":\"用户ID\",\"oldPwd\":\"旧密码\",\"newPwd\":\"新密码\"}")
                                 @RequestBody JSONObject params) {

        if(null == params){
            return JsonResponse.error("接收参数失败！");
        }
        if(StringUtil.isEmpty(params.getString("id"))){
            return JsonResponse.error("用户id不能为空！");
        }
        if(StringUtil.isEmpty(params.getString("oldPwd"))){
            return JsonResponse.error("旧密码不能为空！");
        }
        if(StringUtil.isEmpty(params.getString("newPwd"))){
            return JsonResponse.error("新密码不能为空！");
        }
        try{
            boolean flag = userService.checkOldPwd(params.getString("id"), params.getString("oldPwd"));
            if(!flag){
                return JsonResponse.error("旧密码不正确！");
            }
            int result = userService.changPwd(params.getString("id"), params.getString("newPwd"));
            if(result <= 0){
                logService.failed(request, OptTypeEnum.UPDATE, "修改密码", LogLevelEnum.ERROR, null, params.toString(), true);
                return JsonResponse.error("修改失败！");
            }

            logService.success(request, OptTypeEnum.UPDATE, "修改密码", LogLevelEnum.INFO, null, params.toString(), true);
            return JsonResponse.ok("修改成功！");
        }catch (Exception e){
            e.printStackTrace();
            logService.failed(request, OptTypeEnum.UPDATE, "修改密码", LogLevelEnum.ERROR, null, params.toString(), true);
            return JsonResponse.error("修改失败！");
        }
    }
}
