package com.example.Article.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@ToString
@NoArgsConstructor //디폴트 생성자
@Getter //게터 추가
@Entity //DB가 해당 객체를 인식 가능, (해당 클래스로 테이블 생성)
public class Article {

    @Id //대표값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 Annotation
    private Long id;

    @Column //DB의 테이블 단위와 연결
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
            if (article.content != null)
                this.content = article.content;
        }
    }
}
