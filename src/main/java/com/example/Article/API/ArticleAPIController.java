package com.example.Article.API;

import com.example.Article.DTO.ArticleForm;
import com.example.Article.Entity.Article;
import com.example.Article.Repository.ArticleRepository;
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
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }
    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) { //JSON 을 위한 RequestBody 어노테이션
        //dto 를 Entity 로 변환
        Article article = dto.toEntity();
        //db 로 저장
        return articleRepository.save(article);
    }
    //PATCH
    @PatchMapping("/api/articles/{id}")
    //상태 코드를 보내기 위한 ResponseEntity
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        //수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString()); //id = 1번 중괄호, article.toString() = 2번 중괄호
        //대상 엔티티 가져옴
        Article target = articleRepository.findById(id).orElse(null);
        //잘못된 요청 처리 (대상이 없거나, 다른 아이디)
        if (target == null || id != article.getId()){ //URL ID 와 DTO ID 가 다른 경우
            //400 잘못된 요청 응답 처리
            log.info("잘못된 요청, id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //업데이트 및 응답
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //잘못된 요청 응답 처리
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        //대상 삭제
        articleRepository.delete(target);
        //반환
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
