package com.example.Article.Repository;

import com.example.Article.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//JpaRepository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    //특정 게시글의 모든 댓글 조회
    //쿼리문 설정 어노테이션
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    //특정 닉네임의 모든 댓글 조회
    //xml 로 작성
    List<Comment> findByNickname(@Param("nickname") String nickname);
}
