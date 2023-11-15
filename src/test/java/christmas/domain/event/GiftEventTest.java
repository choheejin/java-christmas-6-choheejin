package christmas.domain.event;

import christmas.domain.human.Money;
import christmas.domain.human.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GiftEventTest {
    @DisplayName("증정이벤트에 해당된 증정 메뉴의 가격을 가져온다")
    @Test
    void gift_증정메뉴_가격() {
        Orders orders = orders();
        Money money = new Money(orders.getTotalOrderAmount());

        GiftEvent giftEvent = new GiftEvent(money);

        int actualDiscount = 25_000;

        assertThat(giftEvent.getGiftDiscount()).isEqualTo(actualDiscount);
    }

    @DisplayName("증정이벤트에 해당되지 않는다")
    @Test
    void gift_증정이벤트_안됨() {
        Orders orders = ordersNotMeetCondition();
        Money money = new Money(orders.getTotalOrderAmount());

        GiftEvent giftEvent = new GiftEvent(money);

        assertThat(giftEvent.isGiftNone()).isTrue();
    }

    static Orders orders() {
        return new Orders(
                Map.of(
                        "타파스", 2,
                        "레드와인", 1,
                        "아이스크림", 2,
                        "초코케이크", 2,
                        "크리스마스파스타", 2,
                        "바비큐립", 1
                )
        );
    }

    static Orders ordersNotMeetCondition() {
        return new Orders(
                Map.of(
                        "타파스", 2,
                        "레드와인", 1
                )
        );
    }
}
