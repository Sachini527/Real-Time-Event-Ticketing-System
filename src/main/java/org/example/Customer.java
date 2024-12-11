package org.example;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int retrievalRate;

    public Customer(TicketPool ticketPool, int retrievalRate) {
        this.ticketPool = ticketPool;
        this.retrievalRate = retrievalRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && !ticketPool.isEmpty()) {
            for (int i = 0; i < retrievalRate; i++) {
                ticketPool.removeTicket();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Logger.warn("TicketCustomer interrupted.");
                Thread.currentThread().interrupt();
            }
        }
        Logger.info("TicketCustomer stopped as the pool is empty.");
    }
}




