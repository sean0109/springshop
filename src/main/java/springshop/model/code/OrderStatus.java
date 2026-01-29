package springshop.model.code;

public enum OrderStatus {
    READY("READY"),
    CONFIRMED("CONFIRMED"),
    CANCELED("CANCELED"),;

    private final String code;

    OrderStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
