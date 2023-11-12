package christmas.domain.condition;

import christmas.domain.human.Menus;
import christmas.domain.human.Money;

import java.util.Map;

public interface ICondition {
    default boolean isMeetsMenuConditions(Map<String, Integer> inputMenu) {
        Menus menus = new Menus(inputMenu);
        return !menus.isOnlyBeverage();
    }

    default boolean isMeetsMenuCountConditions(Map<String, Integer> inputMenu) {
        Menus menus = new Menus(inputMenu);
        return !menus.isMenuCountExceedStandard(20);
    }

    default boolean isAvailableToParticipateEvent(Money money) {
        return money.isMoneyExceedStandard(10_000);
    }
}
