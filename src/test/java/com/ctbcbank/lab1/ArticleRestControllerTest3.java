package com.ctbcbank.lab1;

import com.ctbcbank.lab1.model.Article;
import com.ctbcbank.lab1.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc                   // 自動構建 MockMvc
@ExtendWith(SpringExtension.class)      // 表示需要為當前的測試加上Spring執行時的環境 e.g. Bean...?
public class ArticleRestControllerTest3 {

    /* 用來模擬http request */
    @Resource
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

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

        // 打樁
        ObjectMapper objectMapper = new ObjectMapper();
        Article articleObj = objectMapper.readValue(article, Article.class);
        when(articleService.saveArticle(articleObj)).thenReturn("ok");

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .request(HttpMethod.POST, "/rest/articles")
                        .contentType("application/json")
                        .content(article)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value("ok"))
                .andDo(print())
                .andReturn();

        log.info("### PASS: " + result.getResponse().getContentAsString());
    }
}
