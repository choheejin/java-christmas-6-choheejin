package christmas.domain.event;

import christmas.domain.human.Human;
import christmas.domain.menu.Menus;

import java.util.HashMap;
import java.util.Map;

public interface IEventPolicy {
    default Map<String, Integer> getDiscountAmount(Human human, Menus menus, boolean condition) {
        return new HashMap<>();
    }

    default int getGiftCount(Human human) {
        return 0;
    }
}
