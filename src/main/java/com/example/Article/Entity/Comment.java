package com.example.Article.Entity;

import com.example.Article.DTO.CommentDto;
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

    public static Comment createComment(CommentDto dto, Article article) {
        //예외 발생
        if (dto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패, 댓글의 id가 없어야 함");
        } if (dto.getArticleId() != article.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패, 게시글의 id가 다릅니다");
        }
        //엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        //예외 발생
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패, 댓글의 i가 다름");
        }
        //객체 갱신
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        } if (dto.getBody() != null) {
            this.body = dto.getBody();
        }

    }
}
