package christmas.domain.condition;

import christmas.domain.human.Menus;
import christmas.domain.human.Money;

public interface ICondition {
    default boolean IsMeetsMenuConditions(Menus menus) {
        return !menus.isOnlyBeverage();
    }

    default boolean isMeetsMenuCountConditions(Menus menus) {
        return !menus.isMenuCountExceedStandard(20);
    }

    default boolean isAvailableToParticipateEvent(Money money) {
        return money.isMoneyExceedStandard(10_000);
    }
}
