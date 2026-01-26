package com.sqllite.sqlliteproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 命令行自动配置器
 * 在Spring Boot应用启动完成后自动执行，用于初始化数据库表结构
 *
 * @author kangchen
 * @date 2026/1/26 12:05
 */
@Component
public class CommandLineAutoConfig implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 应用启动后执行的方法
     * 创建command表，如果表已存在则忽略
     *
     * @param args 命令行参数
     * @throws Exception 执行异常
     */
    @Override
    public void run(String... args) throws Exception {
        String sql = """
                CREATE TABLE IF NOT EXISTS command (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    `text` TEXT NOT NULL,
                    num INTEGER
                )
            """;
        jdbcTemplate.execute(sql);

    }
}
