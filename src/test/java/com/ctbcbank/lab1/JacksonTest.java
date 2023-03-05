package com.ctbcbank.lab1;

import com.ctbcbank.lab1.model.Article;
import com.ctbcbank.lab1.model.Reader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JacksonTest {

    @Test
    void testJackson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<Reader> readers = new ArrayList<>() {{
            add(new Reader("Duncan", 22));
            add(new Reader("Jane", 23));
        }};

        Article article = Article.builder()
                .id(2L)
                .author("Alex")
                .content("Spring Boot 學習")
                .createTime(new Date())
                .reader(readers)
                .title("t1").build();

        String jsonStr = mapper.writeValueAsString(article);
        System.out.println(jsonStr);

        // 利用 mapper.readValue() 的類別必須要有「全參建構子」「無參建構子」
        Article a1 = mapper.readValue("{\"content\":\"Spring Boot 學習\",\"title\":\"t1\",\"createTime\":\"2023/03/05 19:28:05\",\"reader\":[{\"name\":\"Duncan\",\"age\":22},{\"name\":\"Jane\",\"age\":23}],\"auther\":\"Alex\"}", Article.class);
        System.out.println(a1);
    }

}
