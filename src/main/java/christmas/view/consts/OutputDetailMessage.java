package christmas.view.consts;

public enum OutputDetailMessage {
    MENU("%s %d개"),
    MONEY("%s원"),
    GIFT("%s %d개"),
    EVENT_NAME("%s: "),
    DISCOUNT("-%s원"),
    NONE("없음");
    private final String message;

    OutputDetailMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
