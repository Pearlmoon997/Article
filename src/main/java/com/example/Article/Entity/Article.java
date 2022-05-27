package com.example.Article.Entity;

import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@AllArgsConstructor
@ToString
@Entity //DB가 해당 객체를 인식 가능
public class Article {

    @Id //대표값 지정
    @GeneratedValue // 자동 생성 Annotation
    private Long id;

    @Column //DB의 테이블 단위와 연결
    private String title;

    @Column
    private String content;

}
