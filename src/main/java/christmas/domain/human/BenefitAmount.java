package christmas.domain.human;

import java.util.Collection;
import java.util.Map;

public class BenefitAmount {
    private final int totalDiscount;

    public BenefitAmount(Map<String, Integer> discountResult) {
        this.totalDiscount = setTotalDiscount(discountResult);
    }

    private int setTotalDiscount(Map<String, Integer> discountResult) {
        return discountResult.values().stream().mapToInt(Integer::intValue).sum();
    }

    public boolean isTotalDiscountExceedStandard(int std) {
        return totalDiscount >= std;
    }
}
