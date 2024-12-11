package org.example;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TicketPool {
    private final List<String> tickets = Collections.synchronizedList(new LinkedList<>());
    private final int maxCapacity;
    private int ticketCounter = 0; // Counter for ticket IDs

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Adds tickets to the pool, starting from Ticket-1
    public synchronized void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            if (tickets.size() >= maxCapacity) {
                Logger.info("TicketPool reached maximum capacity. No more tickets can be added.");
                Logger.disableLogging(); // stop logging
                break;
            }
            ticketCounter++;
            String ticket = "Ticket-" + ticketCounter;
            tickets.add(ticket);
            Logger.info(ticket + " added. Total tickets: " + tickets.size());
        }
    }

    // Removes a ticket from the pool if available
    public synchronized String removeTicket() {
        if (tickets.isEmpty()) {
            Logger.info("No tickets available. Customer is waiting.");
            return null;
        }
        String removedTicket = tickets.remove(0);
        Logger.info(removedTicket + " retrieved by customer. Tickets remaining: " + tickets.size());
        return removedTicket;
    }

    // Checks if the pool is full
    public synchronized boolean isFull() {
        return tickets.size() >= maxCapacity;
    }

    // Checks if the pool is empty
    public synchronized boolean isEmpty() {
        return tickets.isEmpty();
    }

    // Gets the current ticket count
    public synchronized int getTicketCount() {
        return tickets.size();
    }
}





