package christmas.view;

import christmas.domain.badge.Badges;
import christmas.domain.event.Event;
import christmas.domain.human.Menus;
import christmas.domain.human.Money;
import christmas.domain.menu.Menu;
import christmas.view.consts.OutputDetailMessage;
import christmas.view.consts.OutputTittleMessage;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String AMOUNT_NOTATION = "###,###";
    private final DecimalFormat formatter;

    public OutputView() {
        this.formatter = new DecimalFormat(AMOUNT_NOTATION);
    }

    public void displayMenu(Menus menus) {
        System.out.println(OutputTittleMessage.MENU.getMessage());
        menus.getMenus()
                .forEach((menu, count) ->
                        System.out.printf((OutputDetailMessage.MENU.getMessage()), menu, count)
                );
        System.out.println();
    }

    public void displayMoney(Money money) {
        System.out.println(OutputTittleMessage.MONEY.getMessage());
        System.out.printf(OutputDetailMessage.MONEY.getMessage(), formatter.format(money.getFee()));
        System.out.println();
    }


    public void displayGift(Map<Menu, Integer> giftCount, boolean isNone) {
        System.out.println(OutputTittleMessage.GIFT.getMessage());
        if (isNone) {
            System.out.printf(OutputDetailMessage.NONE.getMessage());
            System.out.println();
            return;
        }

        giftCount.forEach((key, value) -> System.out.printf(OutputDetailMessage.GIFT.getMessage(), key.getMenuName(), value));
        System.out.println();
    }

    public void displayBenefitReceipt(Map<Event, Integer> discountResult, boolean isNone) {
        System.out.println(OutputTittleMessage.BENEFIT_RECEIPT.getMessage());
        if (isNone) {
            System.out.printf(OutputDetailMessage.NONE.getMessage());
            System.out.println();
            return;
        }

        discountResult.forEach((key, value) -> {
            System.out.printf(OutputDetailMessage.EVENT_NAME.getMessageNoSeparator(), key);
            System.out.printf(OutputDetailMessage.DISCOUNT.getMessage(), formatter.format(value));
        });
        System.out.println();
    }

    public void displayBadge(Badges badges) {
        System.out.println(OutputTittleMessage.BADGE.getMessage());
        System.out.println(badges.getLabel());
        System.out.println();
    }

    public void displayRealFee(Money money, Money discount) {
        System.out.println(OutputTittleMessage.REAL_FEE.getMessage());
        System.out.printf(OutputDetailMessage.MONEY.getMessage(), formatter.format(money.compareTo(discount)));
        System.out.println();
    }

    public void displayDiscountAmount(Money discount) {
        System.out.println(OutputTittleMessage.DISCOUNT_AMOUNT.getMessage());
        if (discount.isNone()) {
            System.out.println(OutputDetailMessage.NONE.getMessage());
            System.out.println();
            return;
        }
        System.out.printf(OutputDetailMessage.DISCOUNT.getMessage(), formatter.format(discount.getFee()));
        System.out.println();
    }

    public void displayStart() {
        System.out.println(OutputTittleMessage.START.getMessage());
    }

    public void displayEvent() {
        System.out.println(OutputTittleMessage.EVENT.getMessage());
    }
}
