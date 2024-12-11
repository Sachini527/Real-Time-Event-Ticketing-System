package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TicketPool {
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());
    private final int maxCapacity;
    private int ticketCounter = 0;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            if (tickets.size() >= maxCapacity) {
                Logger.info("Maximum capacity reached. Cannot add more tickets.");
                return;
            }
            ticketCounter++;
            String ticket = "Ticket-" + ticketCounter;
            tickets.add(ticket);
            Logger.info(ticket + " added. Total tickets: " + tickets.size());
        }
    }

    public synchronized String removeTicket() {
        if (tickets.isEmpty()) {
            Logger.info("No tickets available. Customer is waiting.");
            return null;
        }
        String removedTicket = tickets.remove(0);
        Logger.info(removedTicket + " retrieved by customer. Tickets remaining: " + tickets.size());
        return removedTicket;
    }

    public synchronized boolean isFull() {
        return tickets.size() >= maxCapacity;
    }

    public synchronized boolean isEmpty() {
        return tickets.isEmpty();
    }
}









