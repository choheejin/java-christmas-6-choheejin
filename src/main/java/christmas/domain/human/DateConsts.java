package christmas.domain.human;

import java.util.List;

public interface DateConsts {
    static final int YEAR = 2023;
    static final int MONTH = 12;

    static final List<Integer> WEEKDAY = List.of(1, 2, 3, 4, 7);
    static final List<Integer> WEEKEND = List.of(5, 6);
    static final List<Integer> SPECIAL_DAY = List.of(3, 10, 17, 24, 25, 31);
}
