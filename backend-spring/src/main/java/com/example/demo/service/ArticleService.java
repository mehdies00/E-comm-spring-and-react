package com.example.demo.service;

import com.example.demo.model.Article;
import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> getAllArticles();

    Optional<Article> getArticleById(Integer id);

    Article saveArticle(Article article);

    Article updateArticle(Integer id, Article article);

    void deleteArticle(Integer id);

}