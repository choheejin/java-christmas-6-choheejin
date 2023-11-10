package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.consts.ErrorMessage;
import christmas.view.consts.InputMessage;

public class InputView {
    private static final String R_NUMBER = "^[\\d]*$";
    private static final int DATE_START = 1;
    private static final int DATE_END = 31;

    public int readDate() {
        String input;
        do {
            System.out.println(InputMessage.READ_DATE.getMessage());
            input = Console.readLine().trim();
        } while (isErrorOccurred(input));

        return Integer.parseInt(input);
    }

    private boolean isErrorOccurred(String input) {
        try {
            validateDate(input);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            System.out.println(ErrorMessage.NOT_VALIDATE_DATE.getMessage());
            return true;
        }
        return false;
    }

    private void validateDate(String input) throws IllegalArgumentException {
        if(input.isBlank()) {
            throw new IllegalArgumentException();
        }

        if (!input.matches(R_NUMBER)) {
            throw new IllegalArgumentException();
        }

        if (Integer.parseInt(input) < DATE_START || Integer.parseInt(input) > DATE_END) {
            throw new IllegalArgumentException();
        }
    }
}
