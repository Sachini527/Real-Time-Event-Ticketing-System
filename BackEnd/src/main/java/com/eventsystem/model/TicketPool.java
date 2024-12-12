package com.eventsystem.model;

import com.eventsystem.util.CustomLogger;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private int totalTickets;
    private int maxCapacity;
    private int currentTickets = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private static final CustomLogger logger = new CustomLogger(TicketPool.class);

    public TicketPool(int totalTickets, int maxCapacity) {
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
    }

    public boolean addTicket() {
        lock.lock();
        try {
            if (currentTickets < maxCapacity && totalTickets > 0) {
                currentTickets++;
                totalTickets--;
                logger.logInfo("Ticket added. Current Tickets: " + currentTickets + ", Remaining Total: " + totalTickets);
                return true;
            } else {
                logger.logInfo("Cannot add ticket. Pool is full or no tickets left.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean removeTicket() {
        lock.lock();
        try {
            if (currentTickets > 0) {
                currentTickets--;
                logger.logInfo("Ticket removed. Current Tickets: " + currentTickets);
                return true;
            } else {
                logger.logInfo("Cannot remove ticket. Pool is empty.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }
}

