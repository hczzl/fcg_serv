package com.glch.system.user.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.glch.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@TableName("t_user")
@ApiModel
@Data
public class User extends BaseEntity{
    @TableField(value = "id")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @TableField(value = "last_logout_time")
    @JsonFormat(timezone = "GMT + 8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastLogoutTime;

    @TableField(value = "last_login_time")
    @JsonFormat(timezone = "GMT + 8", pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastLoginTime;

    @ApiModelProperty("姓名")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty("组织id")
    @TableField(value = "dept_id")
    private String deptId;

    @TableField(value = "position")
    private String position;

    @ApiModelProperty("登录名")
    @TableField(value = "login_name")
    private String loginName;

    @TableField(value = "device_id")
    private String deviceId;

    @ApiModelProperty("密码")
    @TableField(value = "pwd")
    private String pwd;

    @ApiModelProperty("联系电话")
    @TableField(value = "telephone")
    private String telephone;

    @ApiModelProperty("状态：10-启用 20-禁用 -1-删除")
    @TableField(value = "state")
    private Integer state;

    @ApiModelProperty("角色id")
    private String roleId;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("token")
    private String token;
}
