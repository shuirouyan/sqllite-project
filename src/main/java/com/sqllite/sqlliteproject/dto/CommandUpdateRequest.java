package com.sqllite.sqlliteproject.dto;

/**
 * 命令更新请求DTO
 */
public class CommandUpdateRequest {

    /**
     * 命令文本
     */
    private String text;

    /**
     * 数字值
     */
    private Integer num;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
