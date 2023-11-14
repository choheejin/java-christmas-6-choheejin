package christmas.domain.event;

import christmas.domain.human.Date;
import christmas.domain.human.Money;
import christmas.domain.human.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DiscountEventTest {
    @DisplayName("할인 금액의 합계를 계산한다 - 크리스마스 디데이 할인")
    @Test
    void discount_할인금액_합계_크리스마스() {
        Orders orders = ordersWithoutDessert();
        Money money = new Money(orders.getTotalOrderAmount());
        Date date = new Date(4);

        DiscountEvent discountEvent = new DiscountEvent(orders, money, date);

        int actualDiscount = 1_300;
        assertThat(discountEvent.getTotalDiscount()).isEqualTo(actualDiscount);
    }

    @DisplayName("할인 금액의 합계를 계산한다 - 평일 할인 적용")
    @Test
    void discount_할인금액_합계_평일() {
        Orders orders = orders();
        Money money = new Money(orders.getTotalOrderAmount());
        Date date = new Date(26);

        DiscountEvent discountEvent = new DiscountEvent(orders, money, date);

        int actualDiscount = 4_046;
        assertThat(discountEvent.getTotalDiscount()).isEqualTo(actualDiscount);
    }

    @DisplayName("할인 금액의 합계를 계산한다 - 주말 할인 적용")
    @Test
    void discount_할인금액_합계_주말() {
        Orders orders = orders();
        Money money = new Money(orders.getTotalOrderAmount());
        Date date = new Date(29);

        DiscountEvent discountEvent = new DiscountEvent(orders, money, date);

        int actualDiscount = 2_023;
        assertThat(discountEvent.getTotalDiscount()).isEqualTo(actualDiscount);
    }

    @DisplayName("할인 금액의 합계를 계산한다 - 특별 할인")
    @Test
    void discount_할인금액_합계_특별() {
        Orders orders = ordersWithoutDessert();
        Money money = new Money(orders.getTotalOrderAmount());
        Date date = new Date(31);

        DiscountEvent discountEvent = new DiscountEvent(orders, money, date);

        int actualDiscount = 1_000;
        assertThat(discountEvent.getTotalDiscount()).isEqualTo(actualDiscount);
    }

    @DisplayName("할인 금액의 합계를 계산한다 - 할인 전부 적용 (평일)")
    @Test
    void discount_할인금액_합계_전부_평일() {
        Orders orders = orders();
        Money money = new Money(orders.getTotalOrderAmount());
        Date date = new Date(25);

        DiscountEvent discountEvent = new DiscountEvent(orders, money, date);

        int actualDiscount = 3_400 + 4_046 + 1_000;
        assertThat(discountEvent.getTotalDiscount()).isEqualTo(actualDiscount);
    }

    @DisplayName("할인 금액의 합계를 계산한다 - 할인 전부 적용 (주말)")
    @Test
    void discount_할인금액_합계_전부_주말() {
        Orders orders = orders();
        Money money = new Money(orders.getTotalOrderAmount());
        Date date = new Date(23);

        DiscountEvent discountEvent = new DiscountEvent(orders, money, date);

        int actualDiscount = 2_023 + 3_200;
        assertThat(discountEvent.getTotalDiscount()).isEqualTo(actualDiscount);
    }

    @DisplayName("할인 금액의 합계가 0원이다.")
    @Test
    void discount_할인금액_0원() {
        Orders orders = ordersWithoutDessert();
        Money money = new Money(orders.getTotalOrderAmount());
        Date date = new Date(26);

        DiscountEvent discountEvent = new DiscountEvent(orders, money, date);
        assertThat(discountEvent.isDiscountAllNone()).isTrue();
    }

    static Orders orders() {
        return new Orders(
                Map.of(
                        "타파스", 2,
                        "아이스크림", 2,
                        "바비큐립", 1
                )
        );
    }

    static Orders ordersWithoutDessert() {
        return new Orders(
                Map.of(
                        "타파스", 2,
                        "바비큐립", 1
                )
        );
    }
}
