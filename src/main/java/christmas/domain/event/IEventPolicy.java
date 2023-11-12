package christmas.domain.event;

import christmas.domain.human.Date;
import christmas.domain.human.Menus;
import christmas.domain.human.Money;

import java.util.HashMap;
import java.util.Map;

public interface IEventPolicy {
    default Map<String, Integer> getDiscountAmount(Menus menus, Money money, Date date) {
        return new HashMap<>();
    }

    default int getGiftCount(Money money) {
        return 0;
    }
}
