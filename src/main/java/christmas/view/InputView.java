package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.event.ICondition;
import christmas.domain.menu.Menu;
import christmas.view.consts.ErrorMessage;
import christmas.view.consts.InputMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputView implements ICondition {
    private static final String R_NUMBER = "^[\\d]*$";
    private static final String SEPARATOR_COMMA = ",";
    private static final String SEPARATOR_DASH = "-";
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

            validateNumber(input);
            validateNone(input);

            date = Integer.parseInt(input);
        } catch (IllegalArgumentException exception) {
            System.out.println(ErrorMessage.NOT_VALIDATE_DATE.getMessage());
            readDate();
        }

        return date;
    }

    public Map<String, Integer> readOrders() {
        try {
            System.out.println(InputMessage.READ_MENU.getMessage());
            String input = Console.readLine().trim();

            validateNone(input);

            List<String> inputSeparateByComa = Arrays.asList(input.split(SEPARATOR_COMMA, -1));
            inputMenu = new HashMap<>();

            initMenus(inputSeparateByComa);
        } catch (IllegalArgumentException exception) {
            System.out.println(ErrorMessage.NOT_VALIDATE_MENU.getMessage());
            readOrders();
        }

        return inputMenu;
    }

    private void initMenus(List<String> inputs) throws IllegalArgumentException {
        for (String input : inputs) {
            List<String> inputSeparateByDash = Arrays.stream(input.split(SEPARATOR_DASH)).toList();

            validateOrderForm(inputSeparateByDash);
            validateMenu(inputSeparateByDash.get(MENU_IDX));
            validateCount(inputSeparateByDash.get(COUNT_IDX));

            inputMenu.put(inputSeparateByDash.get(MENU_IDX), Integer.parseInt(inputSeparateByDash.get(COUNT_IDX)));
        }
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

    private void validateNumber(String input) throws IllegalArgumentException {
        if (!input.matches(R_NUMBER)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCount(String input) throws IllegalArgumentException {
        validateNumber(input);

        if (Integer.parseInt(input) <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private void validateNone(String input) throws IllegalArgumentException {
        if (input.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
