package springshop.model.code;

public enum DeliveryStatus {
    READY("READY"),
    SHIPPED("SHIPPED"),
    COMPLETE("COMPLETE"),
    CANCELLED("CANCELLED");

    private final String code;

    DeliveryStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}

