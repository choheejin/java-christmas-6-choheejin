package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.menu.Menu;
import christmas.view.consts.ErrorMessage;
import christmas.view.consts.InputMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputView {
    private static final String R_NUMBER = "^[\\d]*$";
    private static final int DATE_START = 1;
    private static final int DATE_END = 31;
    private static final int MENU_IDX = 0;
    private static final int COUNT_IDX = 1;
    private static final int IDX_SIZE = 2;

    private int date;
    private Map<String, Integer> inputMenu;

    public int readDate() {
        String input;

        try {
            System.out.println(InputMessage.READ_DATE.getMessage());
            input = Console.readLine().trim();

            validateDate(input);

            date = Integer.parseInt(input);
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            System.out.println(ErrorMessage.NOT_VALIDATE_DATE.getMessage());
            readDate();
        }

        return date;
    }

    public Map<String, Integer> readMenu() {
        try {
            System.out.println(InputMessage.READ_MENU.getMessage());
            String input = Console.readLine().trim();

            List<String> inputSeparateByComa = Arrays.stream(input.split(",")).toList();
            inputMenu = new HashMap<>();

            for (String i : inputSeparateByComa) {
                initMenus(i);
            }
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            System.out.println(ErrorMessage.NOT_VALIDATE_MENU.getMessage());
            readMenu();
        }

        return inputMenu;
    }

    private void initMenus(String input) throws IllegalArgumentException {
        List<String> inputSeparateByDash = Arrays.stream(input.split("-")).toList();

        validateOrderForm(inputSeparateByDash);
        validateMenu(inputSeparateByDash.get(MENU_IDX));
        validateCount(inputSeparateByDash.get(COUNT_IDX));

        inputMenu.put(inputSeparateByDash.get(0), Integer.parseInt(inputSeparateByDash.get(1)));
    }

    private void validateOrderForm(List<String> inputs) throws IllegalArgumentException {
        if (inputs.size() != IDX_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void validateMenu(String input) throws IllegalArgumentException {
        if (!Menu.isContainMenu(input)) {
            throw new IllegalArgumentException();
        }

        if (inputMenu.containsKey(input)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCount(String input) throws IllegalArgumentException {
        if (!input.matches(R_NUMBER)) {
            throw new IllegalArgumentException();
        }

        if (Integer.parseInt(input) <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDate(String input) throws IllegalArgumentException {
        if (input.isBlank()) {
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
