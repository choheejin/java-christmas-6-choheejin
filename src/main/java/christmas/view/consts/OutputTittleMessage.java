package christmas.view.consts;

public enum OutputTittleMessage {
    MENU("<주문 메뉴>"),
    MONEY("<할인 전 총주문 금액>"),
    GIFT("<증정 메뉴>"),
    DISCOUNT("<혜택 내역>"),
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
