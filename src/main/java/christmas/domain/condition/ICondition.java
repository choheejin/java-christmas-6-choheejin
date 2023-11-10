package christmas.domain.condition;

import christmas.domain.human.Human;
import christmas.domain.human.Menus;

public interface ICondition {
    default boolean IsMeetsMenuConditions(Menus menus) {
        return !menus.isOnlyBeverage();
    }

    default boolean isMeetsMenuCountConditions(Menus menus) {
        return !menus.isMenuCountExceedStandard(20);
    }

    default boolean isAvailableToParticipateEvent(Human human) {
        return human.isMoneyExceedStandard(10_000);
    }
}
