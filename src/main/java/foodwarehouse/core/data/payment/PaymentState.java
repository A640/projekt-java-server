package foodwarehouse.core.data.payment;

import java.util.Optional;

public enum PaymentState {
    IN_PROGRESS("IN PROGRESS"),
    COMPLETED("COMPLETED"),
    REJECTED("REJECTED"),
    CANCELED("CANCELED"),
    WAITING("WAITING FOR PAYMENT");

    private final String value;

    PaymentState(String value) {
        this.value = value;
    }

    public static Optional<PaymentState> from(String value) {
        return switch (value) {
            case "IN PROGRESS" -> Optional.of(PaymentState.IN_PROGRESS);
            case "COMPLETED" -> Optional.of(PaymentState.COMPLETED);
            case "REJECTED" -> Optional.of(PaymentState.REJECTED);
            case "CANCELED" -> Optional.of(PaymentState.CANCELED);
            case "WAITING FOR PAYMENT" -> Optional.of(PaymentState.WAITING);
            default -> Optional.empty();
        };
    }

    public String value() {
        return value;
    }
}
