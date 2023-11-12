package christmas.domain.event;

import christmas.domain.condition.ICondition;
import christmas.domain.human.Date;
import christmas.domain.human.Menus;
import christmas.domain.human.Money;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DiscountEventPolicy implements ICondition {
    private final Menus menus;
    private final Money money;
    private final Date date;
    private Map<String, Integer> result;

    public DiscountEventPolicy(Menus menus, Money money, Date date) {
        this.menus = menus;
        this.money = money;
        this.date = date;
        this.result = new HashMap<>();
        setDiscountAmount();
    }

    public boolean isDiscountAllNone() {
        Long filterResult = result.values().stream().filter(count -> count != 0).count();
        return filterResult.intValue() == 0;
    }

    public Map<String, Integer> getDiscountReceipt() {
        return result.entrySet().stream().filter(entry -> entry.getValue() != 0).collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map<String, Integer> setDiscountAmount() {
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
        result.put("크리스마스 디데이 할인", discount);
    }

    private void weekdayEvent(Menus menus, Date date) {
        int discount = 0;
        if (date.isWeekday()) {
            discount = 2_023 * menus.getDessertCount().intValue();
        }
        result.put("평일 할인", discount);
    }

    private void weekendEvent(Menus menus, Date date) {
        int discount = 0;
        if (date.isWeekend()) {
            discount = 2_023 * menus.getMainCount().intValue();
        }
        result.put("주말 할인", discount);
    }

    private void specialEvent(Date date) {
        int discount = 0;
        if (date.isSpecialDay()) {
            discount = 1000;
        }
        result.put("특별 할인", discount);
    }
}
