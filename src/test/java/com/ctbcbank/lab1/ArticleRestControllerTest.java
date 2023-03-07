package com.ctbcbank.lab1;

import com.ctbcbank.lab1.controller.ArticleController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/*
* [自動化的工具]
* 1. Junit
* 2. Mockito
* 3. spring-boot-starter-rest
*
* @在maven裡面執行"package"的時候就會自動跑過所有Test
*
* */

@Slf4j
public class ArticleRestControllerTest {

    /* 用來模擬http request */
    private static MockMvc mockMvc;

    @BeforeAll
    static void setup() {
        // 針對哪一個 Controller 進行測試？ ==> ArticleController
        mockMvc = MockMvcBuilders.standaloneSetup(new ArticleController()).build();
    }

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
