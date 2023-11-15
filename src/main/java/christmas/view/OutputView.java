package christmas.view;

import christmas.domain.badge.Badges;
import christmas.domain.event.Event;
import christmas.domain.human.Date;
import christmas.domain.human.Orders;
import christmas.domain.human.Money;
import christmas.domain.menu.Menu;
import christmas.view.consts.ErrorMessage;
import christmas.view.consts.OutputDetailMessage;
import christmas.view.consts.OutputTittleMessage;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String AMOUNT_NOTATION = "###,###";
    private final DecimalFormat formatter;

    public OutputView() {
        this.formatter = new DecimalFormat(AMOUNT_NOTATION);
    }

    public void displayError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void displayOrders(Orders orders) {
        System.out.println(OutputTittleMessage.MENU.getMessage());
        orders.getOrders()
                .forEach((menu, count) ->
                        System.out.printf((OutputDetailMessage.MENU.getMessage() + LINE_SEPARATOR), menu, count)
                );
        System.out.println();
    }

    public void displayMoney(Money money) {
        System.out.println(OutputTittleMessage.MONEY.getMessage());
        System.out.printf(OutputDetailMessage.MONEY.getMessage() + LINE_SEPARATOR, formatter.format(money.getFee()));
        System.out.println();
    }


    public void displayGift(Map<Menu, Integer> giftCount, boolean isNone) {
        System.out.println(OutputTittleMessage.GIFT.getMessage());
        if (isNone) {
            System.out.printf(OutputDetailMessage.NONE.getMessage() + LINE_SEPARATOR);
            System.out.println();
            return;
        }

        giftCount.forEach((key, value) -> System.out.printf(OutputDetailMessage.GIFT.getMessage() + LINE_SEPARATOR, key.getMenuName(), value));
        System.out.println();
    }

    public void displayBenefitReceipt(Map<Event, Integer> discountResult, boolean isNone) {
        System.out.println(OutputTittleMessage.BENEFIT_RECEIPT.getMessage());
        if (isNone) {
            System.out.printf(OutputDetailMessage.NONE.getMessage() + LINE_SEPARATOR);
            System.out.println();
            return;
        }

        discountResult.forEach((key, value) -> {
            System.out.printf(OutputDetailMessage.EVENT_NAME.getMessage(), key.getName());
            System.out.printf(OutputDetailMessage.DISCOUNT.getMessage() + LINE_SEPARATOR, formatter.format(value));
        });
        System.out.println();
    }

    public void displayBadge(Badges badges) {
        System.out.println(OutputTittleMessage.BADGE.getMessage());
        System.out.printf(badges.getLabel());
    }

    public void displayRealFee(int realFee) {
        System.out.println(OutputTittleMessage.REAL_FEE.getMessage());
        System.out.printf(OutputDetailMessage.MONEY.getMessage() + LINE_SEPARATOR, formatter.format(realFee));
        System.out.println();
    }

    public void displayDiscountAmount(Money discount) {
        System.out.println(OutputTittleMessage.DISCOUNT_AMOUNT.getMessage());
        if (discount.isNone()) {
            System.out.println(OutputDetailMessage.NONE.getMessage());
            System.out.println();
            return;
        }
        System.out.printf(OutputDetailMessage.DISCOUNT.getMessage() + LINE_SEPARATOR, formatter.format(discount.getFee()));
        System.out.println();
    }

    public void displayStart() {
        System.out.println(OutputTittleMessage.START.getMessage());
    }

    public void displayEvent(int date) {
        System.out.printf(OutputTittleMessage.EVENT.getMessage() + LINE_SEPARATOR, date);
        System.out.println();
    }
}
