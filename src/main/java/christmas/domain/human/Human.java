package christmas.domain.human;

import java.time.LocalDate;
import java.util.List;

public class Human {
    private final int money;
    private final int day;
    private final LocalDate fullDate;
    private final int weekDate;

    public Human(int money, int day) {
        this.money = money;
        this.day = day;
        this.fullDate = LocalDate.of(2023, 12, day);
        this.weekDate = fullDate.getDayOfWeek().getValue();
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
        List<Integer> weekday = List.of(1, 2, 3, 4, 7);
        return weekday.contains(weekDate);
    }

    public boolean isWeekend() {
        List<Integer> weekend = List.of(5, 6);
        return weekend.contains(weekDate);
    }

    public boolean isSpecialDay() {
        List<Integer> specialDay = List.of(3, 10, 17, 24, 25, 31);
        return specialDay.contains(day);
    }
}