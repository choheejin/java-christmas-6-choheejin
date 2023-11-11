package christmas.domain.human;

import christmas.domain.menu.Menu;

import java.util.List;
import java.util.Map;

public class Menus {
    private final Map<String, Integer> menus;

    public Menus(Map<String, Integer> menus) {
        this.menus = menus;
    }

    public Long getMainCount() {
        return menus.entrySet().stream().filter(menu -> Menu.isCategoryMatch(menu.getKey(), "메인")).count();
    }

    public Long getDessertCount() {
        return menus.entrySet().stream().filter(menu -> Menu.isCategoryMatch(menu.getKey(), "디저트")).count();
    }

    public boolean isMenuCountExceedStandard(int std) {
        return menus.size() > std;
    }

    public boolean isOnlyBeverage() {
        int menusSize = menus.size();
        Long beverageCount = menus.entrySet().stream().filter(menu -> Menu.isCategoryMatch(menu.getKey(), "음료")).count();

        return beverageCount.intValue() == menusSize;
    }
}
