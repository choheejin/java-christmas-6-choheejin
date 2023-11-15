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

    @DisplayName("시작 문구 출력")
    @Test
    void manage_시작문구() {
        String actualTitle = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
        assertSimpleTest(() -> {
            run("3", "타파스-1,제로콜라-1");
            assertThat(output()).contains(actualTitle);
        });
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

    @DisplayName("주문 날짜에 맞춰 이벤트 혜택 안내 문구 출력")
    @Test
    void manage_이벤트_혜택_안내() {
        String actualTitle = "12월 15일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
        assertSimpleTest(() -> {
            run("15", "타파스-1,제로콜라-1");
            assertThat(output()).contains(actualTitle);
        });
    }

    @DisplayName("할인 전 총주문 금액 출력")
    @Test
    void manage_할인전_총주문_금액() {
        String actualTitle = "<할인 전 총주문 금액>";
        String actualMoney = """
                68,000원
                """;
        assertSimpleTest(() -> {
            run("3", "시저샐러드-1,바비큐립-1,제로콜라-2");
            assertThat(output()).contains(actualTitle + LINE_SEPARATOR + actualMoney);
        });
    }

    @DisplayName("증정이벤트 해당되지 않을 때, 증정메뉴 출력 - 없음")
    @Test
    void manage_증정이벤트_없음() {
        String actualTitle = "<증정 메뉴>";
        String actualGift = """
                없음
                """;
        assertSimpleTest(() -> {
            run("3", "시저샐러드-1,바비큐립-1,제로콜라-2");
            assertThat(output()).contains(actualTitle + LINE_SEPARATOR + actualGift);
        });
    }

    @DisplayName("증정이벤트 해당될 때, 증정메뉴 출력 - 샴페인")
    @Test
    void manage_증정이벤트_있음() {
        String actualTitle = "<증정 메뉴>";
        String actualGift = """
                샴페인 1개
                """;
        assertSimpleTest(() -> {
            run("3", "시저샐러드-1,바비큐립-2,제로콜라-2");
            assertThat(output()).contains(actualTitle + LINE_SEPARATOR + actualGift);
        });
    }

    @DisplayName("혜택 내역 없을 때, 헤택 내역 출력 - 없음")
    @Test
    void manage_혜택내역_없음() {
        String actualTitle = "<혜택 내역>";
        String actualBenefitReceipt = """
                없음
                """;
        assertSimpleTest(() -> {
            run("3", "시저샐러드-1");
            assertThat(output()).contains(actualTitle + LINE_SEPARATOR + actualBenefitReceipt);
        });
    }

    @DisplayName("혜택 내역 존재할 때, 헤택 내역 출력 - 해당되는 혜택만 출력")
    @Test
    void manage_혜택내역_있음() {
        String actualTitle = "<혜택 내역>";
        String christmas = "크리스마스 디데이 할인: -1,200원";
        String special = "특별 할인: -1,000원";
        String gift = "증정 이벤트: -25,000원";
        assertSimpleTest(() -> {
            run("3", "시저샐러드-1,바비큐립-2,제로콜라-2");
            assertThat(output()).contains(actualTitle, christmas, special, gift);
        });
    }

    @DisplayName("총혜택 금액 없을 때, 총혜택 금액 출력 - 없음")
    @Test
    void manage_총혜택금액_없음() {
        String actualTitle = "<총혜택 금액>";
        String actualBenefitReceipt = """
                없음
                """;
        assertSimpleTest(() -> {
            run("3", "시저샐러드-1");
            assertThat(output()).contains(actualTitle + LINE_SEPARATOR + actualBenefitReceipt);
        });
    }

    @DisplayName("총혜택 금액 있을 때, 총혜택 금액 출력")
    @Test
    void manage_총혜택금액_있음() {
        String actualTitle = "<총혜택 금액>";
        String actualBenefitAmount = """
                -2,200원
                """;
        assertSimpleTest(() -> {
            run("3", "시저샐러드-1,바비큐립-2");
            assertThat(output()).contains(actualTitle + LINE_SEPARATOR + actualBenefitAmount);
        });
    }

    @DisplayName("할인 후 예상 결제 금액 출력")
    @Test
    void manage_예상결제금액() {
        String actualTitle = "<할인 후 예상 결제 금액>";
        String actualRealFee = """
                113,800원
                """;
        assertSimpleTest(() -> {
            run("3", "시저샐러드-1,바비큐립-2");
            assertThat(output()).contains(actualTitle + LINE_SEPARATOR + actualRealFee);
        });
    }

    @DisplayName("이벤트 배지 없을 때, 이벤트 배지 출력 - 없음")
    @Test
    void manage_이벤트배지_없음() {
        String actualTitle = "<12월 이벤트 배지>";
        String actualBadge = """
                없음""";
        assertSimpleTest(() -> {
            run("3", "시저샐러드-1");
            assertThat(output()).contains(actualTitle + LINE_SEPARATOR + actualBadge);
        });
    }

    @DisplayName("이벤트 배지 존재할 때, 이벤트 배지 출력")
    @Test
    void manage_이벤트배지_있음() {
        String actualTitle = "<12월 이벤트 배지>";
        String actualBadge = """
                산타""";
        assertSimpleTest(() -> {
            run("3", "시저샐러드-1,바비큐립-2,제로콜라-2");
            assertThat(output()).contains(actualTitle + LINE_SEPARATOR + actualBadge);
        });
    }
}
