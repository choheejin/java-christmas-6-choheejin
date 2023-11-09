package christmas.domain.event;

import christmas.domain.human.Human;

public class GiftEventPolicy implements IEventPolicy {
    @Override
    public int getGiftCount(Human human) {
        int giftCount = 0;
        if (human.isMoneyExceedStandard(120_000)) {
            giftCount = 1;
        }
        return giftCount;
    }
}
