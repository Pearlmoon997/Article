package com.example.Article.Repository;

import com.example.Article.Entity.Article;
import org.springframework.data.repository.CrudRepository;

//jpa 에서 제공하는 Repository 인터페이스 활용
public interface ArticleRepository extends CrudRepository<Article, Long> { //관리 대상 Entity (Article), 대표값의 타입 (Long)
}
