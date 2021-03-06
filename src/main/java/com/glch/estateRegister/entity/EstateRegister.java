package com.glch.estateRegister.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.glch.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 小区房屋登记信息表
 * </p>
 *
 * @author liangwen
 * @since 2020-07-08
 */
@Data
@TableName("t_estate_register")
public class EstateRegister extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 租用人姓名
     */
    @TableField("name")
    private String name;

    @TableField("gender")
    private Integer gender;

    @TableField("birthday")
    private String birthday;

    @TableField("addrress")
    private String addrress;

    /**
     * 租用人身份证号
     */
    @TableField("cardId")
    private String cardId;
    /**
     * 租用人联系方式
     */
    @TableField("tel")
    private String tel;
    /**
     * 是否业主 0否 1是
     */
    @TableField("owner")
    private Integer owner;
    /**
     * 业主姓名
     */
    @TableField("owner_name")
    private String ownerName;
    /**
     * 业主身份证号
     */
    @TableField("owner_cardId")
    private String ownerCardid;
    /**
     * 业主联系方式
     */
    @TableField("owner_tel")
    private String ownerTel;
    /**
     * 实际居住人工作（单位、职务）
     */
    @TableField("work")
    private String work;
    /**
     * 小区省
     */
    @TableField("xqsheng")
    private String xqsheng;
    /**
     * 小区市
     */
    @TableField("xqshi")
    private String xqshi;
    /**
     * 小区县
     */
    @TableField("xqxian")
    private String xqxian;
    /**
     * 小区名称
     */
    @TableField("xqmc")
    private String xqmc;
    /**
     * 小区详址
     */
    @TableField("xqxz")
    private String xqxz;
    /**
     * 楼栋
     */
    @TableField("building")
    private String building;
    /**
     * 单元号
     */
    @TableField("unit_number")
    private String unitNumber;
    /**
     * 房间号
     */
    @TableField("room_number")
    private String roomNumber;
    /**
     * 户型
     */
    @TableField("house_type")
    private String houseType;
    /**
     * 房屋用途
     */
    @TableField("house_use")
    private String houseUse;
    /**
     * 居住类型，自住、租住
     */
    @TableField("live_type")
    private String liveType;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    /**
     * 状态 0正常 -1删除
     */
    @TableField("state")
    private Integer state;
    /**
     * 状态 0正常 -1删除
     */
    @TableField("image_path")
    private String imagePath;

    @TableField("estate_id")
    private String estateId;

    /**
     * 图片base64码
     */
    private String base64;

    /**
     * 创建人姓名
     */
    private String creatorName;


}
