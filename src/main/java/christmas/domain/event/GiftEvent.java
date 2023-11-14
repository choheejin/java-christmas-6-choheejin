package christmas.domain.event;

import christmas.domain.human.Money;
import christmas.domain.menu.Menu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class GiftEvent {
    private static final int MINIMUM = 120_000;
    private final Money money;
    private Map<Menu, Integer> result;

    public GiftEvent(Money money) {
        this.money = money;
        this.result = new HashMap<>();
        setGiftChampagne();
    }

    public boolean isGiftNone() {
        Long filterResult = result.values().stream().filter(count -> count != 0).count();
        return filterResult.intValue() == 0;
    }

    public int getGiftDiscount() {
        return result.entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrize() * entry.getValue())
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<Event, Integer> getGiftReceipt() {
        Map<Event, Integer> giftEvent = getGiftEvent();
        return giftEvent.entrySet().stream().filter(entry -> entry.getValue() != 0).collect(Collectors.toUnmodifiableMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private void setGiftChampagne() {
        int giftCount = 0;
        if (money.isMoneyExceedStandard(MINIMUM)) {
            giftCount = 1;
        }
        result.put(Menu.CHAMPAGNE, giftCount);
    }

    private Map<Event, Integer> getGiftEvent() {
        Map<Event, Integer> giftEvent = new HashMap<>();
        int discount = 0;
        if (!isGiftNone()) {
            discount = getGiftDiscount();
        }
        giftEvent.put(Event.GIFT, discount);
        return giftEvent;
    }

    public Map<Menu, Integer> getGiftResult() {
        return Collections.unmodifiableMap(result);
    }
}
