package com.sqllite.sqlliteproject.entity;

/**
 * Command实体类
 * 对应数据库中的command表
 */
public class Command {

    /**
     * 命令ID
     */
    private Integer id;

    /**
     * 命令文本
     */
    private String text;

    /**
     * 数字值
     */
    private Integer num;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", num=" + num +
                '}';
    }
}
