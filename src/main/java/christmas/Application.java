package christmas;

import christmas.domain.badge.Badges;
import christmas.domain.event.DiscountEventPolicy;
import christmas.domain.event.GiftEventPolicy;
import christmas.domain.human.Date;
import christmas.domain.human.Menus;
import christmas.domain.human.Money;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Date date = new Date(inputView.readDate());
        Menus menus = new Menus(inputView.readMenu());
        Money money = new Money(menus.getTotalOrderAmount());

        DiscountEventPolicy discountEventPolicy = new DiscountEventPolicy(menus, money, date);
        GiftEventPolicy giftEventPolicy = new GiftEventPolicy(money);

        outputView.displayMenu(menus);
        outputView.displayMoney(money);
        outputView.displayGift(giftEventPolicy.getGiftResult(), giftEventPolicy.isGiftNone());
        outputView.displayDiscount(discountEventPolicy.getDiscountReceipt(), discountEventPolicy.isDiscountAllNone());
    }
}
