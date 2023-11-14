package christmas.domain.human;

import christmas.view.consts.ErrorMessage;

import java.time.LocalDate;

public class Date implements DateConsts{
    private final int day;

    public Date(int day) {
        validateDate(day);
        this.day = day;
    }

    private void validateDate(int day) throws IllegalArgumentException {
        if (day < START || day > END) {
            throw new IllegalArgumentException(ErrorMessage.NOT_VALIDATE_DATE.getMessage());
        }
    }

    private int getDayOfWeek() {
        return LocalDate.of(YEAR, MONTH, day).getDayOfWeek().getValue();
    }

    public int getDifferenceBaseDate(int std) {
        return day - std;
    }

    public boolean isDayBelowStandard(int std) {
        return day <= std;
    }

    public boolean isWeekday() {
        return WEEKDAY.contains(getDayOfWeek());
    }

    public boolean isWeekend() {
        return WEEKEND.contains(getDayOfWeek());
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAY.contains(day);
    }
}
