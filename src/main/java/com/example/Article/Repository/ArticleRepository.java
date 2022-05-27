package com.example.Article.Repository;

import com.example.Article.Entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

//jpa 에서 제공하는 Repository 인터페이스 활용
public interface ArticleRepository extends CrudRepository<Article, Long> { //관리 대상 Entity (Article), 대표값의 타입 (Long)


    @Override
    ArrayList<Article> findAll();  //메소드 오버라이딩, 타입 캐스팅
}
