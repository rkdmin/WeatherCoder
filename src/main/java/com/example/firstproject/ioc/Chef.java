package com.example.firstproject.ioc;

public class Chef {
    // 셰프는 식재료 공장을 알고있음
    private Factory factory;

    // 셰프가 식재로 공장과 협업하기 위한 DI
    public Chef(Factory factory){
        this.factory = factory;
    }

    public String cook(String menu) {
        // 재료 준비
        Ingredient ingredient = factory.get(menu);
        // 요리 반환
        return ingredient.getName() + "으로 만든 " + menu;
    }
}

