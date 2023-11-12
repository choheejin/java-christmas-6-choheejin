package christmas.view;

import christmas.domain.human.Menus;
import christmas.view.consts.OutputMessage;

public class OutputView {
    public void displayMenu(Menus menus) {
        System.out.println(OutputMessage.DISPLAY_MENU_TITTLE.getMessage());
        menus.getMenus()
                .forEach((menu, count) ->
                        System.out.printf((OutputMessage.DISPLAY_MENU_DETAIL.getMessage())+System.lineSeparator(), menu, count)
                );
    }
}
