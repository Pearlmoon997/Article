package com.example.Article.Controller;

import com.example.Article.DTO.ArticleForm;
import com.example.Article.Entity.Article;
import com.example.Article.Repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j // lombok 로깅
@Controller
public class ArticleController {

    @Autowired //객체 주입하기
    private ArticleRepository articleRepository; //필드 선언

    @GetMapping("/articles/new") //url
    public String newArticleForm() {
        return "articles/new"; //articles 디렉토리의 new.mustache
    }

    @PostMapping("articles/create") //form 에서 method 가 post
    public String createArticle(ArticleForm articleForm) {  //dto 를 파라미터 값으로 선언
        log.info(articleForm.toString());
        //DTO -> Entity 변환
        Article article = articleForm.toEntity();
        log.info(article.toString());
        //Repository 에게 Entity 를 DB에 저장하게 함
        Article saved = articleRepository.save(article); //article 데이터를 save, save 된 데이터를 saved 이름으로 반환
        log.info(saved.toString());
       return "redirect:/articles/" + saved.getId(); //redirect 설정
    }

    @GetMapping("/articles/{id}") //id 위치에 들어가는 값은 변함
    public String show(@PathVariable Long id, Model model){ //id 라는 변수는 url 주소로 부터 입력
        log.info("id = " +id);
        //id 로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null); //id 값으로 데이터 조회, 값이 없으면 null
        //가져온 데이터를 model 에 등록
        model.addAttribute("article", articleEntity); //article 이름으로 articleEntity 등록
        //보여줄 페이지 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //모든 article 가져옴
        List<Article> articleEntityList = articleRepository.findAll(); //타입 캐스팅
        //가져온 article 묶음을 view 로 전달
        model.addAttribute("articleList", articleEntityList);
        //view 페이지 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정 할 데이터 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        //뷰 페이지
        return "/articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());

        //DTO를 엔티티로 변환
        Article articleEntity = form.toEntity();

        //엔티티를 DB로 저장
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        if(target != null) {
            articleRepository.save(articleEntity);
        }

        // 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        //삭제 대상을 가져옴
        Article target = articleRepository.findById(id).orElse(null);

        //삭제
        if(target != null) {
            articleRepository.delete(target);
            //삭제 메세지 출력
            rttr.addFlashAttribute("msg", "삭제 완료");
        }

        //결과 페이지로 리다이렉트
        return "redirect:/articles";
    }
}
