package com.zhengbing.learn.boot.util.pdf;

/**
 * @Description:  合同类型
 * @author: zhengbing_vendor
 * @date: 2019/7/23 9:20
 */
public enum ContractTypeEnum {


    /**
     * 劳动合同
     */
    Labor_contract("劳动合同"),

    /**
     * 实习协议
     */
    Internship_agreement("实习协议"),

    /**
     * 兼职协议
     */
    PartTime_agreement("兼职协议"),

    /**
     * 保密协议
     */
    Confidentiality_Agreement("保密协议"),

    /**
     * 保密协议(实习生版)
     */
    Confidentiality_Agreement_Trainee("保密协议(实习生版)"),

    /**
     * 岗位纪律同意书
     */
    Post_Disciplinary_Consent("岗位纪律同意书"),

    /**
     * 竞业限制协议
     */
    Competition_Restriction_Agreement("竞业限制协议"),
    /**
     * 竞业限制协议企业名单  list of enerprise
     */
    Competition_Restriction_Agreement_LOE("竞业限制协议企业名单"),

    /**
     * 健康承诺书
     */
    Health_Commitment("健康承诺书");


    private String name;

    public String getName(){
        return this.name;
    }

    private ContractTypeEnum(String name){
        this.name = name;
    }
}
