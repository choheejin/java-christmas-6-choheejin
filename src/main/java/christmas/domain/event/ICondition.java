package christmas.domain.event;

import christmas.domain.human.Money;

public interface ICondition {
    static final int MINIMUM = 10_000;
    default boolean isAvailableToParticipateEvent(Money money) {
        return money.isMoneyExceedStandard(MINIMUM);
    }
}
