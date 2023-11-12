package christmas.view;

import christmas.domain.human.Menus;
import christmas.domain.human.Money;
import christmas.domain.menu.Menu;
import christmas.view.consts.OutputDetailMessage;
import christmas.view.consts.OutputTittleMessage;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String AMOUNT_NOTATION = "###,###";

    public void displayMenu(Menus menus) {
        System.out.println(OutputTittleMessage.MENU.getMessage());
        menus.getMenus()
                .forEach((menu, count) ->
                        System.out.printf((OutputDetailMessage.MENU.getMessage()), menu, count)
                );
    }

    public void displayMoney(Money money) {
        DecimalFormat formatter = new DecimalFormat(AMOUNT_NOTATION);
        System.out.println(OutputTittleMessage.MONEY.getMessage());
        System.out.printf(OutputDetailMessage.MONEY.getMessage(), formatter.format(money.getFee()));
    }


    public void displayGift(Map<Menu, Integer> giftCount, boolean isNone) {
        System.out.println(OutputTittleMessage.GIFT.getMessage());
        if (isNone) {
            System.out.printf(OutputDetailMessage.NONE.getMessage());
            return;
        }

        giftCount.forEach((key, value) -> System.out.printf(OutputDetailMessage.GIFT.getMessage(), key.getMenuName(), value));
    }

    public void displayDiscount(Map<String, Integer> discountResult, boolean isNone) {
        DecimalFormat formatter = new DecimalFormat(AMOUNT_NOTATION);
        System.out.println(OutputTittleMessage.DISCOUNT.getMessage());
        if (isNone) {
            System.out.printf(OutputDetailMessage.NONE.getMessage());
            return;
        }

        discountResult.forEach((key, value) -> System.out.printf(OutputDetailMessage.DISCOUNT.getMessage(), key, formatter.format(value)));
    }
}
