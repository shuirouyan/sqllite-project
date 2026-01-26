package com.sqllite.sqlliteproject.constants;

/**
 * 用户相关常量类
 * 包含用户模块的SQL语句、日志消息和测试数据等常量
 */
public class UserConstants {

    /**
     * SQL语句常量
     * 定义用户相关的数据库操作SQL语句
     */
    public static class SQL {
        /** 测试数据库连接的SQL语句 */
        public static final String SELECT_ONE = "SELECT 1";

        /** 查询users表结构的SQL语句 */
        public static final String SELECT_USER_SCHEMA = "SELECT sql FROM sqlite_master WHERE type='table' AND name='users'";

        /** 插入用户数据的SQL语句 */
        public static final String INSERT_USER = "INSERT INTO users (username, age) VALUES (?, ?)";
    }

    /**
     * 日志消息常量
     * 定义用户操作相关的日志输出模板
     */
    public static class LogMessage {
        /** 数据库连接测试结果日志 */
        public static final String DB_CONNECTION_TEST_RESULT = "Database connection test result: {}";

        /** 查询用户表结构错误日志 */
        public static final String USER_TABLE_SCHEMA_ERROR = "Error querying user table schema";

        /** 用户表未找到消息 */
        public static final String USER_TABLE_SCHEMA_NOT_FOUND = "User table not found";

        /** 查询用户表结构错误前缀 */
        public static final String USER_TABLE_SCHEMA_ERROR_PREFIX = "Error querying user table schema: ";

        /** 用户插入成功日志 */
        public static final String USER_INSERTED = "Inserted user, affected rows: {}";
    }

    /**
     * 用户数据常量
     * 定义测试用到的用户数据
     */
    public static class UserData {
        /** 测试用户名 */
        public static final String TEST_USERNAME = "Test User";

        /** 测试用户年龄 */
        public static final Integer TEST_AGE = 26;
    }
}
