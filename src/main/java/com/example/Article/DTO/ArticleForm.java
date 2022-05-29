package com.example.Article.DTO;

import com.example.Article.Entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // lombok 생성자
@ToString // lombok toString
//form 데이터를 받아 올 그릇
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() { //Entity 변환 메소드
        return new Article(id, title, content); //Article 의 생성자를 호출
    }
}
