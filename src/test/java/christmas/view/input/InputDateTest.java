package christmas.view.input;

import camp.nextstep.edu.missionutils.test.NsTest;
import christmas.view.InputView;
import christmas.view.consts.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

public class InputDateTest extends NsTest {
    @Override
    protected void runMain() {
        InputView inputView = new InputView();
        inputView.readDate();
    }

    @DisplayName("방문할 날짜를 숫자가 아닌 값을 입력할 때, 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {"일", "one"})
    void input_방문날짜_숫자아님(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains(ErrorMessage.NOT_VALIDATE_DATE.getMessage());
        });
    }

    @DisplayName("방문할 날짜를 입력하지 않았을 때, 예외 처리")
    @ParameterizedTest
    @ValueSource(strings = {" ", "\n"})
    void input_방문날짜_공백(String input) {
        assertSimpleTest(() -> {
            runException(input);
            assertThat(output()).contains(ErrorMessage.NOT_VALIDATE_DATE.getMessage());
        });
    }
}
