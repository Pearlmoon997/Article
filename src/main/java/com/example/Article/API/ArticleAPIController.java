package com.example.Article.API;

import com.example.Article.DTO.ArticleForm;
import com.example.Article.Entity.Article;
import com.example.Article.Repository.ArticleRepository;
import com.example.Article.Service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //RestAPI 용 컨트롤러 -> json 형태의 데이터를 반환
public class ArticleAPIController {

    @Autowired //Dependency Injection
    private ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }
    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) { //JSON 을 위한 RequestBody 어노테이션
        //dto 를 Entity 로 변환
        Article created = articleService.create(dto);
        //db 로 저장
        return (created != null) ?              //삼항 연산자
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() ;
    }
    //PATCH
    @PatchMapping("/api/articles/{id}")
    //상태 코드를 보내기 위한 ResponseEntity
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){

        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() ;
    }
    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //트랜잭션 테스트
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactiontest (@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
