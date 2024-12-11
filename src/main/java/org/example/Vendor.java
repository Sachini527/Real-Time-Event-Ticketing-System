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
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (ticketPool) {
                if (ticketPool.isFull()) {
                    Logger.info("Maximum ticket capacity reached. Vendor stops adding tickets.");
                    ticketPool.notifyAll(); // Notify customers waiting on tickets
                    break; // Exit the loop and terminate the vendor thread
                }
                ticketPool.addTickets(releaseRate);
                ticketPool.notifyAll(); // Notify customers after adding tickets
            }
            try {
                Thread.sleep(1000); // Simulate one-second tick
            } catch (InterruptedException e) {
                Logger.warn("Vendor thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }
        Logger.info("Vendor stopped adding tickets. No more tickets will be added.");
    }
}









