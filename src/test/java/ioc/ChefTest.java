package ioc;

import com.example.firstproject.ioc.Chef;
import com.example.firstproject.ioc.Factory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;



class ChefTest {

    @Test
    void 돈가스_요리하기(){
        // 준비
        Factory factory = new Factory();
        Chef chef = new Chef(factory);// 재료공장에 대한 정보를 삽입(DI삽입)
        String menu = "돈가스";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "한돈 등심으로 만든 돈가스";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 스테이크_요리하기(){
        // 준비
        Factory factory = new Factory();
        Chef chef = new Chef(factory);// 재료공장에 대한 정보를 삽입(DI삽입)
        String menu = "스테이크";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "한우 꽃등심으로 만든 스테이크";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 치킨_요리하기(){
        // 준비
        Factory factory = new Factory();
        Chef chef = new Chef(factory);// 재료공장에 대한 정보를 삽입(DI삽입)
        String menu = "치킨";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "국내산 10호 닭으로 만든 치킨";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

}

