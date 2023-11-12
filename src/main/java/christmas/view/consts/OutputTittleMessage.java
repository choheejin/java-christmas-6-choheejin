package christmas.view.consts;

public enum OutputTittleMessage {
    MENU("<주문 메뉴>"),
    MONEY("<할인 전 총주문 금액>"),
    GIFT("<증정 메뉴>"),
    DISCOUNT("<혜택 내역>");

    private final String message;

    OutputTittleMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
