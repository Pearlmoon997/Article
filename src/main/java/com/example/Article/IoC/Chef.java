package com.example.Article.IoC;

import org.springframework.stereotype.Component;

@Component
public class Chef {
    //셰프는 식재료 공장을 알고있음
    private IngredientFactory ingredientFactory;

    //셰프 가 식재료 공장과 협업하기 위한 DI
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        //재료 준비
        Ingredient ingredient = ingredientFactory.get(menu);

        //반환
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
