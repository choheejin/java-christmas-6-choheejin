package christmas.domain.human;

import christmas.domain.menu.Menu;
import christmas.view.consts.ErrorMessage;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Orders {
    private static final int MAXIMUM = 20;
    private final Map<String, Integer> orders;

    public Orders(Map<String, Integer> orders) {
        validateOrders(orders);
        this.orders = orders;
    }

    private void validateOrders(Map<String, Integer> orders) throws IllegalArgumentException {
        if (isOnlyBeverage(orders)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_VALIDATE_MENU.getMessage());
        }

        if (isMenuCountExceedStandard(orders, MAXIMUM)) {
            throw new IllegalArgumentException(ErrorMessage.NOT_VALIDATE_MENU.getMessage());
        }
    }

    private boolean isMenuCountExceedStandard(Map<String, Integer> orders, int std) {
        return orders.values().stream().mapToInt(Integer::intValue).sum() > std;
    }

    private boolean isOnlyBeverage(Map<String, Integer> orders) {
        int orderCount = orders.size();
        Long beverageCount = orders.entrySet().stream().filter(menu -> Menu.isCategoryMatch(menu.getKey(), "음료")).count();

        return beverageCount.intValue() == orderCount;
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

    public Map<String, Integer> getOrders() {
        return Collections.unmodifiableMap(orders);
    }
}
