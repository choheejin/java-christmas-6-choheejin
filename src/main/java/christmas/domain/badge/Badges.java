package christmas.domain.badge;

import christmas.domain.event.Benefit;

import java.util.Arrays;

public enum Badges {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String label;
    private final int money;

    Badges(String label, int money) {
        this.label = label;
        this.money = money;
    }

    public String getLabel() {
        return label;
    }

    public static Badges badgeMeetingConditions(Benefit benefit) {
        return Arrays.stream(Badges.values())
                .filter(i -> benefit.isTotalBenefitExceedStandard(i.money))
                .findAny()
                .orElseThrow();
    }
}
