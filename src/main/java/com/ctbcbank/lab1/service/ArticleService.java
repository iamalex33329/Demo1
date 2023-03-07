package com.ctbcbank.lab1.service;

import com.ctbcbank.lab1.model.Article;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {

    public String saveArticle(Article article);
}
