package christmas.domain.human;

import java.time.LocalDate;

public class Date implements DateConsts{
    private final int day;

    public Date(int day) {
        this.day = day;
    }

    public int getDayOfWeek() {
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
