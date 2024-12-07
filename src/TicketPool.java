import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private final List<String> tickets = Collections.synchronizedList(new ArrayList<>());

    public void addTickets(int numberOfTickets) {
        synchronized (tickets) {
            for (int i = 0; i < numberOfTickets; i++) {
                tickets.add("Ticket " + (tickets.size() + 1));
            }
            System.out.println(numberOfTickets + " tickets added. Total tickets: " + tickets.size());
        }
    }

    public boolean removeTicket(){
        synchronized (tickets){
            if(!tickets.isEmpty()){
                tickets.remove(0);
                System.out.println("Ticket removed. Total tickets: " + tickets.size());
                return true;
            } else{
                System.out.println("No tickets available to remove.");
                return false;
            }
        }
    }

    public int getTicketCount(){
        synchronized (tickets){
            return tickets.size();
        }
    }
}
