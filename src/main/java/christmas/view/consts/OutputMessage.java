package christmas.view.consts;

public enum OutputMessage {
    MENU_TITTLE("<주문 메뉴>"),
    MENU_DETAIL("%s %d개"),
    MONEY_TITTLE("<할인 전 총주문 금액>"),
    MONEY_DETAIL("%s원");
    private final String message;
    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
