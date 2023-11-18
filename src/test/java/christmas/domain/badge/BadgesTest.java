package christmas.domain.badge;

import christmas.domain.human.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BadgesTest {
    @DisplayName("총 혜택 금액이 2만원 이상이다 - 산타")
    @Test
    void badge_산타() {
        Money money = new Money(50_000);
        assertThat(Badges.badgeMeetingConditions(money)).isEqualByComparingTo(Badges.SANTA);
    }

    @DisplayName("총 혜택 금액이 2만원 미만, 1만원 이상이다 - 트리")
    @Test
    void badge_트리() {
        Money money = new Money(12_000);
        assertThat(Badges.badgeMeetingConditions(money)).isEqualByComparingTo(Badges.TREE);
    }

    @DisplayName("총 혜택 금액이 1만원 미만, 5천원 이상이다 - 별")
    @Test
    void badge_별() {
        Money money = new Money(6_000);
        assertThat(Badges.badgeMeetingConditions(money)).isEqualByComparingTo(Badges.STAR);
    }

    @DisplayName("총 혜택 금액이 5천원 미만이다 - 없음")
    @Test
    void badge_없음() {
        Money money = new Money(1_000);
        assertThat(Badges.badgeMeetingConditions(money)).isEqualByComparingTo(Badges.NONE);
    }
}
