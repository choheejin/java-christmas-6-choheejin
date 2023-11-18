package christmas.domain.event;

import christmas.domain.human.Date;
import christmas.domain.human.Money;
import christmas.domain.human.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BenefitTest {
    @DisplayName("총 혜택 금액이 0원이다")
    @Test
    void benefit_총혜택금액_0원() {
        Benefit benefit = noBenefit();
        assertThat(benefit.isNoneOfBenefit()).isTrue();
    }

    @DisplayName("총 혜택 금액을 계산한다")
    @Test
    void benefit_총혜택금액_계산() {
        Benefit benefit = benefit();
        int actualBenefit = 6_069 + 25_000;
        assertThat(benefit.getTotalBenefit()).isEqualTo(actualBenefit);
    }

    static Benefit benefit() {
        Orders orders = new Orders(
                Map.of(
                        "타파스", 2,
                        "레드와인", 1,
                        "아이스크림", 2,
                        "초코케이크", 2,
                        "크리스마스파스타", 2,
                        "바비큐립", 1
                )
        );
        Money money = new Money(orders.getTotalOrderAmount());
        Date date = new Date(29);

        DiscountEvent discountEvent = new DiscountEvent(orders, money, date);
        GiftEvent giftEvent = new GiftEvent(money);

        return new Benefit(discountEvent, giftEvent);
    }

    static Benefit noBenefit() {
        Orders orders = new Orders(
                Map.of(
                        "타파스", 2,
                        "레드와인", 1,
                        "아이스크림", 2
                )
        );
        Money money = new Money(orders.getTotalOrderAmount());
        Date date = new Date(29);

        DiscountEvent discountEvent = new DiscountEvent(orders, money, date);
        GiftEvent giftEvent = new GiftEvent(money);

        return new Benefit(discountEvent, giftEvent);
    }

}
