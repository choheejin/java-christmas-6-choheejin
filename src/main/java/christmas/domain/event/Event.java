package christmas.domain.event;

public enum Event {
    CHRISTMAS("크리스마스 디데이 할인", 1_000),
    WEEKDAY("평일 할인", 2_023),
    WEEKEND("주말 할인", 2_023),
    SPECIAL("특별 할인", 1_000),
    GIFT("증정 이벤트", 0);

    private static final int ADDITIONAL_DISCOUNTS = 100;
    private final String name;
    private final int defaultDiscount;

    Event(String name, int defaultDiscount) {
        this.name = name;
        this.defaultDiscount = defaultDiscount;
    }

    public static int getChristmasDiscount(int gap) {
        return CHRISTMAS.defaultDiscount + ADDITIONAL_DISCOUNTS * gap;
    }

    public static int getWeekDayDiscount(int count) {
        return WEEKDAY.defaultDiscount * count;
    }

    public static int getWeekEndDiscount(int count) {
        return WEEKEND.defaultDiscount * count;
    }

    public static int getSpecialDiscount() {
        return SPECIAL.defaultDiscount;
    }

    public String getName() {
        return name;
    }
}
