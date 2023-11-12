package christmas.domain.human;

import christmas.domain.menu.Menu;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Menus {
    private final Map<String, Integer> menus;

    public Menus(Map<String, Integer> menus) {
        this.menus = menus;
    }

    public int getTotalOrderAmount() {
        List<Integer> prizes = menus.entrySet().stream().map(menu -> Menu.getMenusPrize(menu.getKey()) * menu.getValue()).toList();
        return prizes.stream().mapToInt(Integer::intValue).sum();
    }

    public int getMainCount() {
        return menus
                .entrySet()
                .stream()
                .filter(menu -> Menu.isCategoryMatch(menu.getKey(), "메인"))
                .map(Map.Entry::getValue)
                .mapToInt(Integer::intValue).sum();
    }

    public int getDessertCount() {
        return menus
                .entrySet()
                .stream()
                .filter(menu -> Menu.isCategoryMatch(menu.getKey(), "디저트"))
                .map(Map.Entry::getValue)
                .mapToInt(Integer::intValue).sum();
    }

    public boolean isMenuCountExceedStandard(int std) {
        return menus.size() > std;
    }

    public boolean isOnlyBeverage() {
        int menusSize = menus.size();
        Long beverageCount = menus.entrySet().stream().filter(menu -> Menu.isCategoryMatch(menu.getKey(), "음료")).count();

        return beverageCount.intValue() == menusSize;
    }

    public Map<String, Integer> getMenus() {
        return Collections.unmodifiableMap(menus);
    }
}
