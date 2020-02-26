package core.testdata;

public abstract class ItemOrderDetails {

    public enum CollectOption {
        SHIP_TO_STORE("Ship to store"), SHIP_BY_MAIL("Ship by mail");
        private String value;
        CollectOption(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public enum BillingAddress {
        DIFFERENT("Different"), SAME("Same");
        private String value;
        BillingAddress(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public enum PaymentMethod {
        CREDIT_CARD("creditCard"), PAYPAL("payPal"), INTERAC("IOP"), VISA_CHECKOUT("visaCheckout"),
        SAVED_PAYMENT("savedPayment");
        private String value;
        PaymentMethod(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }
}
