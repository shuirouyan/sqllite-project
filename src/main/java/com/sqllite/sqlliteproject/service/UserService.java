package com.sqllite.sqlliteproject.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * 用户服务类
 * 提供用户相关的业务逻辑处理
 */
@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 测试数据库连接
     *
     * @return 数据库连接测试结果（1表示成功）
     */
    public Integer testConnection() {
        String sql = "SELECT 1";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        logger.info("Database connection test result: " + result);
        return result;
    }

    /**
     * 获取用户表结构
     *
     * @return users表的DDL语句，如果表不存在返回错误信息
     */
    public String getUserTableSchema() {
        String sql = "SELECT sql FROM sqlite_master WHERE type='table' AND name='users'";
        try {
            String schema = jdbcTemplate.queryForObject(sql, String.class);
            return schema != null ? schema : "User table not found";
        } catch (Exception e) {
            logger.error("Error querying user table schema", e);
            return "Error querying user table schema: " + e.getMessage();
        }
    }

    /**
     * 插入测试用户数据
     * 向users表插入一条测试记录
     *
     * @return 受影响的行数（1表示成功）
     */
    public int insertUser() {
        String sql = "INSERT INTO users (username, age) VALUES (?, ?)";
        String name = "Test User";
        Integer age = 26;
        int affectedRows = jdbcTemplate.update(sql, name, age);
        logger.info("Inserted user, affected rows: {}", affectedRows);
        return affectedRows;
    }



}
