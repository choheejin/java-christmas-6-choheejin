package christmas.domain.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventTest {
    @DisplayName("크리스마스 디데이 할인을 계산한다")
    @Test
    void event_크라스마스_디데이_할인() {
        int gap = 24;
        int actualDiscount = 3400;
        assertThat(Event.getChristmasDiscount(gap)).isEqualTo(actualDiscount);
    }

    @DisplayName("평일 할인을 계산한다")
    @Test
    void event_평일_할인() {
        int count = 3;
        int actualDiscount = 6_069;
        assertThat(Event.getWeekDayDiscount(count)).isEqualTo(actualDiscount);
    }

    @DisplayName("주말 할인을 계산한다")
    @Test
    void event_주말_할인() {
        int count = 1;
        int actualDiscount = 2_023;
        assertThat(Event.getWeekEndDiscount(count)).isEqualTo(actualDiscount);
    }

    @DisplayName("특별 할인을 계산한다")
    @Test
    void event_특별_할인() {
        int actualDiscount = 1_000;
        assertThat(Event.getSpecialDiscount()).isEqualTo(actualDiscount);
    }
}
