package com.glch.estate.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.glch.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author liangwen
 * @since 2020-07-08
 */
@ApiModel
@Data
public class EstateVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(hidden = true)
    private String id;
    /**
     * 城区
     */
    @ApiModelProperty("城区")
    private String cityproper;
    /**
     * 城区编号
     */
    @ApiModelProperty("城区编号")
    private String cityCode;
    /**
     * 小区名称
     */
    @ApiModelProperty("小区名称")
    private String estateName;
    /**
     * 小区经度
     */
    @ApiModelProperty("小区经度")
    private Float addrLng;
    /**
     * 小区纬度
     */
    @ApiModelProperty("小区纬度")
    private Float addrLat;

}
