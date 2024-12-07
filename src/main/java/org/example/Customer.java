package org.example;


public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final Configuration config;

    public Customer(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.config = config;
    }

    @Override
    public void run() {
        while (!ticketPool.isProcessingComplete()) {
            boolean consumed = ticketPool.consumeTickets(config.getCustomerRetrieveRate());
            if (!consumed) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Logger.logError("Customer interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

/*
public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final Configuration config;

    public Customer(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.config = config;
    }

    @Override
    public void run() {
        while (!ticketPool.isProcessingComplete()) {
            boolean consumed = ticketPool.consumeTickets(config.getCustomerRetrieveRate());
            if (!consumed) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Logger.logError("Customer interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
*/
