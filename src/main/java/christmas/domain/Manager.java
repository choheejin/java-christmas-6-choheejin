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
    private Money discountAmount;
    private Money benefitAmount;
    private GiftEvent giftEvent;
    private DiscountEvent discountEvent;
    private Benefit benefit;

    public Manager() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void operate() {
        readDate();
        readOrders();
        calculate();
        display();
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

    private void calculate() {
        money = new Money(orders.getTotalOrderAmount());

        discountEvent = new DiscountEvent(orders, money, date);
        giftEvent = new GiftEvent(money);
        benefit = new Benefit(discountEvent, giftEvent);

        discountAmount = new Money(discountEvent.getTotalDiscount());
        benefitAmount = new Money(benefit.getTotalBenefit());
    }

    private void display() {
        outputView.displayStart();
        outputView.displayMenu(orders);
        outputView.displayMoney(money);
        outputView.displayEvent();
        outputView.displayGift(giftEvent.getGiftResult(), giftEvent.isGiftNone());
        outputView.displayBenefitReceipt(benefit.getBenefitReceipt(), benefit.isNoneOfBenefit());
        outputView.displayDiscountAmount(benefitAmount);
        outputView.displayRealFee(money.compareTo(discountAmount));
        outputView.displayBadge(Badges.badgeMeetingConditions(benefit));
    }
}
