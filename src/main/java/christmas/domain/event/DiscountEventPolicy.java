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
                .filter(entry -> !entry.getKey().equals("증정 이벤트"))
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
        if (date.isDayBelowStandard(25)) {
            discount = 1000 + 100 * date.getDifferenceBaseDate(1);
        }
        result.put("크리스마스 디데이 할인", discount);
    }

    private void weekdayEvent() {
        int discount = 0;
        if (date.isWeekday()) {
            discount = 2_023 * menus.getDessertCount();
        }
        result.put("평일 할인", discount);
    }

    private void weekendEvent() {
        int discount = 0;
        if (date.isWeekend()) {
            discount = 2_023 * menus.getMainCount();
        }
        result.put("주말 할인", discount);
    }

    private void specialEvent() {
        int discount = 0;
        if (date.isSpecialDay()) {
            discount = 1000;
        }
        result.put("특별 할인", discount);
    }

    private void giftEvent() {
        if (!gift.isGiftNone()) {
            result.put("증정 이벤트", gift.getGiftDiscount());
        }
    }
}
