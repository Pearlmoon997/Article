package com.example.Article.FirstController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //컨트롤러 선언
public class FirstController {

    @GetMapping("/hi") //접속할 url 주소
    public String nice(Model model) {   //Model 등록
        model.addAttribute("username", "문기"); //변수 등록
        return "greetings"; // templates/greetings.mustache 를 브라우저로 전송
    }

    @GetMapping("/bye")
    public String Bye(Model model){
        model.addAttribute("nickname", "moongi");
        return "goodbye"; //보여줄 view template 페이지
    }
}
