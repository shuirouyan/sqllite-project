package com.sqllite.sqlliteproject.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
 * CommandController API文档测试
 */
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
public class CommandControllerApiDocumentation {

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
    public void createCommand() throws Exception {
        String jsonRequest = "{\"text\":\"test command\",\"num\":10}";

        this.mockMvc.perform(post("/api/commands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("command/create",
                        requestFields(
                                fieldWithPath("text").description("命令文本"),
                                fieldWithPath("num").description("数字值")
                        ),
                        responseFields(
                                fieldWithPath("success").description("操作是否成功"),
                                fieldWithPath("affectedRows").description("受影响的行数")
                        )
                ));
    }

    @Test
    public void getCommandById() throws Exception {
        this.mockMvc.perform(get("/api/commands/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("command/get-by-id",
                        pathParameters(
                                parameterWithName("id").description("命令ID")
                        )
                ));
    }

    @Test
    public void getAllCommands() throws Exception {
        this.mockMvc.perform(get("/api/commands"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("command/get-all"));
    }

    @Test
    public void searchCommandsByText() throws Exception {
        this.mockMvc.perform(get("/api/commands/search")
                        .param("text", "search"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("command/search"));
    }

    @Test
    public void updateCommand() throws Exception {
        String jsonRequest = "{\"text\":\"updated command\",\"num\":20}";

        this.mockMvc.perform(put("/api/commands/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("command/update",
                        pathParameters(
                                parameterWithName("id").description("命令ID")
                        ),
                        requestFields(
                                fieldWithPath("text").description("命令文本"),
                                fieldWithPath("num").description("数字值")
                        ),
                        responseFields(
                                fieldWithPath("success").description("操作是否成功"),
                                fieldWithPath("affectedRows").description("受影响的行数")
                        )
                ));
    }

    @Test
    public void deleteCommand() throws Exception {
        this.mockMvc.perform(delete("/api/commands/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("command/delete",
                        pathParameters(
                                parameterWithName("id").description("命令ID")
                        ),
                        responseFields(
                                fieldWithPath("success").description("操作是否成功"),
                                fieldWithPath("affectedRows").description("受影响的行数")
                        )
                ));
    }

    @Test
    public void deleteAllCommands() throws Exception {
        this.mockMvc.perform(delete("/api/commands/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("command/delete-all",
                        responseFields(
                                fieldWithPath("success").description("操作是否成功"),
                                fieldWithPath("affectedRows").description("受影响的行数")
                        )
                ));
    }

    @Test
    public void countCommands() throws Exception {
        this.mockMvc.perform(get("/api/commands/count"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("command/count",
                        responseFields(
                                fieldWithPath("count").description("命令总数")
                        )
                ));
    }
}
