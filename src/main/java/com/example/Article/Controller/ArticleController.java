package com.example.Article.Controller;

import com.example.Article.DTO.ArticleForm;
import com.example.Article.Entity.Article;
import com.example.Article.Repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j // lombok 로깅
@Controller
public class ArticleController {

    @Autowired //객체 주입하기
    private ArticleRepository articleRepository; //필드 선언

    @GetMapping("/articles/new") //url
    public String newArticleForm() {
        return "articles/new"; //articles 디렉토리의 new.mustache
    }

    @PostMapping("articles/create") //form 에서 method 가 post
    public String createArticle(ArticleForm articleForm) {  //dto 를 파라미터 값으로 선언
        log.info(articleForm.toString());
        //DTO -> Entity 변환
        Article article = articleForm.toEntity();
        log.info(article.toString());
        //Repository 에게 Entity 를 DB에 저장하게 함
        Article saved = articleRepository.save(article); //article 데이터를 save, save 된 데이터를 saved 이름으로 반환
        log.info(saved.toString());
       return "";
    }
}
