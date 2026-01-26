package com.sqllite.sqlliteproject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testConnection() throws Exception {
        mockMvc.perform(get("/api/users/test"))
                .andExpect(status().isOk());
    }

    @Test
    void testConnection2() throws Exception {
        mockMvc.perform(get("/api/users/sqllite"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserTableSchema() throws Exception {
        mockMvc.perform(get("/api/users/schema"))
                .andExpect(status().isOk());
    }

    @Test
    void insertUser() throws Exception {
        mockMvc.perform(get("/api/users/insert"))
                .andExpect(status().isOk());
    }
}
