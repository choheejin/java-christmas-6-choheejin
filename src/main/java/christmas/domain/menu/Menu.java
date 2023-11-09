package christmas.domain.menu;

public enum Menu {
    MUSHROOM_SOUP("에피타이저", "양송이수프", 6_000),
    TAPAS("에피타이저", "타파스", 5_500),
    CAESAR_SALAD("에피타이저", "시저샐러드", 8_000),
    T_BONE_STEAK("메인", "티본스테이크", 55_000),
    BBQ_RIBS("메인", "바비큐립", 54_000),
    SEAFOOD_PASTA("메인", "해산물파스타", 35_000),
    CHRISTMAS_PASTA("메인", "크리스마스파스타", 25_000),
    CHOCO_CAKE("디저트", "초코케이크", 15_000),
    ICE_CREAM("디저트", "아이스크림", 5_000),
    ZERO_COLA("음료", "제로콜라", 3_000),
    RED_WINE("음료", "레드와인", 60_000),
    CHAMPAGNE("음료", "샴페인", 25_000);

    private final String category;
    private final String name;
    private final int prize;

    Menu(String category, String name, int prize) {
        this.category = category;
        this.name = name;
        this.prize = prize;
    }

    public String getCategory() {
        return category;
    }
}
