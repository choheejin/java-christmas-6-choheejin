package christmas;

import christmas.domain.event.DiscountEventPolicy;
import christmas.domain.event.GiftEventPolicy;
import christmas.domain.human.Human;
import christmas.domain.menu.Menu;
import christmas.domain.menu.Menus;

import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        Human human = new Human(120_900, 22);

        Menus menus = new Menus(
                List.of(
                        Menu.BBQ_RIBS,
                        Menu.RED_WINE,
                        Menu.CHOCO_CAKE,
                        Menu.CAESAR_SALAD
                )
        );

        Map<String, Integer> discountResult = new DiscountEventPolicy().getDiscountAmount(human, menus, true);
        int giftCount = new GiftEventPolicy().getGiftCount(human);
        System.out.println(discountResult);
        System.out.println(giftCount);
    }
}
