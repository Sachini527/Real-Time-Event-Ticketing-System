package org.example;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int releaseRate;

    public Vendor(TicketPool ticketPool, int releaseRate) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted() && !ticketPool.isFull()) {
            ticketPool.addTickets(releaseRate);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Logger.warn("TicketVendor interrupted.");
                Thread.currentThread().interrupt();
            }
        }
        Logger.info("TicketVendor stopped as the pool reached maximum capacity.");
    }
}






