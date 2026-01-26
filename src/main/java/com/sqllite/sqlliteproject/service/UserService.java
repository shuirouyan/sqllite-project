package com.sqllite.sqlliteproject.service;

import com.sqllite.sqlliteproject.constants.UserConstants;
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
        Integer result = jdbcTemplate.queryForObject(UserConstants.SQL.SELECT_ONE, Integer.class);
        logger.info(UserConstants.LogMessage.DB_CONNECTION_TEST_RESULT, result);
        return result;
    }

    /**
     * 获取用户表结构
     *
     * @return users表的DDL语句，如果表不存在返回错误信息
     */
    public String getUserTableSchema() {
        try {
            String schema = jdbcTemplate.queryForObject(UserConstants.SQL.SELECT_USER_SCHEMA, String.class);
            return schema != null ? schema : UserConstants.LogMessage.USER_TABLE_SCHEMA_NOT_FOUND;
        } catch (Exception e) {
            logger.error(UserConstants.LogMessage.USER_TABLE_SCHEMA_ERROR, e);
            return UserConstants.LogMessage.USER_TABLE_SCHEMA_ERROR_PREFIX + e.getMessage();
        }
    }

    /**
     * 插入测试用户数据
     * 向users表插入一条测试记录
     *
     * @return 受影响的行数（1表示成功）
     */
    public int insertUser() {
        int affectedRows = jdbcTemplate.update(
                UserConstants.SQL.INSERT_USER,
                UserConstants.UserData.TEST_USERNAME,
                UserConstants.UserData.TEST_AGE
        );
        logger.info(UserConstants.LogMessage.USER_INSERTED, affectedRows);
        return affectedRows;
    }
}
