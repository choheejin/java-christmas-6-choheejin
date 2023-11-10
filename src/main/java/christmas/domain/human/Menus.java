package christmas.domain.human;

import christmas.domain.menu.Menu;

import java.util.List;

public class Menus {
    private final List<Menu> menus;

    public Menus(List<Menu> menus) {
        this.menus = menus;
    }

    public Long getMainCount() {
        return menus.stream().filter(menu -> menu.getCategory().equals("메인")).count();
    }

    public Long getDessertCount() {
        return menus.stream().filter(menu -> menu.getCategory().equals("디저트")).count();
    }

    public boolean isMenuCountExceedStandard(int std) {
        return menus.size() > std;
    }

    public boolean isOnlyBeverage() {
        int menusSize = menus.size();
        Long beverageCount = menus.stream().filter(menu -> menu.getCategory().equals("음료")).count();

        return beverageCount.intValue() == menusSize;
    }
}
