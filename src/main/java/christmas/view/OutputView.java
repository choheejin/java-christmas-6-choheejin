package christmas.view;

import christmas.domain.human.Menus;
import christmas.domain.human.Money;
import christmas.view.consts.OutputMessage;

import java.text.DecimalFormat;

public class OutputView {
    public void displayMenu(Menus menus) {
        System.out.println(OutputMessage.MENU_TITTLE.getMessage());
        menus.getMenus()
                .forEach((menu, count) ->
                        System.out.printf((OutputMessage.MENU_DETAIL.getMessage())+System.lineSeparator(), menu, count)
                );
    }

    public void displayMoney(Money money) {
        DecimalFormat formatter = new DecimalFormat("###,###");
        System.out.println(OutputMessage.MONEY_TITTLE.getMessage());
        System.out.printf(OutputMessage.MONEY_DETAIL.getMessage() + System.lineSeparator(), formatter.format(money.getFee()));
    }
}
