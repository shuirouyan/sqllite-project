package com.sqllite.sqlliteproject.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 应用启动自动执行器
 * 在Spring Boot应用启动完成后自动执行，用于初始化数据库表结构
 *
 * @author kangchen
 * @date 2026/1/26 12:06
 */
@Component
public class ApplicationAutoRunner implements ApplicationRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 应用启动后执行的方法
     * 创建users表，如果表已存在则忽略
     *
     * @param args 应用启动参数
     * @throws Exception 执行异常
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT NOT NULL,
                    age INTEGER
                )
            """;
        jdbcTemplate.execute(sql);

    }
}
