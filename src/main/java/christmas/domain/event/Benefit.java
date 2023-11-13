package christmas.domain.event;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Benefit {
    private final DiscountEventPolicy discountEventPolicy;
    private final GiftEventPolicy giftEventPolicy;

    public Benefit(DiscountEventPolicy discountEventPolicy, GiftEventPolicy giftEventPolicy) {
        this.discountEventPolicy = discountEventPolicy;
        this.giftEventPolicy = giftEventPolicy;
    }

    public boolean isNoneOfBenefit() {
        return discountEventPolicy.isDiscountAllNone() && giftEventPolicy.isGiftNone();
    }

    public int getTotalBenefit() {
        int discountEvent = discountEventPolicy.getTotalDiscount();
        int giftEvent = giftEventPolicy.getGiftDiscount();
        return discountEvent + giftEvent;
    }

    public boolean isTotalBenefitExceedStandard(int std) {
        int totalDiscount = getTotalBenefit();
        return totalDiscount >= std;
    }

    public Map<Event, Integer> getBenefitReceipt() {
        Map<Event, Integer> discountReceipt = discountEventPolicy.getDiscountReceipt();
        Map<Event, Integer> giftReceipt = giftEventPolicy.getGiftReceipt();
        Map<Event, Integer> benefitReceipt = new HashMap<>();
        benefitReceipt.putAll(discountReceipt);
        benefitReceipt.putAll(giftReceipt);
        return Collections.unmodifiableMap(benefitReceipt);
    }
}
