package com.glch.estateRegister.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("租用人信息")
public class PersonVo {

    @ApiModelProperty("头像base64")
    private String base64;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别 0-未知性别 1-男 2-女 9-未说明")
    private Integer gender;

    @ApiModelProperty("出生日期")
    private String birthday;

    @ApiModelProperty("身份证号")
    private String cardId;

    @ApiModelProperty("户籍地址")
    private String addrress;

    @ApiModelProperty("联系电话")
    private String tel;

    @ApiModelProperty("居住类型 1-自住、2-租住")
    private String liveType;

    @ApiModelProperty("是否业主 0-否 1-是")
    private Integer owner;

}
