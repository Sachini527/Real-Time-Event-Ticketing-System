public class Vendor implements Runnable{
    private String vendorId;
    private int ticketsPerRelease;
    private int releaseInterval;

    public Vendor(String vendorId, int ticketsPerRelease, int releaseInterval) {
        this.vendorId = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
    }




}
