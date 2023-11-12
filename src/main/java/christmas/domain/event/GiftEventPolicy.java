package christmas.domain.event;

import christmas.domain.human.Money;

public class GiftEventPolicy implements IEventPolicy {
    @Override
    public int getGiftCount(Money money) {
        int giftCount = 0;
        if (money.isMoneyExceedStandard(120_000)) {
            giftCount = 1;
        }
        return giftCount;
    }
}
