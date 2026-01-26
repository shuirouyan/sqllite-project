package com.sqllite.sqlliteproject.constants;

/**
 * 响应相关常量类
 * 包含API响应字段名和通用路径前缀等常量
 */
public class ResponseConstants {

    /**
     * 响应字段名常量
     * 定义API返回JSON对象中的字段名称
     */
    public static class Field {
        /** 操作是否成功的字段名 */
        public static final String SUCCESS = "success";

        /** 受影响行数的字段名 */
        public static final String AFFECTED_ROWS = "affectedRows";

        /** 数量统计的字段名 */
        public static final String COUNT = "count";
    }

    /**
     * 资源路径常量
     * 定义Spring资源路径前缀
     */
    /** classpath路径前缀，用于加载classpath下的资源 */
    public static final String CLASSPATH_PREFIX = "classpath:";
}
