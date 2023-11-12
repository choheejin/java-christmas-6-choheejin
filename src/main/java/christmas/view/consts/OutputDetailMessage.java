package christmas.view.consts;

public enum OutputDetailMessage {
    MENU("%s %d개"),
    MONEY("%s원"),
    GIFT("샴페인 %d개"),
    NONE("없음");
    private final String message;
    OutputDetailMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message + System.lineSeparator();
    }
}
