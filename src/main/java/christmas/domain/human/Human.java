package christmas.domain.human;

import java.time.LocalDate;

public class Human implements DateConsts {
    private final int money;
    private final int day;
    private final int dayOfWeek;

    public Human(int money, int day) {
        this.money = money;
        this.day = day;
        this.dayOfWeek = LocalDate.of(YEAR, MONTH, day).getDayOfWeek().getValue();
    }

    public boolean isMoneyExceedStandard(int std) {
        return money >= std;
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