package com.example.Article.Service;

import com.example.Article.DTO.ArticleForm;
import com.example.Article.Entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //스프링 부트와 연동해서 테스트 수행
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test //테스트를 위한 코드 어노테이션
    void index() {
        //예상 데이터
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        //실제 데이터
        List<Article> actual = articleService.index();
        //비교 검증
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void show_Success__존재하는_ID() {
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");

        Article actual = articleService.show(id);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void show_Failed__없는_ID() {
        Long id = -1L;
        Article expected = null;

        Article actual = articleService.show(id);

        assertEquals(expected, actual);
    }

    @Transactional //테스트를 위한 트랜잭션 (테스트 종료 후 롤백)_데이터의 변경이 있는 테스트에 사용
    @Test
    void create_Success__title_content_포함dto() {
        String title = "라라라라";
        String content = "FFFF";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        Article actual = articleService.create(dto);

        assertEquals(expected.toString(), actual.toString());
    }

    @Transactional
    @Test
    void create_Failed__id_포함dto() {
        String title = "라라라라";
        String content = "FFFF";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;

        Article actual = articleService.create(dto);

        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    void update_Success_id_title_content__포함dto() {
        Long id = 1L;
        String title = "GGGG";
        String content = "EEEE";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);

        Article actual = articleService.update(1L, dto);

        assertEquals(expected.toString(), actual.toString());
    }

    @Transactional
    @Test
    void update_Success_id_title__포함dto() {
        Long id = 1L;
        String title = "GGGG";
        ArticleForm dto = new ArticleForm(id, title, "1111");
        Article expected = new Article(id, title, "1111");

        Article actual = articleService.update(1L, dto);

        assertEquals(expected.toString(), actual.toString());
    }

    @Transactional
    @Test
    void update_Failed_없는_ID() {
        Long id = -1L;
        String title = "IIII";
        String content = "UUUU";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        Article actual = articleService.update(id, dto);

        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    void delete_존재하는_ID() {
        Long id = 1L;
        Article expected = articleService.show(id);

        Article actual = articleService.delete(id);

        assertEquals(expected, actual);
    }

    @Transactional
    @Test
    void delete_존재하지않는_ID() {
        Long id = -1L;
        Article expected = articleService.show(id);

        Article actual = articleService.delete(id);

        assertEquals(expected, actual);
    }
}