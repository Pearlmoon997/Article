package com.example.Article.Service;

import com.example.Article.DTO.CommentDto;
import com.example.Article.Entity.Article;
import com.example.Article.Entity.Comment;
import com.example.Article.Repository.ArticleRepository;
import com.example.Article.Repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
//        //조회: 댓글 목록
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        //변환: 엔티티 -> DTO
//        List<CommentDto> dtos = new ArrayList<CommentDto>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment comment = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(comment);
//            dtos.add(dto);
//        }
        //반환
        return commentRepository.findByArticleId(articleId) //스트림 문법
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        //조회 및 예외발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("생성 실패, 대상 게시글 없음")); //예외 발생
        //댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        //DB 저장
        Comment created = commentRepository.save(comment);
        //DTO 로 변환하여 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패, 대상 댓글 없음"));
        //수정
        target.patch(dto);
        //DB 갱신
        Comment updated = commentRepository.save(target);
        //DTO 로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        //조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상이 없음"));
        //삭제
        commentRepository.delete(target);
        //DTO 로 반환
        return CommentDto.createCommentDto(target);
    }
}
