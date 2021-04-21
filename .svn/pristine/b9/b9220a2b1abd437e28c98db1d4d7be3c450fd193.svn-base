package com.glch.estate.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.glch.base.entity.BaseEntity;
import lombok.Data;

/**
 * @author liangwen
 * @since 2020-07-08
 */
@TableName("t_estate")
@Data
public class Estate extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 城区
     */
    @TableField("cityproper")
    private String cityproper;
    /**
     * 城区编号
     */
    @TableField("city_code")
    private String cityCode;
    /**
     * 小区名称
     */
    @TableField("estate_name")
    private String estateName;
    /**
     * 小区经度
     */
    @TableField("addr_lng")
    private Float addrLng;
    /**
     * 小区纬度
     */
    @TableField("addr_lat")
    private Float addrLat;

}
