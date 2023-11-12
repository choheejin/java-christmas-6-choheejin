package christmas;

import christmas.domain.badge.Badges;
import christmas.domain.event.DiscountEventPolicy;
import christmas.domain.event.GiftEventPolicy;
import christmas.domain.human.BenefitAmount;
import christmas.domain.human.Date;
import christmas.domain.human.Menus;
import christmas.domain.human.Money;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        Date date = new Date(inputView.readDate());
        Menus menus = new Menus(inputView.readMenu());
        Money money = new Money(menus.getTotalOrderAmount());

        Map<String, Integer> discountResult = new DiscountEventPolicy().getDiscountAmount(menus, money, date);
        int giftCount = new GiftEventPolicy().getGiftCount(money);

        BenefitAmount benefitAmount = new BenefitAmount(discountResult);

        outputView.displayMenu(menus);
        outputView.displayMoney(money);
        outputView.displayGift(giftCount);
    }
}
