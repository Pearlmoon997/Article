package com.example.Article.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //댓글 엔티티 여러개가 하나의 Article 에 연관
    @JoinColumn(name = "article_id") //테이블에서 연결된 대상의 정보의 컬럼
    private Article article; //FK
    
    @Column
    private String nickname;

    @Column
    private String body;
}
