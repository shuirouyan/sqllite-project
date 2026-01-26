package com.sqllite.sqlliteproject.constants;

/**
 * 控制器相关常量类
 * 包含API路径和控制器返回消息等常量
 */
public class ControllerConstants {

    /**
     * 用户控制器路径常量
     * 定义用户相关API的URL路径
     */
    public static final class UserPath {
        /** 用户控制器基础路径 */
        public static final String BASE = "/api/users";

        /** 测试数据库连接接口路径 */
        public static final String TEST = "/test";

        /** SQLite连接测试接口路径 */
        public static final String SQLLITE = "/sqllite";

        /** 获取用户表结构接口路径 */
        public static final String SCHEMA = "/schema";

        /** 获取classpath路径接口路径 */
        public static final String CLASSPATH = "/classpath";

        /** 插入测试用户数据接口路径 */
        public static final String INSERT = "/insert";
    }

    /**
     * 命令控制器路径常量
     * 定义命令相关API的URL路径
     */
    public static final class CommandPath {
        /** 命令控制器基础路径 */
        public static final String BASE = "/api/commands";

        /** ID路径变量，用于指定命令ID */
        public static final String ID_PATH = "/{id}";

        /** 搜索命令接口路径 */
        public static final String SEARCH = "/search";

        /** 删除所有命令接口路径 */
        public static final String ALL = "/all";

        /** 统计命令数量接口路径 */
        public static final String COUNT = "/count";
    }

    /**
     * 控制器返回消息常量
     * 定义控制器返回给客户端的消息文本
     */
    public static final class Message {
        /** 数据库连接测试成功消息，格式化占位符用于显示结果 */
        public static final String DB_CONNECTION_TEST = "Database connection test executed. Check console for results.num:%d";
    }
}
