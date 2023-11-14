package christmas.view.input;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.view.InputView;
import christmas.view.consts.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

public class InputOrderTest extends NsTest {
    @Override
    protected void runMain() {
        InputView inputView = new InputView();
        inputView.readOrders();
    }

    @DisplayName("메뉴를 주문할 때 입력 형식을 지키지 않을시, 예외처리 (메뉴-갯수,메뉴-갯수)")
    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드,1", "시저샐러드-1,티본스테이크:1", "시저샐러드-1, 티본스테이크-1", "시저샐러드-1.시저샐러드-2"})
    void input_입력_형식(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains(ErrorMessage.NOT_VALIDATE_MENU.getMessage());
        });
    }

    @DisplayName("없는 메뉴를 주문할 때, 예외처리")
    @ParameterizedTest
    @ValueSource(strings = {"메뉴-1", "바베큐립-3"})
    void input_메뉴_없을때(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains(ErrorMessage.NOT_VALIDATE_MENU.getMessage());
        });
    }

    @DisplayName("메뉴주문에서 숫자를 잘못된 값을 입력할 시, 예외처리")
    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드-0", "티본스테이크-일", "티본스테이크-a"})
    void input_메뉴_갯수(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains(ErrorMessage.NOT_VALIDATE_MENU.getMessage());
        });
    }

    @DisplayName("메뉴주문에서 중복 메뉴 입력시, 예외처리")
    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드-1,시저샐러드-1", "아이스크림-1,시저샐러드-1,아이스크림-4"})
    void input_메뉴_중복(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains(ErrorMessage.NOT_VALIDATE_MENU.getMessage());
        });
    }

    @DisplayName("메뉴주문에서 공백 입력시, 예외처리")
    @ParameterizedTest
    @ValueSource(strings = {" ", "\n"})
    void input_메뉴_공백(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains(ErrorMessage.NOT_VALIDATE_MENU.getMessage());
        });
    }

    @DisplayName("메뉴를 정상적으로 입력한다")
    @ParameterizedTest
    @ValueSource(strings = {"시저샐러드-1", "아이스크림-1"})
    void input_메뉴_입력_정상(String input) {
        assertSimpleTest(() -> {
            run(input);
            assertThat(output()).doesNotContain(ErrorMessage.NOT_VALIDATE_MENU.getMessage());
        });
    }
}
