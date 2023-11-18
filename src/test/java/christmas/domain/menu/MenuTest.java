package christmas.domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTest {
    @DisplayName("입력된 값이 메뉴판에 존재하는지 확인한다")
    @ParameterizedTest
    @ValueSource(strings = {
            "양송이수프", "타파스", "시저샐러드", "티본스테이크", "바비큐립", "해산물파스타",
            "크리스마스파스타", "초코케이크", "아이스크림", "제로콜라", "레드와인", "샴페인"
    })
    void menu_메뉴이름_존재여부(String menu) {
        assertThat(Menu.isContainMenu(menu)).isTrue();
    }

    @DisplayName("입력된 메뉴의 가격을 가져온다")
    @ParameterizedTest
    @CsvSource({
            "양송이수프, 6_000", "타파스, 5_500", "시저샐러드, 8_000",
            "티본스테이크, 55_000", "바비큐립, 54_000", "해산물파스타, 35_000", "크리스마스파스타, 25_000",
            "초코케이크, 15_000", "아이스크림, 5_000",
            "제로콜라, 3_000", "레드와인, 60_000", "샴페인, 25_000"
    })
    void menu_메뉴의_가격(String menu, int actualPrize) {
        assertThat(Menu.getMenusPrize(menu)).isEqualTo(actualPrize);
    }

    @DisplayName("입력된 메뉴이름과 카테고리가 메뉴판에 기입된 정보와 동일하다")
    @ParameterizedTest
    @CsvSource({
            "양송이수프, 에피타이저", "타파스, 에피타이저", "시저샐러드, 에피타이저",
            "티본스테이크, 메인", "바비큐립, 메인", "해산물파스타, 메인", "크리스마스파스타, 메인",
            "초코케이크, 디저트", "아이스크림, 디저트",
            "제로콜라, 음료", "레드와인, 음료", "샴페인, 음료"
    })
    void menu_메뉴이름_카테고리_메뉴판과_일치(String menu, String category) {
        assertThat(Menu.isCategoryMatch(menu, category)).isTrue();
    }
}
