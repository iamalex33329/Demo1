package com.ctbcbank.lab1;

import com.ctbcbank.lab1.controller.ArticleController;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/*
*
* 如果沒有加上：
* 1. @ExtendWith(SpringExtension.class)
* 2. @AutoConfigureMockMvc
*
* 就只能測試Controller層面的程式
*
* @當程式裡出現 @Resource Bean的依賴注入？ 就需要加上以上註解
*
* */

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc                   // 自動構建 MockMvc
@ExtendWith(SpringExtension.class)      // 表示需要為當前的測試加上Spring執行時的環境 e.g. Bean...?
public class ArticleRestControllerTest2 {

    /* 用來模擬http request */
    @Resource
    private MockMvc mockMvc;
//    private static MockMvc mockMvc;
//
//    @BeforeAll
//    static void setup() {
//        // 針對哪一個 Controller 進行測試？ ==> ArticleController
//        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleController()).build();
//    }

    @Test
    public void saveArticle() throws Exception {
        String article = "{\n" +
                "\t\"id\": 999,\n" +
                "\t\"author\": \"Billy\",\n" +
                "\t\"title\": \"Time is Money\",\n" +
                "\t\"content\": \"aaa bbb ccc\",\n" +
                "\t\"createTime\": \"2023/3/29 00:00:00\",\n" +
                "\t\"reader\": [{\"name\":\"Alex\", \"age\":24}, {\"name\":\"Mary\", \"age\":13}]\n" +
                "}";

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .request(HttpMethod.POST, "/rest/articles")
                        .contentType("application/json")
                        .content(article)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("Billy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.reader[0].age").value(24))
                .andDo(print())
                .andReturn();

        log.info("### PASS: " + result.getResponse().getContentAsString());
    }
}
