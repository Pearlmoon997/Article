package com.example.Article.Controller;

import com.example.Article.DTO.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles/new") //url
    public String newArticleForm() {
        return "articles/new"; //articles 디렉토리의 new.mustache
    }

    @PostMapping("articles/create") //form 에서 method 가 post
    public String createArticle(ArticleForm articleForm) {  //dto 를 파라미터 값으로 선언
        System.out.println(articleForm.toString());
        return "";
    }
}
