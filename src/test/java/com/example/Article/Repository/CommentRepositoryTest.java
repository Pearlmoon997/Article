package com.example.Article.Repository;

import com.example.Article.Entity.Article;
import com.example.Article.Entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA 와 연동한 테스트
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회") //테스트를 보여줄 이름
    void findByArticleId() {
        //CASE 1: 4번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleId = 4L;
            //수행 결과 예상
            Article article = new Article(4L, "좋아하는 영화", "댓글로 남겨주세요");
            Comment a = new Comment(1L, article, "Moon", "어버웃 미닛");
            Comment b = new Comment(2L, article, "Lee", "정무문");
            Comment c = new Comment(3L, article, "Park", "굳 윌 헌팅");
            List<Comment> expected = Arrays.asList(a, b, c);
            //실제 수행
            List<Comment> actual = commentRepository.findByArticleId(articleId);
            //비교 검증
            assertEquals(expected.toString(), actual.toString(), "4번 글의 모든 댓글 출력");
        }
        //CASE 2: 1번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleId = 1L;
            //수행 결과 예상
            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();
            //실제 수행
            List<Comment> actual = commentRepository.findByArticleId(articleId);
            //비교 검증
            assertEquals(expected.toString(), actual.toString(), "1번 글의 모든 댓글 조회");
        }
        //CASE 3: 9번 게시글의 모든 댓글 조회
        {
            Long articleId = 9L;

            Article article = null;
            List<Comment> expected = Arrays.asList();

            List<Comment> actual = commentRepository.findByArticleId(articleId);

            assertEquals(expected, actual);
        }
        //CASE 4: -1번 게시글의 모든 댓글 조회
        {
            Long articleId = -1L;

            Article article = null;
            List<Comment> expected = Arrays.asList();

            List<Comment> actual = commentRepository.findByArticleId(articleId);

            assertEquals(expected, actual);
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        //CASE 1: "Park"의 모든 댓글 조회
        {
            //입력 데이터 준비
            String nickname = "Park";
            //예상
            Comment a = new Comment(3L, new Article(4L, "좋아하는 영화", "댓글로 남겨주세요"), nickname, "굳 윌 헌팅");
            Comment b = new Comment(6L, new Article(5L, "좋아하는 음식", "댓글로 남겨주시라요"), nickname, "돈까스");
            Comment c = new Comment(9L, new Article(6L, "취미", "댓글로 남겨줘"), nickname, "독서");
            List<Comment> expected = Arrays.asList(a, b, c);
            //실제 수행
            List<Comment> actual = commentRepository.findByNickname(nickname);
            //비교 검증
            assertEquals(expected.toString(), actual.toString(), "Park 의 모든 댓글 조회");
        }
        //CASE 2: "Moon"의 모든 댓글 조회
        {
            String nickname = "Moon";

            Comment a = new Comment(1L, new Article(4L, "좋아하는 영화", "댓글로 남겨주세요"), nickname, "어버웃 미닛");
            Comment b = new Comment(4L, new Article(5L, "좋아하는 음식", "댓글로 남겨주시라요"), nickname, "냉면");
            Comment c = new Comment(7L, new Article(6L, "취미", "댓글로 남겨줘"), nickname, "여행");
            List<Comment> expected = Arrays.asList(a, b, c);

            List<Comment> actual = commentRepository.findByNickname(nickname);

            assertEquals(expected.toString(), actual.toString(), "Moon 의 모든 댓글 조회");
        }
        //CASE 3: null 의 모든 댓글 조회
        {
            String nickname = null;

            List<Comment> expected = Arrays.asList();

            List<Comment> actual = commentRepository.findByNickname(nickname);

            assertEquals(expected, actual, "null 의 모든 댓글 조회");
        }
        //CASE 4: ""의 모든 댓글 조회
        {
            String nickname = "";

            List<Comment> expected = Arrays.asList();

            List<Comment> actual = commentRepository.findByNickname(nickname);

            assertEquals(expected.toString(), actual.toString(), "의 모든 댓글 조회");
        }

    }
}