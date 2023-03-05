package com.ctbcbank.lab1.controller;

import com.ctbcbank.lab1.AjaxResponse;
import com.ctbcbank.lab1.model.Article;
import com.ctbcbank.lab1.model.Reader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController     // @RestController 其實是 @Controller + method 的 @ResponseBody 的結合體
@RequestMapping("/rest")
public class ArticleController {

    /*
    - 如果今天是用 @Controller，則必須加上 @ResponseBody 來返回 JSON 數據 (keywords: 序列化、反序列化)
    - 若不加上 @ResponseBody，並且把return值改成 String，程式會自動去 > resources > templates > xxx.html 來「渲染」頁面

    @DeleteMapping("/articles/{id}")
    public @ResponseBody AjaxResponse updateArticle(@PathVariable("id") Long id) {
        log.info("Delete article: " + id);
        return AjaxResponse.success();
    }
    */

    /* GET => 查詢一篇文章，根據id */
    // @RequestMapping(value = "/articles/{id}", method = RequestMethod.GET)
    @GetMapping("/articles/{id}")
    public AjaxResponse getArticle(@PathVariable Long id) {
        List<Reader> readers = new ArrayList<>() {{
            add(new Reader("Duncan", 22));
            add(new Reader("Jane", 23));
        }};

        Article article = Article.builder()
                        .id(id)
                        .author("Alex")
                        .content("Spring Boot 學習")
                        .createTime(new Date())
                        .reader(readers)
                        .title("t1").build();
        log.info("Get article: " + article);
        return AjaxResponse.success(article);
    }

    /* POST => 新增一篇文章 */
    // 可以接收大量對象，並且支援類別中還有類別的方式 >>> #以Article中還有List<Reader>為例
    // @RequestMapping(value = "/articles", method = RequestMethod.POST)
    @PostMapping("/articles")
    public AjaxResponse saveArticle(@RequestBody Article article,
                                    @RequestHeader String aaa) {
        log.info("Save article: " + aaa);
        return AjaxResponse.success();
    }

    // 類似前端 form-data 的表格接收方式
//    @PostMapping("/articles")
//    public AjaxResponse saveArticle(@RequestParam String author,
//                                    @RequestParam String title,
//                                    @RequestParam String content,
//                                    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//                                    @RequestParam Date createTime) {
//        log.info("Save article: " + createTime);
//        return AjaxResponse.success();
//    }

    /* PUT */
    // @RequestMapping(value = "/articles", method = RequestMethod.PUT)
    @PutMapping("/articles")
    public AjaxResponse updateArticle(@RequestBody Article article) {
        if (article.getId() == null) {} // TODO 拋出一個自定義的exception

        log.info("Update article: " + article.getId());
        return AjaxResponse.success();
    }

    /* DELETE */
    // @RequestMapping(value = "/articles/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/articles/{id}")
    public AjaxResponse updateArticle(@PathVariable("id") Long id) {
        log.info("Delete article: " + id);
        return AjaxResponse.success();
    }

}









