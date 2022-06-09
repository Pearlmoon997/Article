package com.example.Article.IoC;

import org.springframework.stereotype.Component;

@Component //해달 클래스를 객체로 만들고 IoC 컨테이너에 등록
public class IngredientFactory {
    public Ingredient get(String menu) {
        switch (menu) {
            case "돈가스":
                return new Pork("등심");
            case "스테이크":
                return new Beef("꽃등심");
            case "치킨":
                return new Chicken("국내산 10호 닭");
            default:
                return null;
        }
    }
}
