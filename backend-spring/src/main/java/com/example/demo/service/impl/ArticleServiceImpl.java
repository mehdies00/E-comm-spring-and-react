package com.example.demo.service.impl;

import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Optional<Article> getArticleById(Integer id) {
        return articleRepository.findById(id);
    }

    @Override
    @Transactional
    public Article saveArticle(Article article) {
        if (article.getPrice() == null || article.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }
        if (article.getImageUrl() == null || article.getImageUrl().isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be null or empty");
        }

        if (article.getName() == null || article.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        return articleRepository.save(article);
    }

    @Override
    @Transactional
    public Article updateArticle(Integer id, Article article) {
        Optional<Article> existingArticleOpt = articleRepository.findById(id);
        if (existingArticleOpt.isEmpty()) {
            throw new IllegalArgumentException("Article with id " + id + " not found");
        }
        if (article.getPrice() != null && article.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (article.getImageUrl() != null && article.getImageUrl().isEmpty()) {
            throw new IllegalArgumentException("Image URL cannot be empty");
        }
        if (article.getName() != null && article.getName().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        Article existingArticle = existingArticleOpt.get();
        existingArticle.setName(article.getName());
        existingArticle.setDescription(article.getDescription());
        existingArticle.setPrice(article.getPrice());
        existingArticle.setImageUrl(article.getImageUrl());
        return articleRepository.save(existingArticle);
    }

    @Override
    @Transactional
    public void deleteArticle(Integer id) {
        if (!articleRepository.existsById(id)) {
            throw new IllegalArgumentException("Article with id " + id + " not found");
        }
        articleRepository.deleteById(id);
    }
}
