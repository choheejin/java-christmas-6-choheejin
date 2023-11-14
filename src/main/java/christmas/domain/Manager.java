package christmas.domain;

import christmas.domain.badge.Badges;
import christmas.domain.event.Benefit;
import christmas.domain.event.DiscountEvent;
import christmas.domain.event.GiftEvent;
import christmas.domain.human.Date;
import christmas.domain.human.Orders;
import christmas.domain.human.Money;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.consts.ErrorMessage;

public class Manager {
    private final InputView inputView;
    private final OutputView outputView;

    private Date date;
    private Orders orders;
    private Money money;

    public Manager() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void operate() {
        readDate();
        readOrders();
        money = new Money(orders.getTotalOrderAmount());

        DiscountEvent discountEvent = new DiscountEvent(orders, money, date);
        GiftEvent giftEvent = new GiftEvent(money);
        Benefit benefit = new Benefit(discountEvent, giftEvent);

        Money discountAmount = new Money(discountEvent.getTotalDiscount());
        Money benefitAmount = new Money(benefit.getTotalBenefit());

        displayOrders();
        displayEvent(giftEvent, benefit);
        displayCalculate(discountAmount, benefitAmount);
    }

    private void readDate() {
        try {
            date = new Date(inputView.readDate());
        } catch (IllegalArgumentException exception) {
            outputView.displayError(ErrorMessage.NOT_VALIDATE_DATE);
            readDate();
        }
    }

    private void readOrders() {
        try {
            orders = new Orders(inputView.readOrders());
        } catch (IllegalArgumentException exception) {
            outputView.displayError(ErrorMessage.NOT_VALIDATE_MENU);
            readOrders();
        }
    }

    private void displayOrders() {
        outputView.displayStart();
        outputView.displayOrders(orders);
        outputView.displayMoney(money);
    }

    private void displayEvent(GiftEvent giftEvent, Benefit benefit) {
        outputView.displayEvent();
        outputView.displayGift(giftEvent.getGiftResult(), giftEvent.isGiftNone());
        outputView.displayBenefitReceipt(benefit.getBenefitReceipt(), benefit.isNoneOfBenefit());
    }

    private void displayCalculate(Money discountAmount, Money benefitAmount) {
        outputView.displayDiscountAmount(benefitAmount);
        outputView.displayRealFee(money.compareTo(discountAmount));
        outputView.displayBadge(Badges.badgeMeetingConditions(benefitAmount));
    }

}
