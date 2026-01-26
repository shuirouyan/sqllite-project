package com.sqllite.sqlliteproject.controller;

import com.sqllite.sqlliteproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.io.FileNotFoundException;

/**
 * 用户控制器
 * 提供用户相关的REST API接口
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 测试数据库连接
     *
     * @return 连接测试结果
     */
    @GetMapping("/test")
    public EntityResponse<String> testConnection() {
        Integer resp = userService.testConnection();
        return EntityResponse
                .fromObject("Database connection test executed. Check console for results.num:".formatted("%d", resp))
                .build();
    }

    /**
     * SQLite连接测试接口
     *
     * @return 数据库连接测试结果
     */
    @GetMapping("/sqllite")
    public Integer testConnection2() {
        Integer resp = userService.testConnection();
        return resp;
    }

    /**
     * 获取用户表结构
     *
     * @return users表的DDL语句
     */
    @GetMapping("/schema")
    public String getUserTableSchema() {
        return userService.getUserTableSchema();
    }

    /**
     * 获取classpath路径
     *
     * @return classpath路径字符串
     */
    @GetMapping("classpath")
    public String getClassPath() {
        try {
            return ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 插入测试用户数据
     *
     * @return 受影响的行数
     */
    @GetMapping("/insert")
    public int insertUser() {
        return userService.insertUser();
    }
}
