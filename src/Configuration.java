import java.io.Serializable;
import java.util.Scanner;

public class Configuration implements Serializable {
    private static final long serialVersionUID = 1L;
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public Configuration(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public static Configuration promptForConfiguration() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter total number of tickets: ");
        int totalTickets = input.nextInt();
        System.out.println("Enter ticket release rate: ");
        int ticketReleaseRate = input.nextInt();
        System.out.println("Enter customer retrieval rate: ");
        int customerRetrievalRate = input.nextInt();
        System.out.println("Enter maximum ticket capacity: ");
        int maxTicketCapacity = input.nextInt();
        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }
}
