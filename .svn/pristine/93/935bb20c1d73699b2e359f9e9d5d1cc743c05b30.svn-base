package com.glch.system.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.glch.base.caches.ServContexts;
import com.glch.base.util.StringUtil;
import com.glch.base.util.UserAgentUtil;
import com.glch.base.util.UserUtil;
import com.glch.base.util.cipher.CipherUtils;
import com.glch.login.entity.LoginToken;
import com.glch.login.mapper.LoginTokenMapper;
import com.glch.system.user.entity.User;
import com.glch.system.user.entity.UserRole;
import com.glch.system.user.mapper.UserMapper;
import com.glch.system.user.mapper.UserRoleMapper;
import com.glch.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    LoginTokenMapper loginTokenMapper;

    @Override
    public User getUserByLoginName(String loginName) {
        return this.baseMapper.getUserByLoginName(loginName);
    }

    @Override
    public User selectById(String creator) {
        return null;
    }

    @Override
    public void limitUserLogin(User user) {
        Boolean isSuperAdmin = superAdmin(user.getId());
        //只有超级管理员可以多处登录
        if(!isSuperAdmin){
            List<LoginToken> list = loginTokenMapper.selectList(new EntityWrapper<LoginToken>().eq("creator", user.getId()));
            if(StringUtil.isNotEmpty(list)){
                for(LoginToken tokens : list){
                    loginTokenMapper.deleteToken(tokens.getId());
                    ServContexts.removeContexts(tokens.getId());
                }
            }
        }
    }

    @Override
    public User selectByUserId(String userId) {
        return this.baseMapper.selectByUserId(userId);
    }

    @Override
    public boolean checkOldPwd(String id, String oldPwd) {
        User user = this.baseMapper.selectByUserId(id);
        if(CipherUtils.encrypt(oldPwd).equals(user.getPwd())){
            return true;
        }
        return false;
    }

    @Override
    public int changPwd(String id, String newPwd) {
        User user = new User();
        user.setId(id);
        user.setPwd(CipherUtils.encrypt(newPwd));
        return this.baseMapper.updateUser(user);
    }

    @Override
    public int updateUser(User u) {
        return this.baseMapper.updateUser(u);
    }

    public Boolean superAdmin(String userId){
        Boolean superAdmin = false;
        UserRole userRole = userRoleMapper.selectByUserId(userId);
        if(userRole != null){
            String roleId = userRole.getRoleId();
            if(roleId.equals(UserUtil.superAdminRoleId)){
                superAdmin = true;
            }
        }
        return superAdmin;
    }
}
