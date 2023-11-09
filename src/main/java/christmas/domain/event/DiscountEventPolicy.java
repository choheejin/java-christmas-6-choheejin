package christmas.domain.event;

import christmas.domain.human.Human;
import christmas.domain.menu.Menus;

import java.util.HashMap;
import java.util.Map;

public class DiscountEventPolicy implements IEventPolicy {
    private Map<String, Integer> result = new HashMap<>();

    @Override
    public Map<String, Integer> getDiscountAmount(Human human, Menus menus, boolean condition) {
        if (condition) {
            christmasEvent(human);
            weekdayEvent(human, menus);
            weekendEvent(human, menus);
            specialEvent(human);
        }
        return result;
    }

    private void christmasEvent(Human human) {
        int discount = 0;
        if (human.isDayBelowStandard(25)) {
            discount = 1000 + 100 * human.getDifferenceBaseDate(1);
        }
        result.put("ChristMas", discount);
    }

    private void weekdayEvent(Human human, Menus menus) {
        int discount = 0;
        if (human.isWeekday()) {
            discount = 2_023 * menus.getDessertCount().intValue();
        }
        result.put("Weekday", discount);
    }

    private void weekendEvent(Human human, Menus menus) {
        int discount = 0;
        if (human.isWeekend()) {
            discount = 2_023 * menus.getMainCount().intValue();
        }
        result.put("Weekend", discount);
    }

    private void specialEvent(Human human) {
        int discount = 0;
        if (human.isSpecialDay()) {
            discount = 1000;
        }
        result.put("Special", discount);
    }
}
