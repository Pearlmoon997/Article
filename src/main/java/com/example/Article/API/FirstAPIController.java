package com.example.Article.API;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 을 반환하는 컨트롤러
public class FirstAPIController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World!";
    }

    //일반 컨트롤러와 rest 컨트롤러 차이 -> 일반 컨트롤러는 view 페이지를 반환, rest 는 json 을 반환
}
