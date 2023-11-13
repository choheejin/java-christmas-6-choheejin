package christmas.domain.event;

import christmas.domain.condition.ICondition;
import christmas.domain.human.Date;
import christmas.domain.human.Menus;
import christmas.domain.human.Money;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DiscountEventPolicy implements ICondition {
    private static final int START_DATE = 1;
    private static final int END_DATE = 25;
    private final Menus menus;
    private final Money money;
    private final Date date;
    private Map<Event, Integer> result;

    public DiscountEventPolicy(Menus menus, Money money, Date date) {
        this.menus = menus;
        this.money = money;
        this.date = date;

        this.result = new HashMap<>();

        setDiscountAmount();
    }

    public int getTotalDiscount() {
        return result.values().stream().mapToInt(Integer::intValue).sum();
    }

    public boolean isDiscountAllNone() {
        Long filterResult = result.values().stream().filter(count -> count != 0).count();
        return filterResult.intValue() == 0;
    }

    public Map<Event, Integer> getDiscountReceipt() {
        return result.entrySet().stream().filter(entry -> entry.getValue() != 0).collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void setDiscountAmount() {
        if (isAvailableToParticipateEvent(money)) {
            christmasEvent();
            weekdayEvent();
            weekendEvent();
            specialEvent();
        }
    }

    private void christmasEvent() {
        int discount = 0;
        if (date.isDayBelowStandard(END_DATE)) {
            discount = Event.getChristmasDiscount(date.getDifferenceBaseDate(START_DATE));
        }
        result.put(Event.CHRISTMAS, discount);
    }

    private void weekdayEvent() {
        int discount = 0;
        if (date.isWeekday()) {
            discount = Event.getWeekDayDiscount(menus.getDessertCount());
        }
        result.put(Event.WEEKDAY, discount);
    }

    private void weekendEvent() {
        int discount = 0;
        if (date.isWeekend()) {
            discount = Event.getWeekEndDiscount(menus.getMainCount());
        }
        result.put(Event.WEEKEND, discount);
    }

    private void specialEvent() {
        int discount = 0;
        if (date.isSpecialDay()) {
            discount = Event.getSpecialDiscount();
        }
        result.put(Event.SPECIAL, discount);
    }
}
