package christmas.domain.human;

public class Money implements Comparable<Money> {
    private final int fee;
    public Money(int fee) {
        this.fee = fee;
    }

    public boolean isMoneyExceedStandard(int std) {
        return fee >= std;
    }

    public int getFee() {
        return fee;
    }

    @Override
    public int compareTo(Money other) {
        return this.fee - other.fee;
    }
}
