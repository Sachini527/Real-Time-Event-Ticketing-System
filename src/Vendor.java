import javax.swing.*;

public class Vendor implements Runnable{
    private String vendorId;
    private int ticketsPerRelease;
    private int releaseInterval;
    // create a TicketPool object
    private TicketPool ticketPool;
    private boolean isRunning;

    public Vendor(String vendorId, int ticketsPerRelease, int releaseInterval, TicketPool ticketPool) {
        this.vendorId = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
        this.isRunning = true;
    }

    public void run(){
        while(isRunning){
            try{
                Thread.sleep(releaseInterval);
                ticketPool.addTickets(ticketsPerRelease);
                System.out.println("Vendor " + vendorId + " added " + ticketsPerRelease + " tickets to the pool. Total tickets: " + ticketPool.getTicketCount());
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Vendor " + vendorId + " was interrupted.");
            }
        }
    }

    public void stop(){
        isRunning = false;
    }
}
