package com.glch.system.user.service;

import com.baomidou.mybatisplus.service.IService;
import com.glch.system.user.entity.User;

public interface IUserService extends IService<User>{

    User getUserByLoginName(String loginName);

    User selectById(String creator);

    void limitUserLogin(User user);

    User selectByUserId(String creator);

    boolean checkOldPwd(String id, String oldPwd);

    int changPwd(String id, String newPwd);

    int updateUser(User u);
}
