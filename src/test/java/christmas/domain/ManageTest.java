package christmas.domain;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

public class ManageTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Override
    protected void runMain() {
        Manager manager = new Manager();
        manager.operate();
    }

    @DisplayName("주문 메뉴 출력")
    @Test
    void mange_주문메뉴() {
        String actualTitle = "<주문 메뉴>";
        String actualMenu = """
                타파스 1개
                제로콜라 1개
                """;
        assertSimpleTest(() -> {
            run("3", "타파스-1,제로콜라-1");
            assertThat(output()).contains(actualTitle + LINE_SEPARATOR + actualMenu);
        });
    }
}
