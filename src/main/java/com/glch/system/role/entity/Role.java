package com.glch.system.role.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.glch.base.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色基本信息
 * </p>
 *
 * @author liangwen
 * @since 2020-07-09
 */
@Data
@TableName("t_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableField("id")
    private String id;
    /**
     * 角色名称
     */
    @TableField("name")
    private String name;
    /**
     * 角色编号
     */
    @TableField("role_no")
    private String roleNo;
    /**
     * 描述
     */
    @TableField("description")
    private String description;
    /**
     * 状态
     */
    @TableField("state")
    private Integer state;

}
