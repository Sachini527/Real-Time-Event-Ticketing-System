public class Customer implements Runnable{
    private String customerId;
    private int retrievalInterval;

    public Customer(String customerId, int retrievalInterval) {
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
    }

}
