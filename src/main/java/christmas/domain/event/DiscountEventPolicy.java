package christmas.domain.event;

import christmas.domain.condition.ICondition;
import christmas.domain.human.Date;
import christmas.domain.human.Menus;
import christmas.domain.human.Money;

import java.util.HashMap;
import java.util.Map;

public class DiscountEventPolicy implements IEventPolicy, ICondition {
    private Map<String, Integer> result = new HashMap<>();

    @Override
    public Map<String, Integer> getDiscountAmount(Menus menus, Money money, Date date) {
        if (isAvailableToParticipateEvent(money)) {
            christmasEvent(date);
            weekdayEvent(menus, date);
            weekendEvent(menus, date);
            specialEvent(date);
        }
        return result;
    }

    private void christmasEvent(Date date) {
        int discount = 0;
        if (date.isDayBelowStandard(25)) {
            discount = 1000 + 100 * date.getDifferenceBaseDate(1);
        }
        result.put("ChristMas", discount);
    }

    private void weekdayEvent(Menus menus, Date date) {
        int discount = 0;
        if (date.isWeekday()) {
            discount = 2_023 * menus.getDessertCount().intValue();
        }
        result.put("Weekday", discount);
    }

    private void weekendEvent(Menus menus, Date date) {
        int discount = 0;
        if (date.isWeekend()) {
            discount = 2_023 * menus.getMainCount().intValue();
        }
        result.put("Weekend", discount);
    }

    private void specialEvent(Date date) {
        int discount = 0;
        if (date.isSpecialDay()) {
            discount = 1000;
        }
        result.put("Special", discount);
    }
}
