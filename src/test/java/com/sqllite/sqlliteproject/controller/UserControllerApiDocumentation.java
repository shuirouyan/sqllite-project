package com.sqllite.sqlliteproject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * UserController API文档测试
 */
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
public class UserControllerApiDocumentation {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)
                        .uris()
                        .withScheme("http")
                        .withHost("localhost")
                        .withPort(8080))
                .build();
    }

    @Test
    public void testConnection() throws Exception {
        this.mockMvc.perform(get("/api/users/test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user/test-connection"));
    }

    @Test
    public void testConnection2() throws Exception {
        this.mockMvc.perform(get("/api/users/sqllite"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user/test-connection2"));
    }

    @Test
    public void getUserTableSchema() throws Exception {
        this.mockMvc.perform(get("/api/users/schema"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user/get-schema"));
    }

    @Test
    public void getClassPath() throws Exception {
        this.mockMvc.perform(get("/api/users/classpath"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user/get-classpath"));
    }

    @Test
    public void insertUser() throws Exception {
        this.mockMvc.perform(get("/api/users/insert"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("user/insert"));
    }
}
