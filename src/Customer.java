public class Customer implements Runnable{
    private String customerId;
    private int retrievalInterval;
    private TicketPool ticketPool;
    private boolean isRunning;

    public Customer(String customerId, int retrievalInterval, TicketPool ticketPool) {
        this.customerId = customerId;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
        this.isRunning = true;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(retrievalInterval);
                boolean success = ticketPool.removeTicket();
                if (success) {
                    System.out.println("Customer " + customerId + " purchased a ticket. Remaining tickets: " + ticketPool.getTicketCount());
                } else {
                    System.out.println("Customer " + customerId + " failed to purchase a ticket. No tickets available.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Customer " + customerId + " was interrupted.");
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
