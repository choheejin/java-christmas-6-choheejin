package christmas.domain.human;

import christmas.view.consts.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OrdersTest {
    @DisplayName("음료만 주문 시, 예외 처리")
    @ParameterizedTest
    @MethodSource("onlyBeverage")
    void orders_음료만_주문(Map<String, Integer> input) {
        assertThatThrownBy(() -> new Orders(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_VALIDATE_MENU.getMessage());
    }

    @DisplayName("메뉴 최대 20개 넘어갔을 때, 예외처리")
    @ParameterizedTest
    @MethodSource("moreThanMaximum")
    void orders_최대_주문_넘김(Map<String, Integer> input) {
        assertThatThrownBy(() -> new Orders(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("총 주문 금액 계산")
    @Test
    void orders_총_주문_금액() {
        assertThat(normalOrders().getTotalOrderAmount()).isEqualTo(215_000);
    }

    @DisplayName("메인 요리 수 계산")
    @Test
    void orders_메인_요리_수() {
        assertThat(normalOrders().getMainCount()).isEqualTo(3);
    }

    @DisplayName("디저트 요리 수 계산")
    @Test
    void orders_디저트_요리_수() {
        assertThat(normalOrders().getDessertCount()).isEqualTo(4);
    }


    static Stream<Map<String, Integer>> onlyBeverage() {
        return Stream.of(
                Map.of("제로콜라", 1),
                Map.of("레드와인", 3),
                Map.of(
                        "레드와인", 1,
                        "샴페인", 4
                )
        );
    }

    static Stream<Map<String, Integer>> moreThanMaximum() {
        return Stream.of(
                Map.of(
                        "티본스테이크", 1,
                        "제로콜라", 2,
                        "양송이수프", 20
                ),
                Map.of(
                        "시저샐러드", 19,
                        "제로콜라", 3
                ),
                Map.of(
                        "아이스크림", 1,
                        "샴페인", 20
                )
        );
    }

    static Orders normalOrders() {
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
}
