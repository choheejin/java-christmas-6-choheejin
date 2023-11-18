package christmas.domain.human;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    @DisplayName("돈을 비교한 후, 차액을 반환")
    @Test
    void money_돈_비교() {
        Money money1 = new Money(5_000);
        Money money2 = new Money(4_000);
        assertThat(money1.compareTo(money2)).isEqualTo(1_000);
    }

    @DisplayName("돈이 0원이다")
    @Test
    void money_돈_0원() {
        int money = 0;
        assertThat(new Money(money).isNone()).isTrue();
    }

    @DisplayName("입력된 돈이 특정 금액보디 크거나 같다")
    @Test
    void money_돈_크거나_같다() {
        int money = 121_000;
        int std = 120_000;
        assertThat(new Money(money).isMoneyExceedStandard(std)).isTrue();
    }
}
