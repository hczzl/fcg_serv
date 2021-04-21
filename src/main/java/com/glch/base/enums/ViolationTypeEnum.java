package com.glch.base.enums;

/**
 * 违法类型
 */
public enum ViolationTypeEnum {

    MAINBELT(1,"主驾未系安全带"),
    SECONDBELT(2,"副驾未系安全带"),
    CALL(3,"主驾打电话"),
    COMPACTIONLINE(4,"压线"),
    ILLEGALPARKING(5,"违法停车"),
    RETROGRADE(6,"机动车逆行"),
    RUNREDLIGHT(7,"闯红灯"),
    NOTGIVEWAY(8,"未礼让行人"),
    NOTGUIDE(9,"未按导向车道行驶"),
    TURNAROUND(10,"违法掉头");


    private int code;

    private String desc;

    ViolationTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 得到枚举类型
     * @param code
     * @return
     */
    public static ViolationTypeEnum getViolationTypeEnum(int code) {
        ViolationTypeEnum[] types = values();
        for (ViolationTypeEnum type : types) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public static String getDesc(int code) {
        ViolationTypeEnum[] types = values();
        for (ViolationTypeEnum type : types) {
            if (type.getCode() == code) {
                return type.getDesc();
            }
        }
        return "";
    }
}
