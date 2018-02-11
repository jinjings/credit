package com.pay.card.enums;

public enum RepaymentTypeEnum {
    MARK_REPAYMENT(0, "标记还款"), MARK_PAIDOFF(1, "标记已还清"), PRACTICAL_REPAYMENT(2, "实际还款"), BILL_REPAYMENT(3, "账单还款");

    public static String getMsg(Integer code) {
        if (code != null) {
            for (RepaymentTypeEnum enums : RepaymentTypeEnum.values()) {
                if (enums.getCode().equals(code)) {
                    return enums.getMsg();
                }
            }
        }

        return "";
    }

    /**
     * 消息
     */
    private String msg;

    /**
     * 编码
     */
    private Integer code;

    private RepaymentTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}