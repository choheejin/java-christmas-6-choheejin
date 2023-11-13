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
    private final GiftEventPolicy gift;
    private Map<String, Integer> result;

    public DiscountEventPolicy(Menus menus, Money money, Date date) {
        this.menus = menus;
        this.money = money;
        this.date = date;

        this.gift = new GiftEventPolicy(money);
        this.result = new HashMap<>();

        setDiscountAmount();
    }

    public int getTotalBenefit() {
        return result
                .entrySet()
                .stream()
                .filter(entry -> !entry.getKey().equals(Event.GIFT.getName()))
                .map(Map.Entry::getValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getTotalDiscount() {
        return result.values().stream().mapToInt(Integer::intValue).sum();
    }

    public boolean isTotalDiscountExceedStandard(int std) {
        int totalDiscount = getTotalDiscount();
        return totalDiscount >= std;
    }

    public boolean isDiscountAllNone() {
        Long filterResult = result.values().stream().filter(count -> count != 0).count();
        return filterResult.intValue() == 0;
    }

    public Map<String, Integer> getDiscountReceipt() {
        return result.entrySet().stream().filter(entry -> entry.getValue() != 0).collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void setDiscountAmount() {
        if (isAvailableToParticipateEvent(money)) {
            christmasEvent();
            weekdayEvent();
            weekendEvent();
            specialEvent();
            giftEvent();
        }
    }

    private void christmasEvent() {
        int discount = 0;
        if (date.isDayBelowStandard(END_DATE)) {
            discount = Event.getChristmasDiscount(date.getDifferenceBaseDate(START_DATE));
        }
        result.put(Event.CHRISTMAS.getName(), discount);
    }

    private void weekdayEvent() {
        int discount = 0;
        if (date.isWeekday()) {
            discount = Event.getWeekDayDiscount(menus.getDessertCount());
        }
        result.put(Event.WEEKDAY.getName(), discount);
    }

    private void weekendEvent() {
        int discount = 0;
        if (date.isWeekend()) {
            discount = Event.getWeekEndDiscount(menus.getMainCount());
        }
        result.put(Event.WEEKEND.getName(), discount);
    }

    private void specialEvent() {
        int discount = 0;
        if (date.isSpecialDay()) {
            discount = Event.getSpecialDiscount();
        }
        result.put(Event.SPECIAL.getName(), discount);
    }

    private void giftEvent() {
        if (!gift.isGiftNone()) {
            result.put(Event.GIFT.getName(), gift.getGiftDiscount());
        }
    }
}
