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
        int totalTickets = promptForInt(input, "Enter total number of tickets: ");
        int ticketReleaseRate = promptForInt(input, "Enter ticket release rate: ");
        int customerRetrievalRate = promptForInt(input, "Enter customer retrieval rate: ");
        int maxTicketCapacity = promptForInt(input, "Enter maximum ticket capacity: ");
        return new Configuration(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);

    }

    private static int promptForInt(Scanner input, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                value = input.nextInt();
                if (value > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.next(); // clear the invalid input
            }
        }
        return value;
    }
}
