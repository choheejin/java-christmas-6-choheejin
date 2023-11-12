package christmas.domain.human;

public class Money {
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
}
