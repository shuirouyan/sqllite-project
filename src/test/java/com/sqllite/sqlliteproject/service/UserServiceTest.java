package com.sqllite.sqlliteproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testConnection() {
        Integer result = userService.testConnection();
        assertNotNull(result);
        assertEquals(1, result);
    }

    @Test
    void getUserTableSchema() {
        String schema = userService.getUserTableSchema();
        assertNotNull(schema);
        assertFalse(schema.contains("Error"));
        System.out.println("User table schema: " + schema);
    }

    @Test
    @Sql(scripts = "/sql/cleanup-users.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void insertUser() {
        int affectedRows = userService.insertUser();
        assertEquals(1, affectedRows);
    }

    @Test
    void insertMultipleUsers() {
        int firstInsert = userService.insertUser();
        assertEquals(1, firstInsert);

        int secondInsert = userService.insertUser();
        assertEquals(1, secondInsert);
    }
}
