package christmas.domain.event;

import christmas.domain.human.Money;
import christmas.domain.menu.Menu;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GiftEventPolicy {
    private final Money money;
    private Map<Menu, Integer> result;

    public GiftEventPolicy(Money money) {
        this.money = money;
        this.result = new HashMap<>();
        setGiftResult();
    }

    public boolean isGiftNone() {
        Long filterResult = result.values().stream().filter(count -> count != 0).count();
        return filterResult.intValue() == 0;
    }

    public int getGiftDiscount() {
        return result.entrySet()
                .stream()
                .map(entry -> entry.getKey().getPrize() * entry.getValue().intValue())
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<Menu, Integer> getGiftResult() {
        return Collections.unmodifiableMap(result);
    }

    private void setGiftResult() {
        int giftCount = 0;
        if (money.isMoneyExceedStandard(120_000)) {
            giftCount = 1;
        }
        result.put(Menu.CHAMPAGNE, giftCount);
    }
}
