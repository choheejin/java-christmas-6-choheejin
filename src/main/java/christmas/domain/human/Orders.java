package christmas.domain.human;

import christmas.domain.menu.Menu;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Orders {
    private final Map<String, Integer> orders;

    public Orders(Map<String, Integer> orders) {
        this.orders = orders;
    }

    public int getTotalOrderAmount() {
        List<Integer> prizes = orders.entrySet().stream().map(menu -> Menu.getMenusPrize(menu.getKey()) * menu.getValue()).toList();
        return prizes.stream().mapToInt(Integer::intValue).sum();
    }

    public int getMainCount() {
        return orders
                .entrySet()
                .stream()
                .filter(menu -> Menu.isCategoryMatch(menu.getKey(), "메인"))
                .map(Map.Entry::getValue)
                .mapToInt(Integer::intValue).sum();
    }

    public int getDessertCount() {
        return orders
                .entrySet()
                .stream()
                .filter(menu -> Menu.isCategoryMatch(menu.getKey(), "디저트"))
                .map(Map.Entry::getValue)
                .mapToInt(Integer::intValue).sum();
    }

    public boolean isMenuCountExceedStandard(int std) {
        return orders.size() > std;
    }

    public boolean isOnlyBeverage() {
        int orderCount = orders.size();
        Long beverageCount = orders.entrySet().stream().filter(menu -> Menu.isCategoryMatch(menu.getKey(), "음료")).count();

        return beverageCount.intValue() == orderCount;
    }

    public Map<String, Integer> getOrders() {
        return Collections.unmodifiableMap(orders);
    }
}
