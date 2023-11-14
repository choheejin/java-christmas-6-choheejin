package christmas.domain.human;

import christmas.view.consts.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DateTest {
    @DisplayName("날짜가 1~31 사이의 숫자가 아닐 경우, 예외 처리")
    @ParameterizedTest
    @ValueSource(ints = {0, 40, -1})
    void date_날짜_1_31_사이의_수(int input) {
        assertThatThrownBy(() -> new Date(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.NOT_VALIDATE_DATE.getMessage());
    }

    @DisplayName("입력된 날짜가 평일이다")
    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, 6, 7, 10, 11, 12, 13, 14, 17, 18, 19, 20, 21, 24, 25, 26, 27, 28, 31})
    void date_날짜_평일(int input) {
        assertThat(new Date(input).isWeekday()).isTrue();
    }

    @DisplayName("입력된 날짜가 주말이다")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    void date_날짜_주말(int input) {
        assertThat(new Date(input).isWeekend()).isTrue();
    }

    @DisplayName("입력된 날짜가 특별 할인을 하는 날이다")
    @ParameterizedTest
    @ValueSource(ints = {3, 10, 17, 24, 25, 31})
    void date_특별_이벤트_날(int input) {
        assertThat(new Date(input).isSpecialDay()).isTrue();
    }

    @DisplayName("입력된 날짜가 특정 날짜 보다 뒤의 날짜이다")
    @ParameterizedTest
    @ValueSource(ints = {26, 27, 28, 29, 30, 31})
    void date_특정날짜_기준(int input) {
        int christMas = 25;
        assertThat(new Date(input).isDayBelowStandard(christMas)).isFalse();
    }

    @DisplayName("입력된 날짜와 특정 날짜와의 차이 계산")
    @Test
    void date_날짜_차이_계산() {
        int startDate = 1;
        int input = 25;
        assertThat(new Date(input).getDifferenceBaseDate(startDate)).isEqualTo(24);
    }
}
