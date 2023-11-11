package christmas.view.consts;

public enum ErrorMessage {
    NOT_VALIDATE_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NOT_VALIDATE_MENU("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private static final String defaultMessage = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return defaultMessage + message;
    }
}
