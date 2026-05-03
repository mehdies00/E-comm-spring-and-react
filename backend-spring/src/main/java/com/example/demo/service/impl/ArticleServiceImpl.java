package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 1. Get all articles
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.getAllArticles();
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    // 2. Get article by ID
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Integer id) {
        return articleService.getArticleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Create a new article
    @PostMapping
    public ResponseEntity<?> saveArticle(@RequestBody Article article) {
        try {
            Article savedArticle = articleService.saveArticle(article);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 4. Update an existing article
    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Integer id, @RequestBody Article article) {
        try {
            Article updatedArticle = articleService.updateArticle(id, article);
            return ResponseEntity.ok(updatedArticle);
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 5. Delete an article
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Integer id) {
        try {
            articleService.deleteArticle(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}