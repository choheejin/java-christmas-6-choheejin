package christmas.view;

import christmas.domain.human.Menus;
import christmas.domain.human.Money;
import christmas.view.consts.OutputDetailMessage;
import christmas.view.consts.OutputTittleMessage;

import java.text.DecimalFormat;

public class OutputView {
    private static final String AMOUNT_NOTATION = "###,###";

    public void displayMenu(Menus menus) {
        System.out.println(OutputTittleMessage.MENU.getMessage());
        menus.getMenus()
                .forEach((menu, count) ->
                        System.out.printf((OutputDetailMessage.MENU.getMessage())+System.lineSeparator(), menu, count)
                );
    }

    public void displayMoney(Money money) {
        DecimalFormat formatter = new DecimalFormat(AMOUNT_NOTATION);
        System.out.println(OutputTittleMessage.MONEY.getMessage());
        System.out.printf(OutputDetailMessage.MONEY.getMessage() + System.lineSeparator(), formatter.format(money.getFee()));
    }
}
