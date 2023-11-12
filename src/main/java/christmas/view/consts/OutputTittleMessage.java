package christmas.view.consts;

public enum OutputTittleMessage {
    START("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    EVENT("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    MENU("<주문 메뉴>"),
    MONEY("<할인 전 총주문 금액>"),
    GIFT("<증정 메뉴>"),
    DISCOUNT_RECEIPT("<혜택 내역>"),
    DISCOUNT_AMOUNT("<총혜택 금액>"),
    BADGE("<12월 이벤트 배지>"),
    REAL_FEE("<할인 후 예상 결제 금액>");

    private final String message;

    OutputTittleMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
