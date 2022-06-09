package com.example.Article.IoC;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChefTest {

    @Autowired
    IngredientFactory ingredientFactory;

    @Autowired
    Chef chef;

    @Test
    void 돈가스_요리() {
        //준비
        //IngredientFactory ingredientFactory = new IngredientFactory();
        //Chef chef = new Chef(ingredientFactory);
        String menu = "돈가스";
        //수행
        String actual = chef.cook(menu);
        //예상
        String expected = "등심으로 만든 돈가스";
        //검증
        assertEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    void 스테이크_요리() {
        //준비
        //IngredientFactory ingredientFactory = new IngredientFactory();
        //Chef chef = new Chef(ingredientFactory);
        String menu = "스테이크";
        //수행
        String actual = chef.cook(menu);
        //예상
        String expected = "꽃등심으로 만든 스테이크";
        //검증
        assertEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    void 치킨_요리() {
        //준비
        //IngredientFactory ingredientFactory = new IngredientFactory();
        //Chef chef = new Chef(ingredientFactory);
        String menu = "치킨";
        //수행
        String actual = chef.cook(menu);
        //예상
        String expected = "국내산 10호 닭으로 만든 치킨";
        //검증
        assertEquals(expected, actual);
        System.out.println(actual);
    }
}