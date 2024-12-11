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
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (ticketPool) {
                for (int i = 0; i < retrievalRate; i++) {
                    while (ticketPool.isEmpty()) {
                        Logger.info("No tickets available. Customer is waiting.");
                        try {
                            ticketPool.wait(); // Wait for tickets to be added
                        } catch (InterruptedException e) {
                            Logger.warn("Customer thread interrupted.");
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    ticketPool.removeTicket(); // Retrieve a ticket
                }
                ticketPool.notifyAll(); // Notify vendor if tickets fall below max capacity
            }
            try {
                Thread.sleep(1000); // Simulate one-second tick
            } catch (InterruptedException e) {
                Logger.warn("Customer thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }
        Logger.info("Customer stopped retrieving tickets. No more tickets will be retrieved.");
    }
}






