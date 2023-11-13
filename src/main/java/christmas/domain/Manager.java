package christmas.domain;

import christmas.domain.badge.Badges;
import christmas.domain.event.Benefit;
import christmas.domain.event.DiscountEventPolicy;
import christmas.domain.event.GiftEventPolicy;
import christmas.domain.human.Date;
import christmas.domain.human.Menus;
import christmas.domain.human.Money;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Manager {
    private final InputView inputView;
    private final OutputView outputView;

    private Date date;
    private Menus menus;
    private Money money;
    private Money discountAmount;
    private Money benefitAmount;
    private GiftEventPolicy giftEventPolicy;
    private DiscountEventPolicy discountEventPolicy;
    private Benefit benefit;

    public Manager() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void operate() {
        read();
        calculate();
        display();
    }

    private void read() {
        date = new Date(inputView.readDate());
        menus = new Menus(inputView.readMenu());
    }

    private void calculate() {
        money = new Money(menus.getTotalOrderAmount());

        discountEventPolicy = new DiscountEventPolicy(menus, money, date);
        giftEventPolicy = new GiftEventPolicy(money);
        benefit = new Benefit(discountEventPolicy, giftEventPolicy);

        discountAmount = new Money(discountEventPolicy.getTotalDiscount());
        benefitAmount = new Money(benefit.getTotalBenefit());
    }

    private void display() {
        outputView.displayStart();
        outputView.displayMenu(menus);
        outputView.displayMoney(money);
        outputView.displayEvent();
        outputView.displayGift(giftEventPolicy.getGiftResult(), giftEventPolicy.isGiftNone());
        outputView.displayBenefitReceipt(benefit.getBenefitReceipt(), benefit.isNoneOfBenefit());
        outputView.displayDiscountAmount(benefitAmount);
        outputView.displayRealFee(money.compareTo(discountAmount));
        outputView.displayBadge(Badges.badgeMeetingConditions(benefit));
    }
}
