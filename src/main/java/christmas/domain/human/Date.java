package christmas.domain.human;

import java.time.LocalDate;

public class Date implements DateConsts{
    private final int day;
    private final int dayOfWeek;

    public Date(int day) {
        this.day = day;
        this.dayOfWeek = LocalDate.of(YEAR, MONTH, day).getDayOfWeek().getValue();
    }

    public boolean isDayBelowStandard(int std) {
        return day <= std;
    }

    public int getDifferenceBaseDate(int std) {
        return day - std;
    }

    public boolean isWeekday() {
        return WEEKDAY.contains(dayOfWeek);
    }

    public boolean isWeekend() {
        return WEEKEND.contains(dayOfWeek);
    }

    public boolean isSpecialDay() {
        return SPECIAL_DAY.contains(day);
    }
}
