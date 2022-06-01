package com.example.Article.Service;

import com.example.Article.DTO.ArticleForm;
import com.example.Article.Entity.Article;
import com.example.Article.Repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service //서비스 선정 (서비스 객체를 스프링부트에 생성)
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {   //아이디가 있으면 null 리턴
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        //수정용 엔티티 생성
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString()); //id = 1번 중괄호, article.toString() = 2번 중괄호
        //대상 엔티티 가져옴
        Article target = articleRepository.findById(id).orElse(null);
        //잘못된 요청 처리 (대상이 없거나, 다른 아이디)
        if (target == null || id != article.getId()){ //URL ID 와 DTO ID 가 다른 경우
            //400 잘못된 요청 응답 처리
            log.info("잘못된 요청, id: {}, article: {}", id, article.toString());
            return null;
        }
        //업데이트 및 응답
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        //잘못된 요청 응답 처리
        if (target == null) {
            return null;
        }
        //대상 삭제
        articleRepository.delete(target);
        //반환
        return target;
    }


    @Transactional //해당 메소드를 트랜잭션으로 묶 -> 메소드 수행을 실패하면 메소드 실행 이전의 상태로 되돌
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //dto 묶음을 Entity 묶음으로 변환
        List<Article> articleList = dtos.stream()       //스트림 문법
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //Entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        //강제 에러 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("실패")
        );
        //반환
        return articleList;
    }
}
