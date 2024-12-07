package org.example;


public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final Configuration config;

    public Vendor(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.config = config;
    }

    @Override
    public void run() {
        while (!ticketPool.isProcessingComplete()) {
            boolean produced = ticketPool.produceTickets(config.getTicketReleaseRate());
            if (!produced) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Logger.logError("Vendor interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

/*
public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final Configuration config;

    public Vendor(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.config = config;
    }

    @Override
    public void run() {
        while (!ticketPool.isProcessingComplete()) {
            boolean produced = ticketPool.produceTickets(config.getTicketReleaseRate());
            if (!produced) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Logger.logError("Vendor interrupted: " + e.getMessage());
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
*/

