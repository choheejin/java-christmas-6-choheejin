package christmas.view.consts;

public enum OutputMessage {
    DISPLAY_MENU_TITTLE("<주문 메뉴>"),
    DISPLAY_MENU_DETAIL("%s %d개");
    private final String message;
    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
