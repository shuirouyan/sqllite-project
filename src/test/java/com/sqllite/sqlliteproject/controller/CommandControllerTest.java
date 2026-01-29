package com.sqllite.sqlliteproject.controller;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * CommandController单元测试类
 */
@SpringBootTest
class CommandControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    /**
     * 每个测试方法执行前初始化MockMvc
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 每个测试方法执行后清理数据
     */
    @AfterEach
    void cleanup() throws Exception {
        mockMvc.perform(delete("/api/commands/all"));
    }

    @Test
    void testCreateCommand() throws Exception {
        String requestBody = "{\"text\":\"Test Command\",\"num\":100}";

        mockMvc.perform(post("/api/commands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.affectedRows").value(1));
    }

    @Test
    void testCreateCommandWithNullNum() throws Exception {
        String requestBody = "{\"text\":\"Test Command\",\"num\":null}";

        mockMvc.perform(post("/api/commands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testGetCommandById() throws Exception {
        // 先创建一个命令
        String createBody = "{\"text\":\"Test Command\",\"num\":100}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createBody));

        // 查询命令
        mockMvc.perform(get("/api/commands/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.text").value("Test Command"))
                .andExpect(jsonPath("$.num").value(100));
    }

    @Test
    void testGetAllCommands() throws Exception {
        // 创建多个命令
        String body1 = "{\"text\":\"First Command\",\"num\":100}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body1));

        String body2 = "{\"text\":\"Second Command\",\"num\":200}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body2));

        // 查询所有命令
        mockMvc.perform(get("/api/commands"))
                .andExpect(status().isOk());
    }

    @Test
    void testSearchCommandsByText() throws Exception {
        // 创建命令
        String body1 = "{\"text\":\"Hello World\",\"num\":100}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body1));

        String body2 = "{\"text\":\"Hello Java\",\"num\":200}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body2));

        // 搜索命令
        mockMvc.perform(get("/api/commands/search")
                        .param("text", "Hello"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCommand() throws Exception {
        // 先创建一个命令
        String createBody = "{\"text\":\"Original Text\",\"num\":100}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createBody));

        // 更新命令
        String updateBody = "{\"text\":\"Updated Text\",\"num\":200}";

        mockMvc.perform(put("/api/commands/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.affectedRows").value(1));
    }

    @Test
    void testDeleteCommand() throws Exception {
        // 先创建一个命令
        String body = "{\"text\":\"Test Command\",\"num\":100}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));

        // 删除命令
        mockMvc.perform(delete("/api/commands/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.affectedRows").value(1));
    }

    @Test
    void testDeleteAllCommands() throws Exception {
        // 创建多个命令
        String body1 = "{\"text\":\"First Command\",\"num\":100}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body1));

        String body2 = "{\"text\":\"Second Command\",\"num\":200}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body2));

        // 删除所有命令
        mockMvc.perform(delete("/api/commands/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void testCountCommands() throws Exception {
        // 创建多个命令
        String body1 = "{\"text\":\"First Command\",\"num\":100}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body1));

        String body2 = "{\"text\":\"Second Command\",\"num\":200}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body2));

        String body3 = "{\"text\":\"Third Command\",\"num\":300}";
        mockMvc.perform(post("/api/commands")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body3));

        // 统计命令数量
        mockMvc.perform(get("/api/commands/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(3));
    }

    @Test
    void testCountCommandsWhenEmpty() throws Exception {
        mockMvc.perform(get("/api/commands/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(0));
    }
}
